#ifndef MACH_MANAGER_H
#define MACH_MANAGER_H

#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_OPERATIONS 32
#define MAX_NAME_LEN 100
#define INITIAL_MACHINE_CAPACITY 10
#define STATUS_COUNT 3
#define MAX_BUFFER_LENGTH 100

typedef struct {
    char name[MAX_NAME_LEN];
    float min_temp;
    float max_temp;
    float min_humidity;
    float max_humidity;
    int circular_buffer_length;
    int median_window_length;
    float current_temp;
    float current_humidity;
    int id;
    char state[4];
    int operation_ids[MAX_OPERATIONS];
    int operation_count;
    time_t times_tamp;

    // Parâmetros de cálculo da mediana
    float temp_buffer[MAX_BUFFER_LENGTH];
    float hum_buffer[MAX_BUFFER_LENGTH];
    int buffer_index;
} Machine;

typedef struct {
    int id;
    char name[MAX_NAME_LEN];
} Operation;

extern Machine *machines;
extern int machine_count;
extern int machine_capacity;

int configure_serial_port(const char *port_name);
int find_operation_by_id(int operation_id);
void send_command_to_machine(int fd, const char *command);
int process_command(const char *op, int n, char *cmd);
void read_sensor_data(int fd, Machine* machine);
Machine* chose_machine(int id);
void check_alerts(Machine* machine);
void free_machines();

#endif
