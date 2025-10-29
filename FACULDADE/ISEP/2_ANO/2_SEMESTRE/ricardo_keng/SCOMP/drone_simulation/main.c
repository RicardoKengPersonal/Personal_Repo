#define _XOPEN_SOURCE 700

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <string.h>
#include <time.h>
#include "common.h"

SimulationConfig sim_config = {0};
SharedData *shared_data;
int shm_fd;

void error_exit(const char *msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}

void load_config(const char *filename, SimulationConfig *config) {
    FILE *file = fopen(filename, "r");
    if (!file) error_exit("open config");

    char key[64], eq[2], value[64];
    int width = 0, height = 0, depth = 0;

    while (fscanf(file, "%63s %1s %63s", key, eq, value) == 3) {
        if (strcmp(eq, "=") != 0) continue;
        if (strcmp(key, "max_drones") == 0) config->max_drones = atoi(value);
        else if (strcmp(key, "collision_threshold") == 0) config->max_collisions = atoi(value);
        else if (strcmp(key, "time_steps") == 0) config->max_steps = atoi(value);
        else if (strcmp(key, "grid_width") == 0) width = atoi(value);
        else if (strcmp(key, "grid_height") == 0) height = atoi(value);
        else if (strcmp(key, "grid_depth") == 0) depth = atoi(value);
    }

    fclose(file);
    int max_dim = width > height ? width : height;
    config->grid_size = max_dim > depth ? max_dim : depth;
}

void preload_initial_positions(int drone_id, const char *file_path) {
    FILE *file = fopen(file_path, "r");
    if (!file) error_exit("preload instruction file open");

    int x, y, z;
    if (fscanf(file, "%d %d %d", &x, &y, &z) != 3) {
        fprintf(stderr, "Failed to read initial coordinates from %s\n", file_path);
        fclose(file);
        exit(EXIT_FAILURE);
    }

    shared_data->drone_positions[drone_id][0] = x;
    shared_data->drone_positions[drone_id][1] = y;
    shared_data->drone_positions[drone_id][2] = z;
    fclose(file);
}

void* collision_detector(void* arg) {
    (void)arg;
    struct timespec ts = {0, 10 * 1000000}; // 10 ms
    int N = sim_config.grid_size;

    // Dynamically allocate 3D grid
    DroneList ***grid = malloc(N * sizeof(DroneList**));
    for (int x = 0; x < N; x++) {
        grid[x] = malloc(N * sizeof(DroneList*));
        for (int y = 0; y < N; y++) {
            grid[x][y] = calloc(N, sizeof(DroneList));
        }
    }

    while (1) {
        pthread_mutex_lock(&shared_data->mutex);

        while (!shared_data->collision_ready && !shared_data->terminate)
            pthread_cond_wait(&shared_data->collision_cond, &shared_data->mutex);

        if (shared_data->terminate) {
            pthread_mutex_unlock(&shared_data->mutex);
            break;
        }

        memset(shared_data->collision_reported, 0, sizeof(shared_data->collision_reported));

        int collisions = 0;

        // Clear the grid
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int z = 0; z < N; z++) {
                    grid[x][y][z].count = 0;
                }
            }
        }

        // Register drone positions into the grid
        for (int i = 0; i < sim_config.max_drones; i++) {
            if (!shared_data->drone_active[i]) continue;

            int x = shared_data->drone_positions[i][0];
            int y = shared_data->drone_positions[i][1];
            int z = shared_data->drone_positions[i][2];

            if (x < 0 || y < 0 || z < 0 || x >= N || y >= N || z >= N) {
                shared_data->drone_active[i] = 0;
                shared_data->active_drones--;
                continue;
            }

            DroneList *cell = &grid[x][y][z];
            if (cell->count < MAX_DRONES_PER_CELL) {
                cell->ids[cell->count++] = i;
            }
        }

        // Detect collisions
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int z = 0; z < N; z++) {
                    DroneList *cell = &grid[x][y][z];
                    if (cell->count > 1) {
                        for (int i = 0; i < cell->count; i++) {
                            for (int j = i + 1; j < cell->count; j++) {
                                int id1 = cell->ids[i];
                                int id2 = cell->ids[j];
                                if (!shared_data->collision_reported[id1][id2]) {
                                    printf("Collision detected at (%d, %d, %d): Drone %d and Drone %d\n",
                                           x, y, z, id1, id2);
                                    shared_data->collision_reported[id1][id2] = 1;
                                    shared_data->collision_reported[id2][id1] = 1;
                                    collisions++;
                                }
                            }
                        }
                    }
                }
            }
        }

        shared_data->collisions = collisions;

        if (collisions >= sim_config.max_collisions) {
            printf("Collision threshold reached: %d collisions. Terminating simulation.\n", collisions);
            shared_data->terminate = 1;
            pthread_cond_broadcast(&shared_data->cond);
            pthread_mutex_unlock(&shared_data->mutex);
            break;
        }

        shared_data->collision_ready = 0;

        pthread_mutex_unlock(&shared_data->mutex);

        nanosleep(&ts, NULL);
    }

    // Free the grid memory
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            free(grid[x][y]);
        }
        free(grid[x]);
    }
    free(grid);

    return NULL;
}


void* report_generator(void* arg) {
    (void)arg;
    struct timespec ts = {0, 10 * 1000000}; // 10 ms
    while (1) {
        pthread_mutex_lock(&shared_data->mutex);

        while (shared_data->drone_done_count < shared_data->active_drones && !shared_data->terminate)
            pthread_cond_wait(&shared_data->cond, &shared_data->mutex);

        if (shared_data->terminate) {
            pthread_mutex_unlock(&shared_data->mutex);
            break;
        }

        printf("\n--- Time Step %d ---\n", shared_data->time_step);
        for (int i = 0; i < sim_config.max_drones; i++) {
            if (shared_data->drone_active[i]) {
                printf("Drone %d: (%d, %d, %d)\n", i,
                       shared_data->drone_positions[i][0],
                       shared_data->drone_positions[i][1],
                       shared_data->drone_positions[i][2]);
            }
        }

        shared_data->drone_done_count = 0;
        pthread_cond_broadcast(&shared_data->cond);
        pthread_mutex_unlock(&shared_data->mutex);

        nanosleep(&ts, NULL);
    }
    return NULL;
}

int main() {
    load_config("simulation.conf", &sim_config);

    shm_fd = shm_open("/mydrone_sim", O_CREAT | O_RDWR, 0666);
    if (shm_fd == -1) error_exit("shm_open");
    if (ftruncate(shm_fd, sizeof(SharedData)) == -1) error_exit("ftruncate");

    shared_data = mmap(NULL, sizeof(SharedData), PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (shared_data == MAP_FAILED) error_exit("mmap");
    memset(shared_data, 0, sizeof(SharedData));

    shared_data->grid_size = sim_config.grid_size;

    // Initialize mutex
    pthread_mutexattr_t mattr;
    pthread_mutexattr_init(&mattr);
    pthread_mutexattr_setpshared(&mattr, PTHREAD_PROCESS_SHARED);
    pthread_mutex_init(&shared_data->mutex, &mattr);
    pthread_mutexattr_destroy(&mattr);

    // Initialize condition variables
    pthread_condattr_t cattr;
    pthread_condattr_init(&cattr);
    pthread_condattr_setpshared(&cattr, PTHREAD_PROCESS_SHARED);
    pthread_cond_init(&shared_data->cond, &cattr);
    pthread_cond_init(&shared_data->collision_cond, &cattr);
    pthread_condattr_destroy(&cattr);

    shared_data->collision_ready = 0;

    for (int i = 0; i < sim_config.max_drones; i++) {
        shared_data->drone_active[i] = 1;
        char path[256];
        snprintf(path, sizeof(path), "drone_instructions/drone_%d.txt", i);
        preload_initial_positions(i, path);
    }

    shared_data->active_drones = sim_config.max_drones;
    shared_data->drone_done_count = 0;
    shared_data->time_step = 0;
    shared_data->terminate = 0;

    printf("\n--- Initial Positions ---\n");
    for (int i = 0; i < sim_config.max_drones; i++) {
        printf("Drone %d: (%d, %d, %d)\n", i,
               shared_data->drone_positions[i][0],
               shared_data->drone_positions[i][1],
               shared_data->drone_positions[i][2]);
    }

    pthread_t collision_thread, report_thread;
    pthread_create(&collision_thread, NULL, collision_detector, NULL);
    pthread_create(&report_thread, NULL, report_generator, NULL);

    // Start drone processes
    for (int i = 0; i < sim_config.max_drones; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            char id[12], path[256];
            snprintf(id, sizeof(id), "%d", i);
            snprintf(path, sizeof(path), "drone_instructions/drone_%d.txt", i);
            execl("./drone", "./drone", id, path, NULL);
            perror("execl");
            exit(1);
        }
    }

    struct timespec ts = {0, 100 * 1000000}; // 100ms

    // Simulation main loop with correct synchronization
    for (int step = 0; step <= sim_config.max_steps; step++) {
        pthread_mutex_lock(&shared_data->mutex);

        shared_data->time_step = step;
        shared_data->drone_done_count = 0;

        // Signal drones to move
        pthread_cond_broadcast(&shared_data->cond);

        // Wait until all drones finish their move
        while (shared_data->drone_done_count < shared_data->active_drones && !shared_data->terminate) {
            pthread_cond_wait(&shared_data->cond, &shared_data->mutex);
            nanosleep(&ts, NULL);
        }

        // Signal collision detector to check for collisions
        shared_data->collision_ready = 1;
        pthread_cond_signal(&shared_data->collision_cond);

        pthread_mutex_unlock(&shared_data->mutex);

        nanosleep(&ts, NULL);

        if (shared_data->terminate) break;
    }

    // Graceful shutdown
    pthread_mutex_lock(&shared_data->mutex);
    shared_data->terminate = 1;
    pthread_cond_broadcast(&shared_data->cond);
    pthread_cond_signal(&shared_data->collision_cond);
    pthread_mutex_unlock(&shared_data->mutex);

    pthread_join(collision_thread, NULL);
    pthread_join(report_thread, NULL);

    for (int i = 0; i < sim_config.max_drones; i++)
        wait(NULL);

    munmap(shared_data, sizeof(SharedData));
    shm_unlink("/mydrone_sim");

    return 0;
}

