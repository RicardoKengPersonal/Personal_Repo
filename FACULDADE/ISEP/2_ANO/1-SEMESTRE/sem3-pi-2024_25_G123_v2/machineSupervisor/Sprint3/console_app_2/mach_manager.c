#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include "mach_manager.h"
#include "asm.h"
#include <termios.h>

Machine *machines = NULL;
int machine_count = 0;
int machine_capacity = INITIAL_MACHINE_CAPACITY;

Machine* chose_machine(int id) {

  for (int i = 0; i < machine_count; i++){

      if(machines[i].id == id){
        return &machines[i];
      }
  }
  return NULL;
}

int configure_serial_port(const char *port_name) {

    int fd = open(port_name, O_RDWR | O_NOCTTY | O_NDELAY);

    if (fd == -1) {
        perror("open_port: Unable to open port");
        return -1;
    }

    struct termios options;

    cfsetospeed(&options, B9600);
    cfsetispeed(&options, B9600);

    options.c_cflag = (options.c_cflag & ~CSIZE) | CS8;
    options.c_cflag &= ~PARENB;
    options.c_cflag &= ~CSTOPB;
    options.c_cflag |= CREAD | CLOCAL;

    options.c_lflag &= ~ICANON;
    options.c_lflag &= ~ECHO;
    options.c_lflag &= ~ECHOE;
    options.c_lflag &= ~ISIG;

    options.c_iflag &= ~(IXON | IXOFF | IXANY);
    options.c_oflag &= ~OPOST;

    if (tcsetattr(fd, TCSANOW, &options) != 0) {
        perror("ERROR in configuring port");
        exit(EXIT_FAILURE);
    }
    return fd;
}


void initialize_buffer(float *buffer, int len, float value) {
    for (int i = 0; i < len; i++) {
      buffer[i] = value;
    }
}

void executarInstrucoesFicheiro(float* buffer, int length, float value) {  // para preencher buffers com um valor
    for (int i = 0; i < length; i++) { // loop percorre todas as posições do buffer
        buffer[i] = value; // cada posição do buffer é preenchida com o valor
    }
}

void checkTempHum(Machine* currentMachine) {
    if (currentMachine->current_temp < currentMachine->min_temp) { // Verifica se a temperatura atual está abaixo do limite mínimo
        printf("Temperatura abaixo do limite mínimo (%.2f). Temperatura atual: %.2f\n",
               currentMachine->min_temp, currentMachine->current_temp); // Mensagem de alerta
    }

    else if (currentMachine->current_temp > currentMachine->max_temp) {       // Verifica se a temperatura atual está acima do limite máximo
        printf("Temperatura acima do limite máximo (%.2f). Temperatura atual: %.2f\n",
               currentMachine->max_temp, currentMachine->current_temp);       // Mensagem de alerta
    }

    if (currentMachine->current_humidity < currentMachine->min_humidity) {        // verifica se a humidade atual está abaixo do limite mínimo
        printf("Humidade abaixo do limite mínimo (%.2f). Humidade atual: %.2f\n",
               currentMachine->min_humidity, currentMachine->current_humidity);
    }

    else if (currentMachine->current_humidity > currentMachine->max_humidity) {       // verifica se a humidade atual está acima do limite máximo
        printf("Humidade acima do limite máximo (%.2f). Humidade atual: %.2f\n",
               currentMachine->max_humidity, currentMachine->current_humidity);       // mensagem de alerta
    }
}

void send_command_to_machine(int fd, const char *command) {
    write(fd, command, strlen(command));
    write(fd, "\n", 1);
}

// Função para processar o comando
int process_command(const char *op, int n, char *cmd) {
    return format_command((char *)op, n, cmd);
}

// Função para ler os dados do sensor
void read_sensor_data(int fd, Machine* machine) {
    char buffer[200];
    int n = read(fd, buffer, sizeof(buffer) - 1);

    if (n > 0) {
        buffer[n] = '\0';

        char unit[20];
        int value;

        // Atualiza temperatura
        if (extract_data(buffer, "TEMP", unit, &value)) {
            machine->current_temp = value;
            machine->temp_buffer[machine->buffer_index] = value;
        }

        // Atualiza humidade
        if (extract_data(buffer, "HUM", unit, &value)) {
            machine->current_humidity = value;
            machine->hum_buffer[machine->buffer_index] = value;
        }

		// Inicializa buffers se estiverem vazios
        if (machine->buffer_index == 0) {
            initialize_buffer(machine->temp_buffer, machine->circular_buffer_length, machine->current_temp);
            initialize_buffer(machine->hum_buffer, machine->circular_buffer_length, machine->current_humidity);
        }

        // Atualiza índice do buffer circular
        machine->buffer_index = (machine->buffer_index + 1) % machine->circular_buffer_length;

        // Calcula a mediana para temperatura usando a janela configurada
        int temp_values[MAX_BUFFER_LENGTH];
        int start_index = (machine->buffer_index - machine->median_window_length + machine->circular_buffer_length) % machine->circular_buffer_length;
        for (int i = 0; i < machine->median_window_length; i++) {
            temp_values[i] = (int)machine->temp_buffer[(start_index + i) % machine->circular_buffer_length];
        }
        int temp_median = 0;
        median(temp_values, machine->median_window_length, &temp_median);

        // Calcula a mediana para umidade usando a janela configurada
        int hum_values[MAX_BUFFER_LENGTH];
        for (int i = 0; i < machine->median_window_length; i++) {
            hum_values[i] = (int)machine->hum_buffer[(start_index + i) % machine->circular_buffer_length];
        }
        int hum_median = 0;
        median(hum_values, machine->median_window_length, &hum_median);

        // Apresenta os valores
        printf("Máquina %d - Temperatura Atual: %.2f, Mediana Temp: %d - Humidade Atual: %.2f, Mediana Hum: %d\n",
            machine->id, machine->current_temp, temp_median, machine->current_humidity, hum_median);
    }
    //checkAlerts(machine);
}


