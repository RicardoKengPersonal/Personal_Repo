#ifndef ASM_H
#define ASM_H

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define MAX_DRONES 100
#define MAX_COLISOES 100
#define STR_SIZE 256
#define PASTA_BASE "ficheiros_gerados/"

extern int num_drones;
extern int num_ticks;
extern int fim_simulacao; //flag para controlar thread de report
extern char caminho_base[128];

extern sem_t *sem_excl;
extern sem_t *sem_barrier_in;
extern sem_t *sem_barrier_out;
extern pthread_mutex_t mutex_colisoes;
extern pthread_cond_t cond_colisao;



void gerar_ficheiros_drones();
void* collision_thread_func(void* arg);
void* report_thread_func(void* arg);
void* environment_thread_func(void* arg);
void executar_drone(int id);


typedef struct {
    int id;
    int x, y, z;
    int active;
}Drone;

typedef struct {
    Drone drones[MAX_DRONES];
    int tick;
    int contador;//controlador barreira
    int n_elementos;//numero de drones na simulação + thread colisão + thread environment
    int vento_x, vento_y, vento_z;
}shared_memory;

typedef struct {
    int tick;
    int drone1_id;
    int drone2_id;
    int x, y, z;
} CollisionEvent;

extern CollisionEvent eventos_colisao[MAX_COLISOES];
extern int num_eventos_colisao; //usado por thread de report

extern shared_memory *shm;

#endif
