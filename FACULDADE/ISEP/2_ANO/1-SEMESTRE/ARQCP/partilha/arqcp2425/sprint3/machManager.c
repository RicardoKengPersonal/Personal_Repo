#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>
#include <time.h>
#include "machManager.h"
#include "file.h"

// COMMIT DO TIAGO

// Selecionar Máquina
Machine* selectMachine(int id) {
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id) {
            return &machines[i];
        }
    }
    return NULL;  // Retorna NULL se a máquina não for encontrada
}

// COMMIT DO TIAGO

// Configuração da porta serial
int configureSerialPort(const char *port) {
    int fd = open(port, O_RDWR | O_NOCTTY | O_SYNC);
    if (fd < 0) {
        perror("Erro ao abrir a porta serial");
        exit(EXIT_FAILURE);
    }

    struct termios tty;
    if (tcgetattr(fd, &tty) != 0) {
        perror("Erro ao obter atributos da porta serial");
        exit(EXIT_FAILURE);
    }

    cfsetospeed(&tty, B9600);
    cfsetispeed(&tty, B9600);

    tty.c_cflag = (tty.c_cflag & ~CSIZE) | CS8; 
    tty.c_cflag &= ~PARENB;                    
    tty.c_cflag &= ~CSTOPB;                    
    tty.c_cflag |= CREAD | CLOCAL;             

    tty.c_lflag &= ~ICANON;
    tty.c_lflag &= ~ECHO;   
    tty.c_lflag &= ~ECHOE;
    tty.c_lflag &= ~ISIG;   

    tty.c_iflag &= ~(IXON | IXOFF | IXANY);
    tty.c_oflag &= ~OPOST;

    if (tcsetattr(fd, TCSANOW, &tty) != 0) {
        perror("Erro ao configurar a porta serial");
        exit(EXIT_FAILURE);
    }

    return fd;
}

// COMMIT DA BRUNA

// Função para inicializar buffers com um valor
void initializeBuffer(float* buffer, int length, float value) {
    for (int i = 0; i < length; i++) {
        buffer[i] = value;
    }
}

// COMMIT DO TIAGO

// Função para mandar o comando para a Máquina
void sendCommandToMachine(int fd, const char *command) {
    write(fd, command, strlen(command));
    write(fd, "\n", 1);
}

// COMMIT DO TIAGO

// Função para processar o comando
int processCommand(const char *op, int n, char *cmd) {
    return format_command((char *)op, n, cmd); // Chama a função Assembly
}

// COMMIT DO TIAGO

// Função para ler os dados do sensor
void readSensorData(int fd, Machine* machine) {
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
            machine->current_hum = value;
            machine->hum_buffer[machine->buffer_index] = value;
        }

		// Inicializa buffers se estiverem vazios
        if (machine->buffer_index == 0) {
            initializeBuffer(machine->temp_buffer, machine->circular_buffer_length, machine->current_temp);
            initializeBuffer(machine->hum_buffer, machine->circular_buffer_length, machine->current_hum);
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
            machine->id, machine->current_temp, temp_median, machine->current_hum, hum_median);
    }

    // Função de verificação de alertas
    checkAlerts(machine);
}

// COMMIT DA BRUNA

// Função para verificar se a temperatura e a humidade estão dentro dos limites
void checkAlerts(Machine* machine) {
    if (machine->current_temp < machine->temp_min) {
        printf("ALERTA! A temperatura está abaixo do limite mínimo (%.2f). Temperatura atual: %.2f\n", machine->temp_min, machine->current_temp);
    } else if (machine->current_temp > machine->temp_max) {
        printf("ALERTA! A temperatura está acima do limite máximo (%.2f). Temperatura atual: %.2f\n", machine->temp_max, machine->current_temp);
    }

    if (machine->current_hum < machine->hum_min) {
        printf("ALERTA! A humidade está abaixo do limite mínimo (%.2f). Humidade atual: %.2f\n", machine->hum_min, machine->current_hum);
    } else if (machine->current_hum > machine->hum_max) {
        printf("ALERTA! A humidade está acima do limite máximo (%.2f). Humidade atual: %.2f\n", machine->hum_max, machine->current_hum);
    }
}
