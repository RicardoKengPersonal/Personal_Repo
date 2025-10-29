#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include "mach_manager.h"

#define MAX_NAME_LENGTH 10
#define MAX_FILENAME_LENGTH 150

void resize_machines();

void show_available_operations();

int compare_machines_by_id(const void *first, const void *second);

void show_available_machines();

int find_machine_by_id(int id);

int machine_exists(int id);

int isValidInteger(const char *input);

int is_decimal_number(const char *str);

void buffer_clear_input();

void load_configuration_file();

void machine_instruct(int fd);

void allocate_memory_for_machines();

void free_machine_memory();

void add_machine();

void remove_machine();

void assign_operation();

void check_machine_status();

void export_machine_operations();

void show_machine_menu(Machine *machine);

void control_machine(int fd, Machine *machine);

void control_machines(int fd);

void process_command_for_machine(Machine *machine, char *command, int fd);

int is_char_number(const char *str);

int main() {
    const char *serial_port = "/dev/ttyS0";

    int fd = configure_serial_port(serial_port);

    allocate_memory_for_machines();

    int choice;

    do {
        printf("\n=== Menu ===\n");

        printf("1. USAC12 - Load Configuration from a file \n");
        printf("2. USAC13 - Export Machine Configurations \n");
        printf("3. USAC14 - Add Machine \n");
        printf("4. USAC14 - Remove Machine \n");
        printf("5. USAC14 - Verify Machine State \n");
        printf("6. USAC15 - Assign an Operation to a Machine \n");
        printf("7. USAC16/USAC18 - Manage Machines \n");
        printf("8. USAC17/USAC18 - Instruct  Machines \n");
        printf("0. Exit\n");

        printf("Option: ");

        scanf("%d", &choice);

        switch (choice) {
            case 1:
                load_configuration_file();
                break;

            case 2:
                export_machine_operations();
                break;

            case 3:
                add_machine();
                break;

            case 4:
                remove_machine();
                break;

            case 5:
                check_machine_status();
                break;

            case 6:
                assign_operation();
                break;

            case 7:
                control_machines(fd);
                break;

            case 8:
                machine_instruct(fd);
                break;

            case 0:
                printf("Closing...\n");
                break;

            default:
                printf("Invalid Option! Try again.\n");
        }
    } while (choice != 0);

    free_machine_memory();

    return 0;
}

Operation operations[] = {
    {0, "CUT"},
    {1, "DRILL"},
    {2, "TIGHTEN"},
    {3, "BUFF"},
    {4, "COAT"},
    {5, "WRAP"},
    {6, "REPAIR"},
    {7, "ASSEMBLE"},
    {8, "SPRAY"},
    {9, "PRESS"},
    {10, "DISMANTLE"},
    {11, "CURE"},
    {12, "CHILL"},
    {13, "WARM"},
    {14, "SEAL"},
    {15, "STOCK"},
    {16, "ALIGN"},
    {17, "SHAPE"},
    {18, "STAMP"},
    {19, "INSPECT"},
    {20, "BEND"},
    {21, "CLEAN"},
    {22, "VERIFY"},
    {23, "MODIFY"},
    {24, "LOAD"},
    {25, "UNLOAD"},
    {26, "ETCH"},
    {27, "GRIND"},
    {28, "MOVE"},
    {29, "CUTOUT"},
    {30, "LABEL"},
    {31, "PACK"}
};

// Função para redimensionar o array de máquinas
void resize_machines() {
    machine_capacity <<= 1; // Double the capacity through a shift to the left

    machines = (Machine *) realloc(machines, machine_capacity * sizeof(Machine));

    if (machines == NULL) {
        printf("Error reallocating memory.\n");
        exit(1); // Exit program
    }
}

// Função para mostrar as operações disponíveis no sistema
void show_available_operations() {
    printf("\nAvailable operations:\n");

    int op_flag = 0; // Flag to check if Operation exists

    for (int i = 0; i <= 31; i++) {
        if (strlen(operations[i].name) > 0) {
            // If operation name is null or empty , then operation doesn't exist
            printf("ID: %d, Operation Description: %s\n", operations[i].id, operations[i].name);
            op_flag = 1; // Update flag
        }
    }

    if (!op_flag) {
        printf("No operations available.\n");
    }
}

// Função para comparar máquinas por ID
int compare_machines_by_id(const void *first, const void *second) //Void because of qsort
{
    Machine *m1 = (Machine *) first; // Cast the first void pointer to a Machine pointer

    Machine *m2 = (Machine *) second; // Cast the second void pointer to a Machine pointer

    // - If m1->id < m2->id, the result is negative (m1 comes first)
    // - If m1->id == m2->id, the result is zero (both are equal)
    // - If m1->id > m2->id, the result is positive (m2 comes first)
    return (m1->id - m2->id);
}

void show_available_machines() {
    qsort(machines, machine_count, sizeof(Machine), compare_machines_by_id);
    // Used qsort instead of creating a bubble sort

    printf("Available machines:\n");

    for (int i = 0; i < machine_count; i++) {
        const char *status;

        // compares machine[i].state with "ON", if equal then result = 0
        if (strcmp(machines[i].state, "ON") == 0) {
            status = "Ready to operate (ON)";
        } else if (strcmp(machines[i].state, "OFF") == 0) {
            // compares machine[i].state with "OFF", if equal then result = 0
            status = "Offline";
        } else if (strcmp(machines[i].state, "OP") == 0) {
            // compares machine[i].state with "OP", if equal then result = 0
            status = "Operating";
        } else {
            status = "Unknown (Could be maintenance or broken)";
        }

        printf("Machine ID: %d, Name: %s, Status: %s", machines[i].id, machines[i].name, status);

        if (machines[i].operation_count > 0) {
            printf("\n\t Assigned operations: ");

            for (int k = 0; k < machines[i].operation_count; k++) {
                int id_op = machines[i].operation_ids[k];
                int index_op = find_operation_by_id(id_op);

                if (index_op != -1) {
                    printf("%d -> %s", id_op, operations[index_op].name);

                    if (k < machines[i].operation_count - 1) {
                        printf(", ");
                    }
                }
            }
        } else {
            printf("\n\tNo operations assigned.");
        }
        printf("\n\n");
    }
}

int find_machine_by_id(const int id) {
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id) {
            return i; // If found, returns the index
        }
    }
    return -1; // Return fail
}

// Função para encontrar operação pelo ID
int find_operation_by_id(int operation_id) {
    for (int i = 0; i < 32; i++) {
        if (operations[i].id == operation_id) {
            return i; // if found, returns the index
        }
    }
    return -1; // Return fail
}

int machine_exists(int id) {
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id) {
            return 1; // Returns 1, meaning it doesnt exist
        }
    }
    return 0; // Return success meaning it doesn't exist
}

int is_valid_integer(const char *input) {
    // verificar se é um n valido
    char *ptr;
    strtol(input, &ptr, 10);
    return *ptr == '\0' && input != ptr; // verifica se  não há mais caracteres restantes
}

void buffer_clear_input() {
    // limpar o buffer de entrada
    // Remove todos os caracteres até a nova linha
    while (getchar() != '\n');
}

void load_configuration_file() {
    printf("\n");
    char filename[100]; // nome do arquivo

    printf("Nome do ficheiro de configuração ou 'C' para cancelar: ");
    // solicitar ao usuário o nome do arquivo ou um comando para cancelar
    scanf("%99s", filename); // limite de leitura para evitar overflows
    buffer_clear_input(); // limpar o buffer

    if (strcasecmp(filename, "C") == 0) {
        // verifica se a operaçãofoi cancelada
        printf("Operação Cancelada.\n");
        return;
    }

    FILE *configFile = fopen(filename, "r"); // começar leitura
    if (configFile == NULL) {
        // caso o ficheiro não for encontrado erro
        printf("Erro ao encontrar ficheiro %s.", filename);
        return;
    }

    // variáveis temporárias para processar as informações da máquina
    Machine current_machine;
    char inputLine[256]; // Armazena cada linha do arquivo

    printf("\n");

    // Processa cada linha do arquivo
    while (fgets(inputLine, sizeof(inputLine), configFile)) {
        // Ignora linhas em branco
        if (inputLine[0] == '\n') {
            continue;
        }

        // divide a linha com delimitador ";"
        char *token = strtok(inputLine, ";");

        // Lê e processa o ID da máquina
        if (token != NULL) current_machine.id = atoi(token); // Converte o ID para inteiro

        // verifica se a máquina com este ID já foi carregada
        if (machine_exists(current_machine.id)) {
            printf(" Máquina com ID %d já registrada.", current_machine.id);
            continue; // Passa para o próximo registro
        }

        // Valida se o ID da máquina é positivo
        if (current_machine.id <= 0) {
            printf("ID inválido (%d). O ID apresentado é negativo.", current_machine.id);
            continue; // passa para o próximo registro
        }

        // lê o nome da máquina
        token = strtok(NULL, ";");
        if (token != NULL) strncpy(current_machine.name, token, MAX_NAME_LENGTH); // copia o nome da máquina

        // lê a temperatura mínima e valida
        token = strtok(NULL, ";");
        if (token != NULL) current_machine.min_temp = atof(token); // converte a temperatura mínima

        // lê a temperatura máxima e valida
        token = strtok(NULL, ";");
        if (token != NULL) current_machine.max_temp = atof(token); // converte a temperatura máxima

        // Verifica se a temperatura máxima é maior que a mínima
        if (current_machine.max_temp <= current_machine.min_temp) {
            printf("Temperatura máxima (%.2f) deve ser maior que a mínima (%.2f) para o ID %d.\n",
                   current_machine.max_temp, current_machine.min_temp, current_machine.id);
            continue; // passa para o próximo registro
        }

        // humidades

        // Lê a umidade mínima e valida
        token = strtok(NULL, ";");
        if (token != NULL) current_machine.min_humidity = atof(token); // converte a humidade mínima

        // Lê a humidade máxima e valida
        token = strtok(NULL, ";");
        if (token != NULL) current_machine.max_humidity = atof(token); // converte a umidade máxima

        // Verifica se a umidade máxima é maior que a mínima
        if (current_machine.max_humidity <= current_machine.min_humidity) {
            printf("Humidade máxima (%.2f) deve ser maior que a mínima (%.2f) para o ID %d.\n",
                   current_machine.max_humidity, current_machine.min_humidity, current_machine.id);
            continue; // passa para o próximo registro
        }

        // verificar se as humidades não são negativas
        if (current_machine.min_humidity < 0 || current_machine.max_humidity < 0) {
            printf("Valores da humidade mínima (%.2f) e máxima (%.2f) não podem ser negativas para o ID %d.\n",
                   current_machine.min_humidity, current_machine.max_humidity, current_machine.id);
            continue; // passa para o próximo registro
        }

        token = strtok(NULL, ";"); // Lê o comprimento do buffer circular
        if (token != NULL) current_machine.circular_buffer_length = atoi(token); // Converte o comprimento do buffer

        token = strtok(NULL, ";"); // Lê o comprimento da janela de mediana

        if (token != NULL) current_machine.median_window_length = atoi(token);
        // Converte o comprimento da janela de mediana

        // verifica se o comprimento da janela de mediana é menor que o do buffer circular
        if (current_machine.median_window_length >= current_machine.circular_buffer_length) {
            printf(
                "Comprimento da janela da mediana (%d) tem de ser menor que o comprimento do buffer circular (%d) para o ID %d.\n",
                current_machine.median_window_length, current_machine.circular_buffer_length, current_machine.id);
            continue; // passar para o próximo registro
        }


        // inicializar o estado da máquina, temperatura e humidade
        strncpy(current_machine.state, "ON", sizeof(current_machine.state) - 1); // estado inicial "ON"
        current_machine.state[sizeof(current_machine.state) - 1] = '\0'; // terminação nula
        current_machine.current_temp = current_machine.min_temp; // iniciar com temperatura com a mínima
        current_machine.current_humidity = current_machine.min_humidity; // iniciar com a humidade mínima
        current_machine.operation_count = 0; // iniciar o contador das operacoes


        for (int i = 0; i < MAX_OPERATIONS; i++) {
            // inicializar os IDs das operações com -1
            current_machine.operation_ids[i] = -1;
        }

        // Lê as operações associadas à máquina
        token = strtok(NULL, ";");
        while (token != NULL && current_machine.operation_count < MAX_OPERATIONS) {
            int operationId = atoi(token); // converte o ID da operação

            if (operationId != 0) {
                // vai ignorar o valor 0, que significa sem operação
                current_machine.operation_ids[current_machine.operation_count++] = operationId;
            }
            token = strtok(NULL, ";"); // Avança para o próximo token (operações)
        }

        // Adiciona a máquina à lista de máquinas carregadas
        machines[machine_count] = current_machine;
        machine_count++;

        // mostrar informações sobre a máquina carregada
        printf("A maquina foi carregada: ID: %d, Nome: %s, Temp: %.2f-%.2f°C, Hum: %.2f-%.2f%%,",
               current_machine.id, current_machine.name, current_machine.min_temp, current_machine.max_temp,
               current_machine.min_humidity, current_machine.max_humidity);

        // exibe as operações atribuídas, se existirem
        if (current_machine.operation_count > 0) {
            printf(" Operações que foram atribuídas: ");

            for (int j = 0; j < current_machine.operation_count; j++) {
                int opId = current_machine.operation_ids[j];
                int operationIndex = find_operation_by_id(opId);

                if (operationIndex != -1) {
                    printf("%d - %s", opId, operations[operationIndex].name); // ID e o nome da operação
                    if (j < current_machine.operation_count - 1) {
                        printf(", "); // Adicionar vírgula entre as operações
                    }
                }
            }
        } else {
            printf(" Sem operações atribuídas");
        }

        printf("\n");
    }

    fclose(configFile);
    printf("Sucesso.\n");
}

void machine_instruct(int fd) {
    //  ler o ficheiro de configuração e processar comandos para as máquinas
    printf("\n");

    // verifica se há máquinas registradas
    if (machine_count == 0) {
        printf("Sem máquinas registadas.\n");
        return; // sai da função se não houver máquinas registradas
    }

    // solicitar o nome do arquivo de configuração
    char filename[100];

    printf("Inserir o nome do arquivo de configuração. Imprima 'C' para cancelar: ");
    scanf("%99s", filename); // restringe a entrada a 99 caracteres para evitar buffer overflow

    buffer_clear_input(); // Limpar o buffer

    if (strcasecmp(filename, "C") == 0) {
        printf("Carregamento do arquivo cancelado.\n");
        return;
    }

    // abre o ficheiro para leitura
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("Erro a encontrar o arquivo %s.", filename);
        return; // se o arquivo não for encontrado, mostra erro e sai
    }

    // Ler o ficheiro linha por linha
    char line[256];
    while (fgets(line, sizeof(line), file)) {
        // rremove o caractere da nova linha no final da linha
        line[strcspn(line, "\n")] = '\0';
        char *machine_id_str = strtok(line, ";"); // separar o nome da maquina
        if (machine_id_str == NULL) {
            printf("Inválida: %s\n", line); // Caso o ID da máquina não seja válido
            continue; // próxima linha
        }

        int machine_id = atoi(machine_id_str); // Converte o ID da máquina de string para inteiro

        // procura a máquina com o ID fornecido
        int machine_index = find_machine_by_id(machine_id); // Função para encontrar a máquina com o ID

        // Se a máquina não for encontrada mensagem de erro
        if (machine_index == -1) {
            printf("\nMáquina ID %d não registada.\n", machine_id);
            continue; //  próxima linha de comando
        }

        printf("\nInstruções da Máquina com ID %d.", machine_id);
        // Se a máquina for encontrada, exibe uma mensagem de instruções para a máquina

        // pega a máquina correspondente ao ID
        Machine *machine = &machines[machine_index];

        // Pega o restante dos comandos na linha após o ID da máquina
        char *remaining_commands = strtok(NULL, "");
        if (remaining_commands == NULL) {
            printf("Não existem comandos para a máquina %d.\n", machine_id);
            continue; // Se não houver comandos,  próxima linha
        }

        // Contador para o número de instruções
        int count_instruction = 0;
        // Separa os blocos de comandos utilizando ";" como delimitador
        char *command_block = strtok(remaining_commands, ";");


        while (command_block != NULL) {
            printf("\nInstrução %d: \n", ++count_instruction);
            process_command_for_machine(machine, command_block, fd);
            command_block = strtok(NULL, ";");
        }
    }
    fclose(file); // Fecha o ficheiro após a leitura
}

void allocate_memory_for_machines() {
    // alocar memória para o array de máquinas
    // memória para o array de máquinas, com a capacidade definida pela variável 'machine_capacity'
    machines = (Machine *) malloc(machine_capacity * sizeof(Machine));
    // Aloca espaço suficiente para 'machine_capacity' máquinas
    if (machines == NULL) {
        printf("Falha ao alocar memória para armazenar máquinas.\n");
        exit(1); // Caso ocorra um erro na alocação, o programa é encerrado com err
    }
}

void free_machine_memory() {
    // liberar a memória alocada quando o programa terminar
    // Libera a memória alocada para o array de máquinas
    free(machines);
}

int is_char_number(const char *str) {
    char *p;
    strtol(str, &p, 10);
    return *p == '\0' && str != p;
}

int is_decimal_number(const char *str) {
    char *p;
    strtof(str, &p);
    return *p == '\0' && str != p;
}

// Função para adicionar a máquina ao sistema
void add_machine() {
    printf("\n");

    // Verifica se o array de máquinas precisa ser redimensionado
    if (machine_count >= machine_capacity) {
        resize_machines();
    }

    Machine machineToAdd;

    // Inicializa o array operation_ids com -1 e o contador de operações
    for (int operationIndex = 0; operationIndex < MAX_OPERATIONS; operationIndex++) {
        machineToAdd.operation_ids[operationIndex] = -1;
    }
    machineToAdd.operation_count = 0;

    char userInput[100];
    int isValid = 0;
    int machineId;

    // Solicita o ID da nova máquina
    printf(
        "Digite o ID da nova máquina (letras/símbolos inseridos após um número não serão contabilizados ex: 12h -> 12): ");

    while (1) {
        if (scanf("%d", &machineId) != 1 || machineId < 0) {
            printf("Por favor, insira um número não negativo para o ID: ");
            while (getchar() != '\n'); // Limpa o buffer de entrada
        } else {
            int doesIdExist = 0;
            for (int existingMachineIndex = 0; existingMachineIndex < machine_count; existingMachineIndex++) {
                if (machines[existingMachineIndex].id == machineId) {
                    doesIdExist = 1;
                    break;
                }
            }

            if (doesIdExist) {
                printf("ALERTA: O ID %d já existe. Por favor, insira um ID único.\n", machineId);
            } else {
                machineToAdd.id = machineId;
                while (getchar() != '\n'); // Limpa caracteres residuais no buffer
                break;
            }
        }
    }

    // Solicita o nome da máquina
    printf("Digite o nome da máquina: ");
    fgets(machineToAdd.name, sizeof(machineToAdd.name), stdin);
    machineToAdd.name[strcspn(machineToAdd.name, "\n")] = 0;

    // Solicita e valida temperaturas
    isValid = 0;
    do {
        printf("Digite a temperatura mínima: ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_decimal_number(userInput)) {
            printf("ALERTA: Entrada inválida para temperatura mínima. Insira um número.\n");
            continue;
        }
        machineToAdd.min_temp = strtof(userInput, NULL);

        printf("Digite a temperatura máxima: ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_decimal_number(userInput)) {
            printf("ALERTA: Entrada inválida para temperatura máxima. Insira um número.\n");
            continue;
        }
        machineToAdd.max_temp = strtof(userInput, NULL);

        if (machineToAdd.max_temp < machineToAdd.min_temp) {
            printf("ALERTA: A temperatura máxima deve ser maior ou igual à mínima.\n");
        } else {
            isValid = 1;
        }
    } while (!isValid);

    // Solicita e valida humidades
    isValid = 0;
    do {
        printf("Digite a humidade mínima (0-100%%): ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_decimal_number(userInput)) {
            printf("ALERTA: Entrada inválida para humidade mínima. Insira um número.\n");
            continue;
        }
        machineToAdd.min_humidity = strtof(userInput, NULL);

        printf("Digite a humidade máxima (0-100%%): ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_decimal_number(userInput)) {
            printf("ALERTA: Entrada inválida para humidade máxima. Insira um número.\n");
            continue;
        }
        machineToAdd.max_humidity = strtof(userInput, NULL);

        if (machineToAdd.max_humidity < machineToAdd.min_humidity || machineToAdd.max_humidity > 100 || machineToAdd.
            min_humidity < 0) {
            printf("ALERTA: A humidade máxima deve estar entre 0 e 100 e ser maior ou igual à mínima.\n");
        } else {
            isValid = 1;
        }
    } while (!isValid);

    // Solicita e valida comprimento do buffer circular
    isValid = 0;
    do {
        printf("Digite o comprimento do buffer circular: ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_char_number(userInput)) {
            printf("ALERTA: Entrada inválida. Insira um número inteiro positivo.\n");
            continue;
        }

        machineToAdd.circular_buffer_length = strtol(userInput, NULL, 10);

        if (machineToAdd.circular_buffer_length <= 0) {
            printf("ALERTA: O comprimento do buffer deve ser um número positivo.\n");
        } else {
            isValid = 1;
        }
    } while (!isValid);

    // Solicita e valida comprimento da janela da mediana móvel
    isValid = 0;
    do {
        printf("Digite o comprimento da janela da mediana móvel: ");
        fgets(userInput, sizeof(userInput), stdin);
        userInput[strcspn(userInput, "\n")] = 0;

        if (!is_char_number(userInput)) {
            printf("ALERTA: Entrada inválida. Insira um número inteiro positivo.\n");
            continue;
        }

        machineToAdd.median_window_length = strtol(userInput, NULL, 10);
        if (machineToAdd.median_window_length <= 0) {
            printf("ALERTA: O comprimento da janela deve ser um número positivo.\n");
        } else if (machineToAdd.median_window_length > machineToAdd.circular_buffer_length) {
            printf(
                "ALERTA: O comprimento da janela da mediana móvel não pode ser maior que o comprimento do buffer circular.\n");
        } else {
            isValid = 1;
        }
    } while (!isValid);

    // Inicializa outros atributos da máquina
    strncpy(machineToAdd.state, "ON", sizeof(machineToAdd.state) - 1);
    machineToAdd.state[sizeof(machineToAdd.state) - 1] = '\0';
    machineToAdd.current_temp = machineToAdd.min_temp;
    machineToAdd.current_humidity = machineToAdd.min_humidity;
    machineToAdd.times_tamp = time(NULL);

    // Adiciona a nova máquina ao array
    machines[machine_count] = machineToAdd;
    machine_count++;

    // Confirmação da criação da máquina
    printf("\nMáquina adicionada com sucesso:\n");
    printf("ID: %d, Nome: %s\n", machineToAdd.id, machineToAdd.name);
    printf("Temperatura: %.2f°C - %.2f°C\n", machineToAdd.min_temp, machineToAdd.max_temp);
    printf("Humidade: %.2f%% - %.2f%%\n", machineToAdd.min_humidity, machineToAdd.max_humidity);
    printf("Comprimento do Buffer Circular: %d\n", machineToAdd.circular_buffer_length);
    printf("Comprimento da Janela da Mediana Móvel: %d\n", machineToAdd.median_window_length);
}

// Função para remover uma máquina com a opção de cancelar
void remove_machine() {
    printf("\n");

    if (machine_count == 0) {
        printf("Não há máquinas para remover.\n");
        return;
    }

    show_available_machines();
    printf("\n");

    int targetMachineId;
    int targetIndex = -1;
    char userChoice;

    // Prompt for removal or cancellation
    do {
        printf("Digite o ID da máquina que deseja remover, ou 'C' para cancelar: ");

        char userInput[10]; // Buffer for user input
        scanf("%s", userInput); // Read input as string

        if (userInput[0] == 'C' || userInput[0] == 'c') {
            printf("Processo de remoção cancelado.\n");
            return;
        }

        // Validate input as a number
        if (sscanf(userInput, "%d", &targetMachineId) != 1) {
            printf("Opção inválida. Se deseja cancelar, digite 'C'.\n");
        } else {
            targetIndex = find_machine_by_id(targetMachineId);
            if (targetIndex == -1) {
                printf("Máquina com ID %d não encontrada. Tente novamente.\n", targetMachineId);
            } else {
                break;
            }
        }
    } while (targetIndex == -1);

    // Confirm removal
    if (targetIndex != -1) {
        printf("Você selecionou a máquina com ID %d e Nome: %s.\n",
               machines[targetIndex].id,
               machines[targetIndex].name);
        printf("Deseja remover esta máquina? (S para sim, C para cancelar): ");

        while (getchar() != '\n'); // Clear input buffer
        scanf("%c", &userChoice);

        if (userChoice == 'C' || userChoice == 'c') {
            printf("Processo de remoção cancelado.\n");
            return;
        }

        if (strcmp(machines[targetIndex].state, "OP") == 0) {
            printf("A máquina está em operação e não pode ser removida.\n");
            return;
        }

        // Remove the machine
        printf("Máquina %d removida com sucesso.\n", machines[targetIndex].id);
        for (int i = targetIndex; i < machine_count - 1; i++) {
            machines[i] = machines[i + 1];
        }
        machine_count--;
    }
}

void assign_operation() {
    printf("\n");

    if (machine_count == 0) {
        printf("No machines registered in the system.\n");
        return;
    }

    show_available_machines();

    printf("\n");

    int selected_machine_id;
    int machine_position = -1;
    char cancel_input;
    char user_input[100];

    do {
        printf("Insert machine id, or 'C' to cancel: ");
        scanf("%s", user_input); // Read input as a string

        // If the user types 'C' or 'c', cancel the operation
        if (user_input[0] == 'C' || user_input[0] == 'c') {
            printf("Action canceled.\n");
            return; // Cancel operation
        }

        // Check if the input is a valid number
        if (sscanf(user_input, "%d", &selected_machine_id) != 1) {
            printf("Invalid Input. To cancel, write 'C'.\n");
        } else {
            machine_position = find_machine_by_id(selected_machine_id); // Check if the machine exists

            if (machine_position == -1) {
                printf("Machine ID %d not found.\n", selected_machine_id);
            } else {
                break; // machine found, break out of the loop
            }
        }
    } while (machine_position == -1);

    if (machine_position != -1) {
        // If a machine is found, prompt user to confirm assignment
        printf("Machine ID %d and Name: %s.\n", machines[machine_position].id, machines[machine_position].name);
        printf("Assign operation? (Y for yes, C to cancel): ");

        buffer_clear_input();
        scanf("%c", &cancel_input);

        if (cancel_input == 'C' || cancel_input == 'c') {
            // If the user chooses to cancel, exit the function
            printf("Operation Assignement canceled.\n");
            return;
        }
    }

    show_available_operations();

    printf("\n");

    int selected_operation_id;
    int operation_position = -1;
    char *conversion_ptr;

    do {
        printf("Insert the operation ID to assign, or 'C' to cancel: ");
        scanf("%s", user_input); // Read input as a string

        if (user_input[0] == 'C' || user_input[0] == 'c') {
            // If the user types 'C' or 'c', cancel the operation assignment
            printf("Assignement canceled.\n");
            return;
        }

        selected_operation_id = strtol(user_input, &conversion_ptr, 10); // Use conversion_ptr to validate input

        if (*conversion_ptr != '\0') {
            // Check if the input is valid
            printf("Invalid input.\n");
            continue;
        }

        operation_position = find_operation_by_id(selected_operation_id); // Find the operation by its ID

        if (operation_position == -1) {
            // If the operation is not found, display an error message
            printf("Operation ID %d not found.\n", selected_operation_id);
        } else {
            break; // Operation found, break out of the loop
        }
    } while (operation_position == -1);

    if (operation_position != -1) {
        // If the operation is found, ask the user to confirm the assignment
        printf("Selected Operation: %s.\n", operations[operation_position].name);
        printf("Assign this operation? (Y for yes, C to cancel): ");

        while (1) {
            if (fgets(user_input, sizeof(user_input), stdin) == NULL) {
                printf("Error validating input. Try again.\n");
                continue;
            }

            user_input[strcspn(user_input, "\n")] = '\0'; // Remove the newline character

            if (strcasecmp(user_input, "C") == 0) {
                // Handle user input for confirmation
                printf("Action canceled.\n");
                return;
            } else if (strcasecmp(user_input, "Y") == 0) {
                break;
            } else {
                printf("Invalid option. Type 'Y' for yes or 'C' to cancel: ");
            }
        }
    }

    if (machines[machine_position].operation_count >= 32) {
        printf("Error: Operation per machine limit reached.\n");
        return;
    }

    // Assign the selected operation to the machine
    machines[machine_position].operation_ids[machines[machine_position].operation_count] = operations[
        operation_position].id;
    machines[machine_position].operation_count++;

    printf("Op: %s successfully assigned to: %s.\n", operations[operation_position].name,
           machines[machine_position].name);
}

// Função para verificar o status das máquinas (USAC14)
void check_machine_status() {
    printf("\n");
    if (machine_count == 0) {
        printf("Nenhuma máquina disponível no momento.\n");
        return;
    }

    // Exibir máquinas disponíveis
    show_available_machines();
    printf("\n");

    int id_maquina;
    const char *status;

    // Solicitar o ID da máquina para detalhes ou permitir o cancelamento
    while (1) {
        printf("Digite o ID da máquina para ver mais detalhes ou 'C' para cancelar: ");

        char entrada[10]; // Array para armazenar a entrada do usuário

        scanf("%s", entrada); // Lê a entrada como string

        // Se o usuário digitar 'C' ou 'c', cancela o processo
        if (entrada[0] == 'C' || entrada[0] == 'c') {
            printf("Processo de visualização cancelado.\n");
            return; // Cancelar o processo
        }

        // Verificar se a entrada é um número válido
        if (sscanf(entrada, "%d", &id_maquina) != 1) {
            printf("Opção inválida. Se deseja cancelar, digite 'C'.\n");
        } else {
            // Função para encontrar o índice da máquina a partir do ID
            int indice_maquina = find_machine_by_id(id_maquina);
            if (indice_maquina == -1) {
                printf("Máquina com ID %d não encontrada. Tente novamente.\n", id_maquina);
            } else {
                // Exibir detalhes da máquina selecionada
                Machine *m = &machines[indice_maquina];
                printf("\nDetalhes da máquina:\n");
                printf("ID: %d\n", m->id);
                printf("Nome: %s\n", m->name);
                printf("Temperatura atual: %.2f°C (Min: %.2f°C, Max: %.2f°C)\n", m->current_temp, m->min_temp,
                       m->max_temp);
                printf("Humidade atual: %.2f%% (Min: %.2f%%, Max: %.2f%%)\n", m->current_humidity, m->min_humidity,
                       m->max_humidity);
                printf("Comprimento do Buffer Circular: %d\n", m->circular_buffer_length);
                printf("Comprimento da Janela da Mediana Móvel: %d\n", m->median_window_length);

                if (strcmp(m->state, "ON") == 0) {
                    status = "Parada (Pronta para ser operada)";
                } else if (strcmp(m->state, "OFF") == 0) {
                    status = "Desligada";
                } else if (strcmp(m->state, "OP") == 0) {
                    status = "Em operação";
                } else {
                    status = "Estado desconhecido";
                }
                printf("Status: %s\n", status);

                // Exibir as operações atribuídas à máquina
                if (m->operation_count == 0) {
                    printf("Sem operações atribuídas.\n");
                } else {
                    printf("Operações atribuídas: ");

                    for (int i = 0; i < m->operation_count; i++) {
                        printf("%d - %s", m->operation_ids[i], operations[m->operation_ids[i]].name);
                        if (i < m->operation_count - 1) {
                            printf(", ");
                        }
                    }
                    printf("\n");
                }
                printf("\n");
                return; // Após exibir os detalhes, sair da função
            }
        }
    }
}

// Função para exportar operações de uma máquina para um arquivo CSV
void export_machine_operations() {
    printf("\n");
    if (machine_count == 0) {
        printf("Não há máquinas para exportar.\n");
        return;
    }

    // Exibir todas as máquinas disponíveis
    show_available_machines(machines, machine_count);
    printf("\n");

    int id_maquina;

    // Solicitar o ID da máquina cujas operações deseja exportar ou permitir o cancelamento
    printf("Digite o ID da máquina cujas operações deseja exportar (ou 'C' para cancelar): ");
    char entrada[10]; // Array para armazenar a entrada do usuário
    scanf("%s", entrada); // Lê a entrada como string

    // Se o usuário digitar 'C' ou 'c', cancela o processo
    if (entrada[0] == 'C' || entrada[0] == 'c') {
        printf("Processo de exportação cancelado.\n");
        return; // Cancelamento do processo
    }

    // Verificar se o ID da máquina é válido
    if (sscanf(entrada, "%d", &id_maquina) != 1) {
        printf("ID inválido. Processo de exportação cancelado.\n");
        return;
    }

    // Verificar se a máquina existe
    int indice_maquina = -1;
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id_maquina) {
            indice_maquina = i;
            break;
        }
    }

    if (indice_maquina == -1) {
        printf("Máquina com ID %d não encontrada.\n", id_maquina);
        return;
    }

    // Obter a máquina selecionada
    Machine *m = &machines[indice_maquina];

    // Gerar o nome do arquivo com o nome da máquina
    char nome_arquivo[MAX_FILENAME_LENGTH + 20]; // Espaço extra para '_operations.csv'
    snprintf(nome_arquivo, sizeof(nome_arquivo), "%s_operations.csv", m->name);

    // Abrir o arquivo CSV para escrita
    FILE *arquivo = fopen(nome_arquivo, "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para exportação.\n");
        return;
    }

    // Escrever o cabeçalho no arquivo CSV
    fprintf(arquivo, "Máquina ID;Máquina Nome;Operação ID;Operação Nome\n");

    // Verificar se existem operações atribuídas e exportá-las
    int operacao_encontrada = 0;
    for (int i = 0; i < m->operation_count; i++) {
        // Iterando sobre todas as operações atribuídas
        int indice_operacao = find_operation_by_id(m->operation_ids[i]);
        if (indice_operacao != -1) {
            // Escrever os dados da operação no formato CSV
            fprintf(arquivo, "%d;%s;%d;%s\n", m->id, m->name, m->operation_ids[i], operations[indice_operacao].name);
            operacao_encontrada = 1;
        }
    }

    // Caso não haja operações, informar no arquivo
    if (!operacao_encontrada) {
        fprintf(arquivo, "Nenhuma operação atribuída.\n");
    }

    fclose(arquivo); // Fecha o arquivo após a escrita
    printf("Operações exportadas com sucesso para '%s'.\n", nome_arquivo);
}

// Função para mostrar o menu
void show_machine_menu(Machine *machine) {
    printf("\nCommand for Machine: %d - %s\n", machine->id, machine->name);
    printf("Insert ON/OP,<operation number> (0-31) or OFF (C - Cancel):\n");
}

void control_machine(int fd, Machine *machine) {
    while (1) {
        show_machine_menu(machine);

        char s[10];
        int n = -1;
        char command[30];

        char input[20];

        if (fgets(input, sizeof(input), stdin) == NULL) {
            printf("Error in input");
            continue;
        }

        // remove \n
        input[strcspn(input, "\n")] = '\0';

        if (input[0] == 'C' || input[0] == 'c') {
            printf("Process canceled.");
            return;
        }

        if (sscanf(input, "%[^,],%d", s, &n) == 2) {
            if ((strcasecmp(s, "ON") == 0 || strcasecmp(s, "OP") == 0) && n >= 0 && n <= 31) {

                snprintf(command, sizeof(command), "%s,%d", s, n);
                printf("Command: %s \n", command);
                send_command_to_machine(fd, command);
                strcpy(machine->state, s);

                time(&machine->times_tamp);

                read_sensor_data(fd, machine);
            } else {
                printf("Invalid number (0-31) or invalid command");
            }
        } else if (sscanf(input, "%s\n", s) == 1) {
            if (strcasecmp(s, "OFF") == 0) {
                strcpy(command, "OFF");

                printf("Command: %s \n", command);

                send_command_to_machine(fd, command);

                strcpy(machine->state, "OFF");

                printf("\n Machine Offline.\n");
            } else {
                printf("Invalid command \n");
            }
        } else {
            printf("Invalid input");
        }
    }
}

void control_machines(int fd) {
    buffer_clear_input();

    while (1) {
        printf("\n");

        if (machine_count == 0) {
            printf("No machines registred.\n");
            return;
        }

        show_available_machines();

        printf("\n Select Machine to control (C - cancel): ");

        char input[10];

        if (fgets(input, sizeof(input), stdin) == NULL) {
            printf("Error in input\n");
            continue;
        }

        input[strcspn(input, "\n")] = '\0';

        if (strcasecmp(input, "C") == 0) {
            printf("Cancelling...\n");
            return;
        }

        // Tenta converter a entrada para um número
        int selected_id;

        if (sscanf(input, "%d", &selected_id) == 1) {
            Machine *selectedMachine = chose_machine(selected_id);

            if (selectedMachine == NULL) {
                printf("Invalid machine id.\n");
            } else {
                printf("Selected ID: %d\n", selectedMachine->id);
                control_machine(fd, selectedMachine);
            }
        } else {
            printf("Invalid input.\n");
        }
    }
}

void process_command_for_machine(Machine *machine, char *command, int fd) {

    char s[10];
    int n = -1;

    if (sscanf(command, "%[^,],%d", s, &n) == 2) {

        char cmd[30];

        if (process_command(s, n, cmd)) {

            printf("Command sent: %s,%d\n", s, n);
            printf("Command: %s\n", cmd);

            send_command_to_machine(fd, cmd);

            strcpy(machine->state, s);

            time(&machine->times_tamp);

        } else {
            printf("Invalid command.\n");
        }
    } else if (sscanf(command, "%s", s) == 1) {
        if (strcmp(s, "OFF") == 0) {
            char cmd[30] = "OFF";

            strcpy(cmd, "OFF");

            printf("Comando enviado: %s\n", cmd);

            send_command_to_machine(fd, cmd);

            strcpy(machine->state, "OFF");

            printf("Machine offline.\n");
        } else {
            printf("Invalid command.\n");
        }
    } else {
        printf("Invalid input.\n");
    }

    // wait 2 s
    sleep(2);
}
