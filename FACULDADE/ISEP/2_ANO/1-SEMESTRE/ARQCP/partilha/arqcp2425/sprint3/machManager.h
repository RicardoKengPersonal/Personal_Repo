#ifndef MACH_MANAGER_H
#define MACH_MANAGER_H
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_OPERATIONS 32
#define MAX_NAME_LEN 100
#define INITIAL_MACHINE_CAPACITY 10 // Capacidade inicial do array dinâmico de máquinas
#define STATUS_COUNT 3
#define MAX_BUFFER_LENGTH 100 // Limite máximo para o buffer circular

// COMMIT DA BRUNA

typedef struct {
    char name[MAX_NAME_LEN];
    float temp_min;
    float temp_max;
    float hum_min;
    float hum_max;
    int circular_buffer_length;
    int median_window_length;
    float current_temp;
    float current_hum;
    int id;                 
    char state[4];
    int operation_ids[MAX_OPERATIONS];    
    int operation_count;        
    time_t timestamp;    
    
    // Parâmetros de cálculo da mediana
    float temp_buffer[MAX_BUFFER_LENGTH];
    float hum_buffer[MAX_BUFFER_LENGTH];
    int buffer_index;
} Machine;

// COMMIT DO RICARDO

typedef struct {
    int id;
    char name[MAX_NAME_LEN];
} Operation;

extern Machine *machines;  
extern int machine_count;  
extern int machine_capacity;      

// COMMIT DA BRUNA

int configureSerialPort(const char *portname);
int findOperationById(int operation_id);
void sendCommandToMachine(int fd, const char *command);
int processCommand(const char *op, int n, char *cmd);
void readSensorData(int fd, Machine* machine);
Machine* selectMachine(int id);
void checkAlerts(Machine* machine);
void freeMachines(); // Função para liberar a memória alocada para as máquinas

#endif
