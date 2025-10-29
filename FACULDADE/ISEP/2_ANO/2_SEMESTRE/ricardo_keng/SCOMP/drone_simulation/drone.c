#define _XOPEN_SOURCE 700

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <pthread.h>
#include "common.h"

SharedData *shared_data;

void error_exit(const char *msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}

int clamp(int val, int max) {
    if (val < 0) return 0;
    if (val >= max) return max - 1;
    return val;
}

void update_position(int *pos, const char *cmd, int steps, int grid_size) {
    if (strcmp(cmd, "LEFT") == 0) pos[0] = clamp(pos[0] - steps, grid_size);
    else if (strcmp(cmd, "RIGHT") == 0) pos[0] = clamp(pos[0] + steps, grid_size);
    else if (strcmp(cmd, "FORWARD") == 0) pos[1] = clamp(pos[1] + steps, grid_size);
    else if (strcmp(cmd, "BACKWARD") == 0) pos[1] = clamp(pos[1] - steps, grid_size);
    else if (strcmp(cmd, "UP") == 0) pos[2] = clamp(pos[2] + steps, grid_size);
    else if (strcmp(cmd, "DOWN") == 0) pos[2] = clamp(pos[2] - steps, grid_size);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <drone_id> <instruction_file>\n", argv[0]);
        return 1;
    }

    int drone_id = atoi(argv[1]);
    const char *instruction_file = argv[2];

    int shm_fd = shm_open("/mydrone_sim", O_RDWR, 0666);
    if (shm_fd == -1) error_exit("shm_open");
    shared_data = mmap(NULL, sizeof(SharedData), PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (shared_data == MAP_FAILED) error_exit("mmap");

    FILE *file = fopen(instruction_file, "r");
    if (!file) error_exit("instruction file open");

    int x, y, z;
    if (fscanf(file, "%d %d %d\n", &x, &y, &z) != 3) {
        fprintf(stderr, "Failed to read coordinates from file\n");
        fclose(file);
        exit(EXIT_FAILURE);
    }
    pthread_mutex_lock(&shared_data->mutex);
    shared_data->drone_positions[drone_id][0] = x;
    shared_data->drone_positions[drone_id][1] = y;
    shared_data->drone_positions[drone_id][2] = z;

    if (!shared_data->drone_active[drone_id]) {
        shared_data->drone_active[drone_id] = 1;
        shared_data->active_drones++;
    }
    pthread_mutex_unlock(&shared_data->mutex);

    char cmd[16];
    int steps;

    while (fscanf(file, "%s %d\n", cmd, &steps) == 2) {
        pthread_mutex_lock(&shared_data->mutex);
        // Wait for the time step to advance
        int current_step = shared_data->time_step;
        while (shared_data->time_step == current_step && !shared_data->terminate)
            pthread_cond_wait(&shared_data->cond, &shared_data->mutex);

        if (shared_data->terminate) {
            pthread_mutex_unlock(&shared_data->mutex);
            break;
        }

        update_position(shared_data->drone_positions[drone_id], cmd, steps, shared_data->grid_size);

        shared_data->drone_done_count++;
        pthread_cond_broadcast(&shared_data->cond);
        pthread_mutex_unlock(&shared_data->mutex);
    }

    fclose(file);
    munmap(shared_data, sizeof(SharedData));
    return 0;
}
