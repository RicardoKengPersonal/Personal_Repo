// Compile with: gcc -o hybrid_sim hybrid_sim.c -lpthread -lrt

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <semaphore.h>
#include <signal.h>
#include <string.h>

#define SHM_NAME "/drone_shm"
#define SEM_NAME "/drone_sem"
#define MAX_DRONES 4
#define MAX_STEPS 5

typedef struct {
    int x, y, z;
} Position;

typedef struct {
    Position positions[MAX_DRONES];
    int steps[MAX_DRONES];
    pthread_mutex_t mutex;
    pthread_cond_t cond;
    int stop;
} SharedData;

SharedData *shared_data;
sem_t *sem;

// Signal handler
void handle_sigint(int sig) {
    shared_data->stop = 1;
}

// Drone process function
void drone_process(int id) {
    int shm_fd = shm_open(SHM_NAME, O_RDWR, 0666);
    SharedData *shm = mmap(NULL, sizeof(SharedData), PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);

    for (int i = 0; i < MAX_STEPS && !shm->stop; i++) {
        sem_wait(sem);

        pthread_mutex_lock(&shm->mutex);
        shm->positions[id].x = rand() % 100;
        shm->positions[id].y = rand() % 100;
        shm->positions[id].z = rand() % 100;
        shm->steps[id] = i + 1;
        pthread_cond_broadcast(&shm->cond);
        pthread_mutex_unlock(&shm->mutex);

        sem_post(sem);
        sleep(1);
    }

    munmap(shm, sizeof(SharedData));
    exit(0);
}

// Parent thread for monitoring
void *monitor_thread(void *arg) {
    while (!shared_data->stop) {
        pthread_mutex_lock(&shared_data->mutex);
        pthread_cond_wait(&shared_data->cond, &shared_data->mutex);

        printf("== Monitor Thread: Current Positions ==\n");
        for (int i = 0; i < MAX_DRONES; i++) {
            printf("Drone %d: (%d, %d, %d) Step: %d\n", i,
                   shared_data->positions[i].x,
                   shared_data->positions[i].y,
                   shared_data->positions[i].z,
                   shared_data->steps[i]);
        }
        pthread_mutex_unlock(&shared_data->mutex);
    }
    return NULL;
}

int main() {
    signal(SIGINT, handle_sigint);

    // Create shared memory
    int shm_fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, 0666);
    ftruncate(shm_fd, sizeof(SharedData));
    shared_data = mmap(NULL, sizeof(SharedData), PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    memset(shared_data, 0, sizeof(SharedData));

    // Initialize mutex and cond variable
    pthread_mutexattr_t m_attr;
    pthread_mutexattr_init(&m_attr);
    pthread_mutexattr_setpshared(&m_attr, PTHREAD_PROCESS_SHARED);
    pthread_mutex_init(&shared_data->mutex, &m_attr);

    pthread_condattr_t c_attr;
    pthread_condattr_init(&c_attr);
    pthread_condattr_setpshared(&c_attr, PTHREAD_PROCESS_SHARED);
    pthread_cond_init(&shared_data->cond, &c_attr);

    // Create named semaphore
    sem = sem_open(SEM_NAME, O_CREAT, 0666, 1);

    // Fork drone processes
    for (int i = 0; i < MAX_DRONES; i++) {
        pid_t pid = fork();
        if (pid == 0) drone_process(i);
    }

    // Create monitoring thread
    pthread_t monitor;
    pthread_create(&monitor, NULL, monitor_thread, NULL);

    // Wait for drones to finish
    for (int i = 0; i < MAX_DRONES; i++) wait(NULL);

    // Cleanup
    shared_data->stop = 1;
    pthread_cond_broadcast(&shared_data->cond);
    pthread_join(monitor, NULL);

    munmap(shared_data, sizeof(SharedData));
    shm_unlink(SHM_NAME);
    sem_close(sem);
    sem_unlink(SEM_NAME);

    printf("Simulation ended cleanly.\n");
    return 0;
}
