#ifndef COMMON_H
#define COMMON_H

#define GRID_SIZE 100
#define MAX_DRONES 100
#define MAX_DRONES_PER_CELL 16  // 👈 Add this

#include <pthread.h>

// 👇 Add this struct for collision grid cells
typedef struct {
    int ids[MAX_DRONES_PER_CELL];
    int count;
} DroneList;

typedef struct {
    int max_drones;
    int max_collisions;
    int max_steps;
    int grid_size;
} SimulationConfig;

typedef struct {
    pthread_mutex_t mutex;
    pthread_cond_t cond;

    int drone_positions[MAX_DRONES][3];
    int drone_active[MAX_DRONES];
    int active_drones;

    int grid_size;
    int time_step;
    int terminate;
    int collisions;
    int drone_done_count;
    int time_step_complete;
    int total_drones;

    int collision_reported[MAX_DRONES][MAX_DRONES]; // Tracks reported collisions

    // 👇 For Option A synchronization
    int collision_ready;
    pthread_cond_t collision_cond;
} SharedData;

#endif // COMMON_H
