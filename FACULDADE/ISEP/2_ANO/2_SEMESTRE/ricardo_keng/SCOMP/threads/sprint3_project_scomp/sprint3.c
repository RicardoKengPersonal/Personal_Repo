#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <semaphore.h>
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <signal.h>

#define MAX_DRONES 100
#define SHM_NAME "/shm_drones"
#define SEM_NAME_BASE "/sem_drone_"
#define MAX_POSITIONS 100
#define MAX_LINE 128
#define NUM_DRONES 5
#define POSITION_FILE "positions.txt"

typedef struct {
    int drone_id, x, y, z;
} DronePosition;

DronePosition drone_positions[MAX_DRONES][MAX_POSITIONS];

sem_t* open_semaphore_for_drone(int drone_index) {
    char sem_name[64];
    snprintf(sem_name, sizeof(sem_name), "%s%d", SEM_NAME_BASE, drone_index);
    sem_t *sem = sem_open(sem_name, 0);
    if (sem == SEM_FAILED) {
        perror("sem_open in open_semaphore_for_drone");
        exit(1);
    }
    return sem;
}

void cleanup_semaphores(int num_drones) {
    for (int i = 0; i < num_drones; i++) {
        char sem_name[64];
        snprintf(sem_name, sizeof(sem_name), "%s%d", SEM_NAME_BASE, i);
        sem_unlink(sem_name);
    }
}

int read_positions(const char *file, int drone_id, int max_line, int max_positions) {
    FILE *f = fopen(file, "r");
    if (!f) {
        perror("Failed to open input file");
        exit(1);
    }

    char *line = malloc(max_line);
    int count = 0;

    while (fgets(line, max_line, f) && count < max_positions) {
        int id, x, y, z;
        if (sscanf(line, "%d %d %d %d", &id, &x, &y, &z) == 4 && id == drone_id) {
            drone_positions[drone_id][count++] = (DronePosition){id, x, y, z};
        }
    }

    free(line);
    fclose(f);
    return count;
}

// Global shared variables
int num_drones = NUM_DRONES;
int num_steps = 0;

pid_t drone_pids[NUM_DRONES];
sem_t *drone_sems[NUM_DRONES];
DronePosition *shm;

pthread_mutex_t positions_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t positions_updated = PTHREAD_COND_INITIALIZER;
pthread_cond_t collision_done = PTHREAD_COND_INITIALIZER;

int step = 0;
int active_drones[NUM_DRONES];  // 1 = alive, 0 = dead

void kill_drone(int i) {
    if (active_drones[i]) {
        printf("[Collision] Killing Drone %d (pid %d)\n", i, drone_pids[i]);
        kill(drone_pids[i], SIGKILL);
        waitpid(drone_pids[i], NULL, 0); // reap immediately
        active_drones[i] = 0;

        // Also close and unlink semaphore for that drone
        sem_close(drone_sems[i]);
        char sem_name[64];
        snprintf(sem_name, sizeof(sem_name), "%s%d", SEM_NAME_BASE, i);
        sem_unlink(sem_name);
    }
}

void* collision_thread_func(void *arg) {
    while (1) {
        pthread_mutex_lock(&positions_mutex);

        // Wait for signal from main thread that positions have been updated
        pthread_cond_wait(&positions_updated, &positions_mutex);

        // If all steps done, exit thread
        if (step >= num_steps) {
            pthread_mutex_unlock(&positions_mutex);
            return NULL;
        }

        // Check collisions only among active drones
        for (int i = 0; i < num_drones; i++) {
            if (!active_drones[i]) continue;
            for (int j = i + 1; j < num_drones; j++) {
                if (!active_drones[j]) continue;
                if (shm[i].x == shm[j].x &&
                    shm[i].y == shm[j].y &&
                    shm[i].z == shm[j].z) {
                    printf("[Collision] Step %d: Drone %d and Drone %d collided at (%d, %d, %d)\n",
                           step, shm[i].drone_id, shm[j].drone_id,
                           shm[i].x, shm[i].y, shm[i].z);

                    // Kill both drones
                    kill_drone(i);
                    kill_drone(j);
                }
            }
        }

        step++; // advance step

        // Signal main thread collision processing done
        pthread_cond_signal(&collision_done);

        pthread_mutex_unlock(&positions_mutex);
    }

    return NULL;
}

int main() 
{
    // Initialize active drones array
    for (int i = 0; i < num_drones; i++) {
        active_drones[i] = 1;
    }

    int fd;

    // Create/Open shared memory for all drones
    fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, 0666);
    if (fd == -1) {
        perror("shm_open");
        exit(1);
    }

    // Set size to hold array of DronePosition
    if (ftruncate(fd, sizeof(DronePosition) * num_drones) == -1) {
        perror("ftruncate");
        exit(2);
    }

    // Map shared memory
    shm = mmap(NULL, sizeof(DronePosition) * num_drones, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (shm == MAP_FAILED) {
        perror("mmap");
        exit(3);
    }

    // Create semaphores for each drone, initialized to 0
    for (int i = 0; i < num_drones; i++) 
    {
        char sem_name[64];
        snprintf(sem_name, sizeof(sem_name), "%s%d", SEM_NAME_BASE, i);
        sem_unlink(sem_name);  // unlink first to avoid conflicts
        drone_sems[i] = sem_open(sem_name, O_CREAT | O_EXCL, 0644, 0);
        if (drone_sems[i] == SEM_FAILED) {
            perror("sem_open");
            exit(4);
        }
    }

    // Read max steps from input file by counting lines for drone 0 (assuming equal lines per drone)
    num_steps = 0;
    FILE *f = fopen(POSITION_FILE, "r");
    if (!f) {
        perror("Failed to open input file");
        exit(1);
    }
    char buffer[MAX_LINE];
    while (fgets(buffer, sizeof(buffer), f)) {
        int id;
        if (sscanf(buffer, "%d", &id) == 1 && id == 0) {
            num_steps++;
        }
    }
    fclose(f);

    if (num_steps == 0) {
        fprintf(stderr, "No steps found in position file.\n");
        exit(1);
    }
    printf("[Parent] Detected %d steps from input file.\n", num_steps);

    // Read all drones' positions from the file
    for (int i = 0; i < num_drones; i++) {
        read_positions(POSITION_FILE, i, MAX_LINE, MAX_POSITIONS);
    }

    pthread_t collision_thread;
    if (pthread_create(&collision_thread, NULL, collision_thread_func, NULL) != 0) {
        perror("pthread_create");
        exit(1);
    }

    // Fork drones
    for (int i = 0; i < num_drones; i++) {
        pid_t pid = fork();
        if (pid < 0) {
            perror("fork");
            exit(5);
        }

        if (pid == 0) {
            // Child process

            int steps = read_positions(POSITION_FILE, i, MAX_LINE, MAX_POSITIONS);

            // Open semaphore (must open in child too)
            char sem_name[64];
            snprintf(sem_name, sizeof(sem_name), "%s%d", SEM_NAME_BASE, i);
            sem_t *sem = sem_open(sem_name, 0);

            if (sem == SEM_FAILED) {
                perror("sem_open child");
                exit(6);
            }

            for (int step = 0; step < steps; step++) {
                shm[i] = drone_positions[i][step];
                printf("[Drone %d] wrote position: (%d, %d, %d) step %d\n",i, shm[i].x, shm[i].y, shm[i].z, step);
                sem_post(sem);
                sleep(1);
            }

            munmap(shm, sizeof(DronePosition) * num_drones);
            sem_close(sem);
            exit(0);
        }
        else {
            // Parent records drone pid
            drone_pids[i] = pid;
        }
    }

    // Main thread loop over steps
    for (int current_step = 0; current_step < num_steps; current_step++) {

        // Wait for all active drones to post semaphore
        for (int i = 0; i < num_drones; i++) {
            pthread_mutex_lock(&positions_mutex);
            int active = active_drones[i];
            pthread_mutex_unlock(&positions_mutex);
            if (!active) continue;
            sem_wait(drone_sems[i]);
        }

        // Lock and signal collision thread to check collisions
        pthread_mutex_lock(&positions_mutex);

        // Signal collision thread that positions updated
        pthread_cond_signal(&positions_updated);

        // Wait for collision thread to finish processing
        pthread_cond_wait(&collision_done, &positions_mutex);

        // Print positions of only active drones
        for (int i = 0; i < num_drones; i++) {
            if (active_drones[i]) {
                printf("[Parent] Drone %d position at step %d: (%d, %d, %d)\n",
                       i, current_step, shm[i].x, shm[i].y, shm[i].z);
            }
        }

        pthread_mutex_unlock(&positions_mutex);

        printf("-------------------------------\n");
    }

    // Wait for collision thread to finish if not already
    pthread_mutex_lock(&positions_mutex);
    pthread_cond_signal(&positions_updated);
    pthread_mutex_unlock(&positions_mutex);
    pthread_join(collision_thread, NULL);

    // Wait for all remaining alive drones
    for (int i = 0; i < num_drones; i++) {
        if (active_drones[i]) {
            waitpid(drone_pids[i], NULL, 0);
        }
    }

    printf("[Parent] All drones finished.\n");

    // Cleanup
    for (int i = 0; i < num_drones; i++) {
        if (active_drones[i]) sem_close(drone_sems[i]);
    }
    cleanup_semaphores(num_drones);

    munmap(shm, sizeof(DronePosition) * num_drones);
    close(fd);
    shm_unlink(SHM_NAME);

    return 0;
}
