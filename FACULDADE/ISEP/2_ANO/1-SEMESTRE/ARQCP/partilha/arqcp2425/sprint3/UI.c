#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>
#include <unistd.h>
#include "machManager.h"

// COMMIT DO TIAGO

Machine *machines = NULL;
int machine_count = 0;
int machine_capacity = INITIAL_MACHINE_CAPACITY;

//COMMIT DO RICARDO

// Lista de operações
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


//COMMIT DO DANIEL

// Strings para Status
const char* status_strings[STATUS_COUNT] = {"OFF", "ON", "OP"};

// COMMIT DO RICARDO

// Função para redimensionar o array de máquinas
void resizeMachines() 
{
    machine_capacity <<= 1; // Dobra a capacidade
    
    machines = (Machine *)realloc(machines, machine_capacity * sizeof(Machine));
    
    if (machines == NULL) 
    {
        printf("Erro ao redimensionar memória para as máquinas.\n");
        exit(1); // Termina o programa em caso de falha de alocação
    }
}

//COMMIT DO RICARDO

// Função para mostrar as operações disponíveis no sistema
void showAvailableOperations() {
    printf("\nOperações disponíveis:\n");

    int found = 0;  // Flag para verificar se há operações

    for (int i = 0; i < MAX_OPERATIONS; i++) {
        // Verifica se a operação foi inicializada (assumindo que operações com nome vazio ou nula são inválidas)
        if (strlen(operations[i].name) > 0) {
            printf("ID: %d, Nome: %s\n", operations[i].id, operations[i].name);
            found = 1;  // Marcamos que encontramos pelo menos uma operação válida
        }
    }

    if (!found) {
        printf("Nenhuma operação disponível.\n");
    }
}

// COMMIT DO RICARDO

// Função para comparar máquinas por ID
int compareMachinesById(const void *a, const void *b) {
    Machine *machineA = (Machine *)a;
    Machine *machineB = (Machine *)b;
    return (machineA->id - machineB->id);
}

// COMMIT DO RICARDO

// Função para exibir as máquinas disponíveis e as operações atribuídas, ordenadas por ID
void showAvailableMachines() {
    // Ordena as máquinas por ID usando qsort
    qsort(machines, machine_count, sizeof(Machine), compareMachinesById);

    printf("Máquinas disponíveis:\n");

    for (int i = 0; i < machine_count; i++) {
        const char* status;

        if (strcmp(machines[i].state, "ON") == 0) {
            status = "Parada (Pronta para ser operada)";
        } else if (strcmp(machines[i].state, "OFF") == 0) {
            status = "Desligada";
        } else if (strcmp(machines[i].state, "OP") == 0) {
            status = "Em operação";
        } else {
            status = "Estado desconhecido";
        }

        printf("ID: %d, Nome: %s, Status: %s", machines[i].id, machines[i].name, status);

        // Verifica se existem operações atribuídas e exibe todas as operações
        if (machines[i].operation_count > 0) {
            printf(", Operações atribuídas: ");
            for (int j = 0; j < machines[i].operation_count; j++) {
                int operation_id = machines[i].operation_ids[j];
                int operation_index = findOperationById(operation_id);
                if (operation_index != -1) {
                    // Exibe o ID e o nome da operação
                    printf("%d - %s", operation_id, operations[operation_index].name);
                    if (j < machines[i].operation_count - 1) {
                        printf(", ");  // Adiciona vírgula entre as operações
                    }
                }
            }
        } else {
            printf(", Sem operações atribuídas");
        }
        printf("\n");
    }
}


// COMMIT DO RICARDO

// Função para encontrar uma máquina pelo ID
int findMachineById(int id) {
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id) {
            return i;
        }
    }
    return -1;
}

// COMMIT DO RICARDO

// Função para encontrar operação pelo ID
int findOperationById(int operation_id) {
    for (int i = 0; i < MAX_OPERATIONS; i++) {
        if (operations[i].id == operation_id) {
            return i;
        }
    }
    return -1;  // Retorna -1 se não encontrar a operação
}

// COMMIT DO RICARDO

// Função para verificar se o ID da máquina já existe
int isMachineIdExist(int id) {
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == id) {
            return 1;  // Retorna 1 se o ID já existir
        }
    }
    return 0;  // Retorna 0 se o ID não existir
}

// COMMIT DO TIAGO

// Função para verificar se uma string é um número de ponto flutuante válido
int isFloatNumber(const char *str) {
    char *endptr;
    strtof(str, &endptr);
    // Verifica se o número foi totalmente convertido e se não há mais caracteres restantes
    return *endptr == '\0' && str != endptr;
}

// COMMIT DA BRUNA

// Função para verificar se uma string é um número inteiro válido
int isNumber(const char *str) {
    char *endptr;
    strtol(str, &endptr, 10);
    // Verifica se o número foi totalmente convertido e se não há mais caracteres restantes
    return *endptr == '\0' && str != endptr;
}

// COMMIT DA BRUNA

// Função para limpar o buffer de entrada
void clearInputBuffer() {
    while (getchar() != '\n'); // Limpa o buffer até a nova linha
}

// COMMIT DO DANIEL

// Função para adicionar a máquina ao sistema
void addMachine() {
	printf("\n");
	if (machine_count >= machine_capacity) {
        resizeMachines();  // Redimensiona o array se necessário
    }

    Machine new_machine;
    
    // Inicializa o array operation_ids com -1
    for (int i = 0; i < MAX_OPERATIONS; i++) {
        new_machine.operation_ids[i] = -1;
    }
    new_machine.operation_count = 0; // Inicializa o contador de operações
    
    char input[100];
    int valid = 0;
    int id;

    // Exibe a mensagem apenas uma vez
    printf("Digite o ID da nova máquina (letras/símbolos inseridos após um número não serão contabilizados ex: 12h -> 12) : ");
        
    // Loop para garantir que o id inserido seja um número não negativo
    while (1) {
        if (scanf("%d", &id) != 1 || id < 0) {
            printf("Por favor, insira um número não negativo para o ID: ");
            while (getchar() != '\n'); // Limpa o buffer de entrada
        } else {
            // Verifica se o ID já existe
            int id_exists = 0;
            for (int i = 0; i < machine_count; i++) {
                if (machines[i].id == id) {
                    id_exists = 1;
                    break;
                }
            }

            if (id_exists) {
                printf("ALERTA: O ID %d já existe. Por favor, insira um ID único.\n", id);
            } else {
                new_machine.id = id;  // Atribui o ID à nova máquina
                while (getchar() != '\n'); // Limpa qualquer caractere residual no buffer
                break;
            }
        }
    }

    // Solicitar o nome da máquina
    printf("Digite o nome da máquina: ");
    fgets(new_machine.name, sizeof(new_machine.name), stdin);
    new_machine.name[strcspn(new_machine.name, "\n")] = 0; // Remove o '\n' que o fgets captura

    // Solicitar e validar temperaturas
    valid = 0;
    do {
        printf("Digite a temperatura mínima: ");
        fgets(input, sizeof(input), stdin);
        // Remover o '\n' que o fgets pode adicionar
        input[strcspn(input, "\n")] = 0;

        if (!isFloatNumber(input)) {
            printf("ALERTA: Entrada inválida para temperatura mínima. Insira um número.\n");
            continue;
        }
        new_machine.temp_min = strtof(input, NULL);

        printf("Digite a temperatura máxima: ");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove o '\n' extra

        if (!isFloatNumber(input)) {
            printf("ALERTA: Entrada inválida para temperatura máxima. Insira um número.\n");
            continue;
        }
        new_machine.temp_max = strtof(input, NULL);

        if (new_machine.temp_max < new_machine.temp_min) {
            printf("ALERTA: A temperatura máxima deve ser maior ou igual à mínima.\n");
        } else {
            valid = 1; // Temperaturas válidas
        }
    } while (!valid); // Repete até as temperaturas serem válidas

    // Solicitar e validar humidades
    valid = 0;
    do {
        printf("Digite a humidade mínima (0-100%%): ");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove o '\n' extra

        if (!isFloatNumber(input)) {
            printf("ALERTA: Entrada inválida para humidade mínima. Insira um número.\n");
            continue;
        }
        new_machine.hum_min = strtof(input, NULL);

        printf("Digite a humidade máxima (0-100%%): ");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove o '\n' extra

        if (!isFloatNumber(input)) {
            printf("ALERTA: Entrada inválida para humidade máxima. Insira um número.\n");
            continue;
        }
        new_machine.hum_max = strtof(input, NULL);

        if (new_machine.hum_max < new_machine.hum_min || new_machine.hum_max > 100 || new_machine.hum_min < 0) {
            printf("ALERTA: A humidade máxima deve estar entre 0 e 100 e ser maior ou igual à mínima.\n");
        } else {
            valid = 1; // Humidades válidas
        }
    } while (!valid); // Repete até as humidades serem válidas

    // Solicitar e validar comprimento do buffer circular
    valid = 0;
    do {
        printf("Digite o comprimento do buffer circular: ");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove o '\n' extra

        if (!isNumber(input)) {
            printf("ALERTA: Entrada inválida. Insira um número inteiro positivo.\n");
            continue;
        }

        new_machine.circular_buffer_length = strtol(input, NULL, 10);
        if (new_machine.circular_buffer_length <= 0) {
            printf("ALERTA: O comprimento do buffer deve ser um número positivo.\n");
        } else {
            valid = 1; // Comprimento do buffer válido
        }
    } while (!valid); // Repete até o comprimento do buffer ser válido

    // Solicitar e validar comprimento da janela da mediana móvel
    valid = 0;
    do {
        printf("Digite o comprimento da janela da mediana móvel: ");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = 0;  // Remove o '\n' extra

        if (!isNumber(input)) {
            printf("ALERTA: Entrada inválida. Insira um número inteiro positivo.\n");
            continue;
        }

         new_machine.median_window_length = strtol(input, NULL, 10);
        if (new_machine.median_window_length <= 0) {
            printf("ALERTA: O comprimento da janela deve ser um número positivo.\n");
        } else if (new_machine.median_window_length > new_machine.circular_buffer_length) {
            printf("ALERTA: O comprimento da janela da mediana móvel não pode ser maior que o comprimento do buffer circular.\n");
        } else {
            valid = 1; // Comprimento da janela válido
        }
    } while (!valid); // Repete até o comprimento da janela ser válido

	// Inicializar outros atributos da máquina
	strncpy(new_machine.state, "ON", sizeof(new_machine.state) - 1); // Estado inicial "ON"
	new_machine.state[sizeof(new_machine.state) - 1] = '\0'; // Garantir terminação nula
    new_machine.current_temp = new_machine.temp_min;  // Inicializa a temperatura atual como a mínima
    new_machine.current_hum = new_machine.hum_min;    // Inicializa a humidade atual como a mínima
    new_machine.timestamp = time(NULL);
    
    machines[machine_count] = new_machine;
    machine_count++;

    // Confirmação da criação da máquina
    printf("\nMáquina adicionada com sucesso:\n");
    printf("ID: %d, Nome: %s\n", new_machine.id, new_machine.name);
    printf("Temperatura: %.2f°C - %.2f°C\n", new_machine.temp_min, new_machine.temp_max);
    printf("Humidade: %.2f%% - %.2f%%\n", new_machine.hum_min, new_machine.hum_max);
    printf("Comprimento do Buffer Circular: %d\n", new_machine.circular_buffer_length);
    printf("Comprimento da Janela da Mediana Móvel: %d\n", new_machine.median_window_length);
}

// COMMIT DO DANIEL

// Função para remover uma máquina com a opção de cancelar
void removeMachine() {
	printf("\n");
    if (machine_count == 0) {
        printf("Não há máquinas para remover.\n");
        return;
    }

    showAvailableMachines();
    printf("\n");

    int machine_id;
    int machine_index = -1;
    char cancel_choice;

    // Solicitar a escolha do usuário sobre a remoção ou cancelamento
	do {
		printf("Digite o ID da máquina que deseja remover, ou 'C' para cancelar: ");
    
		// Tentar ler o ID da máquina, mas permitindo a entrada de 'C' para cancelar
		char input[10];  // Array para armazenar a entrada do usuário
		scanf("%s", input);  // Lê a entrada como string

		if (input[0] == 'C' || input[0] == 'c') {
			printf("Processo de remoção cancelado.\n");
			return;  // Cancelamento da remoção
		}

		// Verificar se a entrada é um número válido
		if (sscanf(input, "%d", &machine_id) != 1) {
			printf("Opção inválida. Se deseja cancelar, digite 'C'.\n");
		} else {
			machine_index = findMachineById(machine_id);
			if (machine_index == -1) {
				printf("Máquina com ID %d não encontrada. Tente novamente.\n", machine_id);
			} else {
				break;  // Encontrou a máquina e o ID é válido
			}
		}
	} while (machine_index == -1);

    // Confirmação para remover ou cancelar
    if (machine_index != -1) {
        printf("Você selecionou a máquina com ID %d e Nome: %s.\n", machines[machine_index].id, machines[machine_index].name);
        printf("Deseja remover esta máquina? (S para sim, C para cancelar): ");
        while (getchar() != '\n');  // Limpar buffer de entrada
        scanf("%c", &cancel_choice);

        if (cancel_choice == 'C' || cancel_choice == 'c') {
            printf("Processo de remoção cancelado.\n");
            return;  // Cancelamento da remoção
        }

		if (strcmp(machines[machine_index].state, "OP") == 0) {
			printf("A máquina está em operação e não pode ser removida.\n");
			return;	
		}

        // Remover a máquina da lista
        printf("Máquina %d removida com sucesso.\n", machines[machine_index].id);
        for (int i = machine_index; i < machine_count - 1; i++) {
            machines[i] = machines[i + 1];
        }
        machine_count--;
    }
}

// COMMIT DO RICARDO

// Função para atribuir operação a uma máquina com opção de cancelamento
void assignOperation() {
    printf("\n");
    if (machine_count == 0) {
        printf("Não há máquinas para atribuir operação.\n");
        return;
    }

    // Exibir máquinas disponíveis
    showAvailableMachines();
    printf("\n");

    int machine_id;
    int machine_index = -1;
    char cancel_choice;
    char input[100];  // Buffer para entrada

    // Solicitar o ID da máquina ou a opção de cancelamento
    do {
        printf("Digite o ID da máquina para atribuir operação, ou 'C' para cancelar: ");
        scanf("%s", input);  // Lê a entrada como string

        // Se o usuário digitar 'C' ou 'c', cancela o processo
        if (input[0] == 'C' || input[0] == 'c') {
            printf("Processo de atribuição cancelado.\n");
            return;  // Cancelamento da atribuição
        }

        // Verificar se a entrada é um número válido
        if (sscanf(input, "%d", &machine_id) != 1) {
            printf("Opção inválida. Se deseja cancelar, digite 'C'.\n");
        } else {
            // Verifica se a máquina existe
            machine_index = findMachineById(machine_id);
            if (machine_index == -1) {
                printf("Máquina com ID %d não encontrada. Tente novamente.\n", machine_id);
            } else {
                break;  // Encontrou a máquina e o ID é válido
            }
        }
    } while (machine_index == -1);

    // Confirmação para atribuir operação ou cancelar
    if (machine_index != -1) {
        printf("Você selecionou a máquina com ID %d e Nome: %s.\n", machines[machine_index].id, machines[machine_index].name);
        printf("Deseja atribuir operação a esta máquina? (S para sim, C para cancelar): ");
        
        while (getchar() != '\n');  // Limpar buffer de entrada
        scanf("%c", &cancel_choice);

        if (cancel_choice == 'C' || cancel_choice == 'c') {
            printf("Processo de atribuição cancelado.\n");
            return;  // Cancelamento da atribuição
        }
	}
	
    // Exibir operações disponíveis
	showAvailableOperations();	
	printf("\n");

	int operation_id;
	int operation_index = -1;
	char *endptr;     // Variável para validação da conversão

	// Escolher operação ou cancelar
	do {
		printf("Digite o ID da operação a atribuir, ou 'C' para cancelar: ");
		scanf("%s", input);  // Lê a entrada como string

		// Se o usuário digitar 'C' ou 'c', cancela o processo
		if (input[0] == 'C' || input[0] == 'c') {
			printf("Processo de atribuição da operação cancelado.\n");
			return;  // Cancelamento da atribuição
		}

		// Tenta converter a entrada para um número
		operation_id = strtol(input, &endptr, 10); // Uso de endptr para validação

		if (*endptr != '\0') {
			printf("Entrada inválida. Tente novamente.\n");
			continue;  // Se a conversão falhar, solicita nova entrada
		}

		operation_index = findOperationById(operation_id);

		if (operation_index == -1) {
			printf("Operação com ID %d não encontrada. Tente novamente.\n", operation_id);
		} else {
			break;  // Encontrou a operação e o ID é válido
		}
	} while (operation_index == -1);

	// Confirmação para atribuir a operação
	if (operation_index != -1) {
		printf("Você selecionou a operação: %s.\n", operations[operation_index].name);
		printf("Deseja atribuir esta operação à máquina? (S para sim, C para cancelar): ");

		// Leitura da confirmação
		while (1) {
			if (fgets(input, sizeof(input), stdin) == NULL) {
				printf("Erro ao ler entrada. Tente novamente.\n");
				continue;
			}
			input[strcspn(input, "\n")] = '\0';  // Remover o caractere de nova linha
	
			if (strcasecmp(input, "C") == 0) {
				printf("Processo de atribuição da operação cancelado.\n");
				return;  // Cancelamento da atribuição
			} else if (strcasecmp(input, "S") == 0) {
				break;  // Confirmou a atribuição
			} else {
				printf("Opção inválida. Digite 'S' para sim ou 'C' para cancelar: ");
			}
		}
	}

    // Verificar se a máquina já atingiu o limite de operações
    if (machines[machine_index].operation_count >= MAX_OPERATIONS) {
        printf("Erro: Esta máquina já atingiu o limite de operações.\n");
        return;
    }

    // Atribuir operação à máquina
    machines[machine_index].operation_ids[machines[machine_index].operation_count] = operations[operation_index].id;
    machines[machine_index].operation_count++; // Incrementar o contador de operações

    printf("Operação %s atribuída com sucesso à máquina %s.\n", operations[operation_index].name, machines[machine_index].name);
}

// COMMIT DO DANIEL

// Função para verificar o status das máquinas (USAC14)
void checkMachineStatus() {
	printf("\n");
    if (machine_count == 0) {
        printf("Nenhuma máquina disponível no momento.\n");
        return;
    }
    
    // Exibir máquinas disponíveis
    showAvailableMachines();
    printf("\n");

    int machine_id;
    const char* status;

	// Solicitar o ID da máquina para detalhes ou permitir o cancelamento
    while (1) {
        printf("Digite o ID da máquina para ver mais detalhes ou 'C' para cancelar: ");
        char input[10];  // Array para armazenar a entrada do usuário
        scanf("%s", input);  // Lê a entrada como string

        // Se o usuário digitar 'C' ou 'c', cancela o processo
        if (input[0] == 'C' || input[0] == 'c') {
            printf("Processo de visualização cancelado.\n");
            return;  // Cancelar o processo
        }

        // Verificar se a entrada é um número válido
        if (sscanf(input, "%d", &machine_id) != 1) {
            printf("Opção inválida. Se deseja cancelar, digite 'C'.\n");
        } else {
            // Função para encontrar o índice da máquina a partir do ID
            int machine_index = findMachineById(machine_id);
            if (machine_index == -1) {
                printf("Máquina com ID %d não encontrada. Tente novamente.\n", machine_id);
            } else {
                // Exibir detalhes da máquina selecionada
                Machine *m = &machines[machine_index];
                printf("\nDetalhes da máquina:\n");
                printf("ID: %d\n", m->id);
                printf("Nome: %s\n", m->name);
                printf("Temperatura atual: %.2f°C (Min: %.2f°C, Max: %.2f°C)\n", m->current_temp, m->temp_min, m->temp_max);
                printf("Humidade atual: %.2f%% (Min: %.2f%%, Max: %.2f%%)\n", m->current_hum, m->hum_min, m->hum_max);
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
                return;  // Após exibir os detalhes, sair da função
            }
        }
    }
}

// COMMIT DA BRUNA

// Função para processar o ficheiro de configuração
void loadConfigFromFile() {
	printf("\n");
    char filename[100];

	printf("Digite o nome do arquivo de configuração para carregar as máquinas ou 'C' para cancelar: ");
    scanf("%99s", filename); // Limite de leitura para evitar overflow
    clearInputBuffer(); // Limpa o buffer após a leitura

    // Verifica se o usuário digitou 'C' ou 'c'
    if (strcasecmp(filename, "C") == 0) {
        printf("Carregamento do arquivo cancelado.\n");
        return; // Sai da função
    }
    
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo %s. Verifique se o arquivo existe.\n", filename);
        return;
    }

    Machine temp_machine;
    char line[256]; // Variável para armazenar cada linha do arquivo

	printf("\n");
	
    // Agora verificamos se o número de campos lidos corresponde ao esperado
    while (fgets(line, sizeof(line), file)) {
        // Ignorar linhas em branco
        if (line[0] == '\n') {
            continue;
        }

        // Usa strtok para separar a linha pelos delimitadores ';'
        char *token = strtok(line, ";");

        // Lê os dados e atribui às variáveis correspondentes
        if (token != NULL) temp_machine.id = atoi(token); // ID

		// Verifica se o ID da máquina já existe
        if (isMachineIdExist(temp_machine.id)) {
            printf("ALERTA: Máquina com ID %d já existe. Máquina não foi registada.\n", temp_machine.id);
            continue;  // Ignora a máquina e continua com o próximo registro
        }
        
               // Verificação: ID válido (positivo)
        if (temp_machine.id <= 0) {
            printf("ALERTA: ID inválido (%d). O ID deve ser positivo.\n", temp_machine.id);
            continue;
        }
        
        token = strtok(NULL, ";");
        if (token != NULL) strncpy(temp_machine.name, token, MAX_NAME_LEN); // Nome

        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.temp_min = atof(token); // Temperatura mínima

        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.temp_max = atof(token); // Temperatura máxima

		// Verificação: temperatura máxima deve ser maior que a mínima
        if (temp_machine.temp_max <= temp_machine.temp_min) {
            printf("ALERTA: Temperatura máxima (%.2f) deve ser superior à mínima (%.2f) para a máquina ID %d.\n",
                   temp_machine.temp_max, temp_machine.temp_min, temp_machine.id);
            continue;
        }
        
        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.hum_min = atof(token); // Humidade mínima

        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.hum_max = atof(token); // Humidade máxima

		// Verificação: humidade máxima deve ser maior que a mínima
        if (temp_machine.hum_max <= temp_machine.hum_min) {
            printf("ALERTA: Humidade máxima (%.2f) deve ser superior à mínima (%.2f) para a máquina ID %d.\n",
                   temp_machine.hum_max, temp_machine.hum_min, temp_machine.id);
            continue;
        }
        
        // Verificação: humidade não pode ser negativa
        if  (temp_machine.hum_min < 0 || temp_machine.hum_max < 0)  {
			printf("ALERTA: Humidade mínima (%.2f) e máxima (%.2f) não podem ser negativas para a máquina ID %d.\n",
                   temp_machine.hum_min, temp_machine.hum_max, temp_machine.id);
            continue;
        }
        
        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.circular_buffer_length = atoi(token); // Comprimento do buffer circular

        token = strtok(NULL, ";");
        if (token != NULL) temp_machine.median_window_length = atoi(token); // Comprimento da janela da mediana

		// Verificação: o comprimento da janela da mediana deve ser menor que o do buffer circular
        if (temp_machine.median_window_length >= temp_machine.circular_buffer_length) {
            printf("ALERTA: Comprimento da janela da mediana (%d) deve ser inferior ao comprimento do buffer circular (%d) para a máquina ID %d.\n",
                   temp_machine.median_window_length, temp_machine.circular_buffer_length, temp_machine.id);
            continue;
        }
        
		// Inicializa o status da máquina
        strncpy(temp_machine.state, "ON", sizeof(temp_machine.state) - 1); // Estado inicial "ON"
		temp_machine.state[sizeof(temp_machine.state) - 1] = '\0'; // Garantir terminação nula
        temp_machine.current_temp = temp_machine.temp_min; // Inicializa a temperatura como a mínima
        temp_machine.current_hum = temp_machine.hum_min;   // Inicializa a umidade como a mínima
        temp_machine.operation_count = 0;  // Inicializa o contador de operações

        // Inicializa todos os IDs de operações com -1 (nenhuma operação atribuída)
        for (int i = 0; i < MAX_OPERATIONS; i++) {
            temp_machine.operation_ids[i] = -1;
        }

        // Carrega as operações associadas, se houver (supondo que elas venham na linha após os dados da máquina)
        token = strtok(NULL, ";");
        while (token != NULL && temp_machine.operation_count < MAX_OPERATIONS) {
            int operation_id = atoi(token);
            if (operation_id != 0) {  // Ignora 0, pois significa que a operação não foi atribuída
                temp_machine.operation_ids[temp_machine.operation_count++] = operation_id;
            }
            token = strtok(NULL, ";"); // Próximo token (operações)
        }

        // Adiciona a máquina à lista de máquinas
        machines[machine_count] = temp_machine;
        machine_count++;

        // Confirmação da máquina carregada
        printf("Máquina carregada: ID: %d, Nome: %s, Temp: %.2f-%.2f°C, Hum: %.2f-%.2f%%,",
               temp_machine.id, temp_machine.name, temp_machine.temp_min, temp_machine.temp_max,
               temp_machine.hum_min, temp_machine.hum_max);
        
        // Verifica se existem operações atribuídas e exibe todas as operações
        if (temp_machine.operation_count > 0) {
            printf(" Operações atribuídas: ");
            for (int j = 0; j < temp_machine.operation_count; j++) {
                int operation_id =temp_machine.operation_ids[j];
                int operation_index = findOperationById(operation_id);
                if (operation_index != -1) {
                    // Exibe o ID e o nome da operação
                    printf("%d - %s", operation_id, operations[operation_index].name);
                    if (j < temp_machine.operation_count - 1) {
                        printf(", ");  // Adiciona vírgula entre as operações
                    }
                }
            }
        } else {
            printf(" Sem operações atribuídas");
        }

        printf("\n");
    }

    fclose(file);  // Fecha o arquivo após ler
    printf("Configuração carregada com sucesso.\n");
}

// COMMIT DO DANIEL

// Função para exportar operações de uma máquina para um arquivo CSV
void exportMachineOperations() {
	printf("\n");
    if (machine_count == 0) {
        printf("Não há máquinas para exportar.\n");
        return;
    }

    // Exibir todas as máquinas disponíveis
    showAvailableMachines(machines, machine_count);
    printf("\n");

    int machine_id;

    // Solicitar o ID da máquina cujas operações deseja exportar ou permitir o cancelamento
    printf("Digite o ID da máquina cujas operações deseja exportar (ou 'C' para cancelar): ");
    char input[10];  // Array para armazenar a entrada do usuário
    scanf("%s", input);  // Lê a entrada como string

    // Se o usuário digitar 'C' ou 'c', cancela o processo
    if (input[0] == 'C' || input[0] == 'c') {
        printf("Processo de exportação cancelado.\n");
        return;  // Cancelamento do processo
    }

    // Verificar se o ID da máquina é válido
    if (sscanf(input, "%d", &machine_id) != 1) {
        printf("ID inválido. Processo de exportação cancelado.\n");
        return;
    }

    // Verificar se a máquina existe
    int machine_index = -1;
    for (int i = 0; i < machine_count; i++) {
        if (machines[i].id == machine_id) {
            machine_index = i;
            break;
        }
    }

    if (machine_index == -1) {
        printf("Máquina com ID %d não encontrada.\n", machine_id);
        return;
    }

    // Obter a máquina selecionada
    Machine *m = &machines[machine_index];

    // Gerar o nome do arquivo com o nome da máquina
    char filename[MAX_NAME_LEN + 20];  // Espaço extra para '_operations.csv'
    snprintf(filename, sizeof(filename), "%s_operations.csv", m->name);

    // Abrir o arquivo CSV para escrita
    FILE *file = fopen(filename, "w");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para exportação.\n");
        return;
    }

    // Escrever o cabeçalho no arquivo CSV
    fprintf(file, "Máquina ID;Máquina Nome;Operação ID;Operação Nome\n");

	// Verificar se existem operações atribuídas e exportá-las
    int operation_found = 0;
    for (int i = 0; i < m->operation_count; i++) {  // Iterando sobre todas as operações atribuídas
        int operation_index = findOperationById(m->operation_ids[i]);
        if (operation_index != -1) {
            // Escrever os dados da operação no formato CSV
            fprintf(file, "%d;%s;%d;%s\n", m->id, m->name, m->operation_ids[i], operations[operation_index].name);
            operation_found = 1;
        }
    }

    // Caso não haja operações, informar no arquivo
    if (!operation_found) {
        fprintf(file, "Nenhuma operação atribuída.\n");
    }

    fclose(file);  // Fecha o arquivo após a escrita
    printf("Operações exportadas com sucesso para '%s'.\n", filename);
}

// COMMIT DO TIAGO

// Função para mostrar o menu
void showMachineMenu(Machine* machine) {
    printf("\nComando para a Máquina: %d - %s\n", machine->id, machine->name);
    printf("Insira ON,<número operação> ou OFF ou OP,<número operação> (0-31) (ou digite 'C' para cancelar e retornar ao menu principal):\n");
}

// COMMIT DO TIAGO

// Função para processar o comando mandado para a máquina
void controlMachine(int fd, Machine* machine) {
    while (1) {
        showMachineMenu(machine);

        char op[10];
        int n = -1;
        char cmd[20];
        
        char input[20];  // Aumenta o tamanho para garantir que a entrada seja armazenada corretamente
        scanf("%s", input);  // Lê a entrada como string

        // Remove o caractere de nova linha, se presente
        input[strcspn(input, "\n")] = '\0';

        // Substitui a vírgula por um espaço, se presente, para que o sscanf consiga analisar corretamente
        for (size_t i = 0; i < strlen(input); i++) {
            if (input[i] == ',') {
                input[i] = ' ';
            }
        }

		clearInputBuffer();
		
        // Se o usuário digitar 'C' ou 'c', cancela o processo
        if (input[0] == 'C' || input[0] == 'c') {
            printf("Retornando ao menu de seleção de máquinas...\n");
            return;  // Cancelamento do processo
        }

        // Tenta ler o comando no formato "<op> <número>"
		if (sscanf(input, "%s %d", op, &n) == 2) {
			// Verifica se o comando é "ON" ou "OP" e se o número da operação está dentro do intervalo
			if ((strcasecmp(op, "ON") == 0 || strcasecmp(op, "OP") == 0) && n >= 0 && n <= 31) {
				if (processCommand(op, n, cmd)) {
					
					printf("\nComando formatado: %s\n", cmd);
					sendCommandToMachine(fd, cmd);
					strcpy(machine->state, op);
			
					// Adiciona a operação à lista de operações da máquina, se não estiver presente
                    int found = 0;
                    for (int i = 0; i < machine->operation_count; i++) {
                        if (machine->operation_ids[i] == n) {
                            found = 1;
                            break;
                        }
                    }
                    
                    // Se a operação não foi encontrada, adicione-a
					if (!found && machine->operation_count < MAX_OPERATIONS) {
						machine->operation_ids[machine->operation_count++] = n;
						printf("\nOperação %d (%s) atribuída à máquina %s.\n", operations[n].id, operations[n].name, machine->name);
					} else {
						if (found) {
							printf("\nOperação %d (%s) já atribuída à máquina %s.\n", operations[n].id, operations[n].name, machine->name);
						} else {
							printf("\nCapacidade máxima de operações atingida para a máquina %s.\n", machine->name);
						}
					}

					time(&machine->timestamp);
					
					readSensorData(fd, machine); // Lê e exibe os dados do sensor
					
				} else {
					printf("Comando inválido. Tente novamente.\n");
				}
			} else {
				printf("Comando inválido ou número da operação fora do intervalo (0-31). Tente novamente.\n");
			}
        
		} else if (sscanf(input, "%s", op) == 1) {
			// Caso o comando seja "OFF"
			if (strcasecmp(op, "OFF") == 0) {
				strcpy(cmd, "OFF");
				printf("\nComando formatado: %s\n", cmd);
				sendCommandToMachine(fd, cmd);
				strcpy(machine->state, "OFF");
				printf("\nMáquina desligada.\n");
				
			} else {
				printf("Comando inválido. Tente novamente.\n");
			}
		} else {
			printf("Entrada inválida. Tente novamente.\n");
		}
    }
}


// COMMIT DO TIAGO

// Função para controlar cada máquina
void controlMachines(int fd) {
	
	clearInputBuffer();
	        
    while (1) {
        printf("\n");
        if (machine_count == 0) {
            printf("Não há máquinas registradas.\n");
            return;
        }

        showAvailableMachines();

        printf("\nDigite o ID da máquina que deseja controlar (ou digite 'C' para cancelar e retornar ao menu principal): ");

        char input[10];  // Array para armazenar a entrada do usuário
        if (fgets(input, sizeof(input), stdin) == NULL) {  // Lê a entrada como string
            printf("Erro ao ler entrada. Tente novamente.\n");
            continue;
        }

        // Remove o caractere de nova linha, se presente
        input[strcspn(input, "\n")] = '\0';

        // Verifica se o usuário quer cancelar
        if (strcasecmp(input, "C") == 0) {
            printf("Retornando ao menu principal...\n");
            return;  // Cancelamento do processo
        }

        // Tenta converter a entrada para um número
        int selectedId;
        if (sscanf(input, "%d", &selectedId) == 1) {
            Machine* selectedMachine = selectMachine(selectedId);
            if (selectedMachine == NULL) {
                printf("ID da máquina inválido. Tente novamente.\n");
            } else {
                printf("Você selecionou a máquina com ID: %d\n", selectedMachine->id);
                controlMachine(fd, selectedMachine);
            }
        } else {
            printf("Entrada inválida. Tente novamente.\n");
        }
    }
}

// COMMIT DO TIAGO

// Função para processar comandos da máquina
void processCommandForMachine(Machine* machine, const char* command, int fd) {
    char op[10];
    int n = -1;  // Inicializa n como -1 caso não haja número

    // Extrair o formato "<op>,<número>" ou "<on>"
    if (sscanf(command, "%[^,],%d", op, &n) == 2) {
        char cmd[20];
        if (processCommand(op, n, cmd)) {
			
            printf("Comando enviado: %s,%d\n", op, n);
            printf("Comando formatado: %s\n", cmd);
            sendCommandToMachine(fd, cmd);
            strcpy(machine->state, op);
            
            // Adiciona a operação à lista de operações da máquina, se não estiver presente
            int found = 0;
            for (int i = 0; i < machine->operation_count; i++) {
				if (machine->operation_ids[i] == n) {
					found = 1;
                    break;
                }
			}
			
			// Se a operação não foi encontrada, adicione-a
			if (!found && machine->operation_count < MAX_OPERATIONS) {
				machine->operation_ids[machine->operation_count++] = n;
				printf("Operação %d (%s) atribuída à máquina %s.\n", operations[n].id, operations[n].name, machine->name);
			} else {
				if (found) {
					printf("Operação %d (%s) já atribuída à máquina %s.\n", operations[n].id, operations[n].name, machine->name);
				} else {
					printf("Capacidade máxima de operações atingida para a máquina %s.\n", machine->name);
				}
			}
					   
            time(&machine->timestamp);  
           
            readSensorData(fd, machine);
            
        } else {
            printf("Comando inválido. Tente novamente.\n");
        }
    } else if (sscanf(command, "%s", op) == 1) {
        // Caso "OFF" sem número
        if (strcmp(op, "OFF") == 0) {
            char cmd[20] = "OFF";
            strcpy(cmd, "OFF");
            printf("Comando enviado: %s\n", cmd);
            sendCommandToMachine(fd, cmd);
            strcpy(machine->state, "OFF");
            printf("Máquina desligada.\n");
        } else {
            printf("Comando inválido. Tente novamente.\n");
        }
    } else {
        printf("Entrada inválida. Tente novamente.\n");
    }

    // Aguardar 2 segundos para visualização
    sleep(2);
}

// COMMIT DA BRUNA

// Função para ler o ficheiro e processar comandos
void instructMachines(int fd) {
	printf("\n");
	if (machine_count == 0) {
		printf("Não há máquinas registadas.\n");
		return;
	}
		
    char filename[100];
    printf("Digite o nome do arquivo de configuração para carregar ou 'C' para cancelar: ");
    scanf("%99s", filename);  // Limita a entrada a 99 caracteres
    clearInputBuffer();       // Limpa o buffer após a leitura

	// Verifica se o usuário digitou 'C' ou 'c'
    if (strcasecmp(filename, "C") == 0) {
        printf("Carregamento do arquivo cancelado.\n");
        return; // Sai da função
    }

    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo %s. Verifique se o arquivo existe.\n", filename);
        return;
    }

    char line[256];
    while (fgets(line, sizeof(line), file)) {
        // Remover o caractere de nova linha
        line[strcspn(line, "\n")] = '\0';

        // Separar o ID da máquina (antes do primeiro ';')
        char* machine_id_str = strtok(line, ";");
        if (machine_id_str == NULL) {
            printf("Linha inválida: %s\n", line);
            continue;
        }

        int machine_id = atoi(machine_id_str);
        int machine_index = findMachineById(machine_id);
        if (machine_index == -1) {
            printf("\nMáquina com ID %d não registada.\n", machine_id);
            continue;
        }
        
        // Apenas se encontrar a máquina
        printf("\nInstruções para a Máquina com ID %d.", machine_id);
        
        Machine* machine = &machines[machine_index];

        // Processar blocos de comandos: ON,12 ; OP,11 ; OFF
        char* remaining_commands = strtok(NULL, ""); // Pega o resto da linha
        if (remaining_commands == NULL) {
            printf("Sem comandos para a máquina %d.\n", machine_id);
            continue;
        }

        int instruction_count = 0; // Contador para instruções
        char* command_block = strtok(remaining_commands, ";");

        // Loop para processar comandos com contagem
        for (instruction_count = 1; command_block != NULL; instruction_count++) {
            printf("\nInstrução %d: \n", instruction_count);
            processCommandForMachine(machine, command_block, fd);

            // Próximo bloco de comandos
            command_block = strtok(NULL, ";");
        }
    }

    fclose(file);
}

// COMMIT DA BRUNA

void allocateMachines() {
    // Alocar memória para o array de máquinas com a capacidade inicial
    machines = (Machine *)malloc(machine_capacity * sizeof(Machine));
    if (machines == NULL) {
        printf("Erro ao alocar memória para as máquinas.\n");
        exit(1); // Termina o programa em caso de falha de alocação
    }
}

// COMMIT DA BRUNA

// Função para liberar a memória quando o programa terminar
void freeMachines() {
    free(machines);
}



// Função para mostrar o menu
void showMenu() {
	printf("\n--- MENU PRINCIPAL - Aplicação para KeepItSimple Solutions (KS) ---\n");
    printf("\nMenu de Opções:\n");
    printf("1. Carregar configuração de um arquivo (USAC12)\n");
    printf("2. Exportar operações de uma máquina (USAC13)\n");
    printf("3. Adicionar máquina (USAC14)\n");
    printf("4. Remover máquina (USAC14)\n");
    printf("5. Verificar status das máquinas (USAC14)\n");
    printf("6. Atribuir operação a uma máquina (USAC15)\n");
    printf("7. Controlar máquinas (USAC16 / USAC18)\n");
    printf("8. Instruir máquinas (USAC17 / USAC18)\n");
    printf("9. Sair\n");
}

// MAIN
int main() {
    const char *serialPort = "/dev/ttyS0";
    int fd = configureSerialPort(serialPort);

    // Aloca a memória para as máquinas
    allocateMachines();

    int option = 0;  // Inicializa a variável option com um valor padrão (0)

    do {
        // Exibe o menu
        showMenu();
        printf("Escolha uma opção: ");

        // Lê a entrada do usuário e valida
        if (scanf("%d", &option) != 1) {
            // Limpa a entrada inválida
            while (getchar() != '\n');
            printf("Entrada inválida. Por favor, insira um número válido.\n");
            continue;
        }

        // Executa a ação baseada na opção
        switch (option) {
            case 1:
                loadConfigFromFile();
                break;
            case 2:
                exportMachineOperations();
                break;
            case 3:
                addMachine();
                break;
            case 4:
                removeMachine();
                break;
            case 5:
                checkMachineStatus();
                break;
            case 6:
                assignOperation();
                break;
            case 7:
                controlMachines(fd);
                break;
            case 8:
                instructMachines(fd);
                break;
            case 9:
                printf("Saindo do programa...\n");
                break;
            default:
                printf("Entrada inválida. Tente novamente.\n");
                break;
        }

    } while (option != 9);

    // Libera a memória alocada para as máquinas antes de sair
    freeMachines();

    return 0;
}
