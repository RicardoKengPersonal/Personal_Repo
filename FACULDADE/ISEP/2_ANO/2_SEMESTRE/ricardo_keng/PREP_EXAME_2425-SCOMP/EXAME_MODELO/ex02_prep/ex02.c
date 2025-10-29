#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

#define NUM_LEITORES 3
#define NUM_ESCRITORES 2

typedef struct {
    char mensagem1[100];
    char mensagem2[100];
} MemPartilhada;

MemPartilhada memoria;

int leitores_ativos = 0;
int escritores_ativos = 0;
int escritores_esperando = 0;

pthread_mutex_t mutex;
pthread_cond_t cond_leitor;
pthread_cond_t cond_escritor;

// Função para obter a hora actual como string
void hora_atual(char *dest, int tamanho) {
    time_t agora = time(NULL);
    strftime(dest, tamanho, "%H:%M:%S", localtime(&agora));
}

// Função da thread leitora
void *leitor(void *arg) {
    int id = *(int *)arg;
    free(arg);

    while (1) {
        pthread_mutex_lock(&mutex);

        // Espera enquanto houver escritores activos
        while (escritores_ativos > 0) {
            pthread_cond_wait(&cond_leitor, &mutex);
        }

        leitores_ativos++;
        printf("[Leitor %d] entrou (ativos: %d)\n", id, leitores_ativos);

        pthread_mutex_unlock(&mutex);

        // Leitura das mensagens
        printf("[Leitor %d] leu: \"%s\" e \"%s\"\n", id, memoria.mensagem1, memoria.mensagem2);
        sleep(1);

        pthread_mutex_lock(&mutex);

        leitores_ativos--;
        printf("[Leitor %d] saiu (ativos: %d)\n", id, leitores_ativos);

        // Se for o último leitor a sair, acordar um escritor
        if (leitores_ativos == 0) {
            pthread_cond_signal(&cond_escritor);
        }

        pthread_mutex_unlock(&mutex);

        // Pausa aleatória antes da próxima leitura
        sleep(rand() % 3 + 1);
    }

    return NULL;
}

// Função da thread escritora
void *escritor(void *arg) {
    int id = *(int *)arg;
    free(arg);

    while (1) {
        pthread_mutex_lock(&mutex);

        escritores_esperando++;
        // Espera enquanto houver leitores ou escritores activos
        while (leitores_ativos > 0 || escritores_ativos > 0) {
            pthread_cond_wait(&cond_escritor, &mutex);
        }

        escritores_esperando--;
        escritores_ativos = 1;
        printf(">>> [Escritor %d] entrou (leitores: %d)\n", id, leitores_ativos);

        pthread_mutex_unlock(&mutex);

        // Escrita na memória partilhada
        char hora[64];
        hora_atual(hora, sizeof(hora));
        snprintf(memoria.mensagem1, 100, "Escritor %d", id);
        snprintf(memoria.mensagem2, 100, "Hora: %s", hora);

        sleep(2);

        pthread_mutex_lock(&mutex);
        escritores_ativos = 0;
        printf("<<< [Escritor %d] saiu\n", id);

        // Acordar leitores com prioridade
        pthread_cond_broadcast(&cond_leitor);

        // Se houver escritores à espera, acordar um deles
        if (escritores_esperando > 0) {
            pthread_cond_signal(&cond_escritor);
        }

        pthread_mutex_unlock(&mutex);

        // Pausa aleatória antes da próxima escrita
        sleep(rand() % 4 + 2);
    }

    return NULL;
}

// Função principal
int main() {
    srand(time(NULL));
    strcpy(memoria.mensagem1, "Inicial");
    strcpy(memoria.mensagem2, "Mensagem");

    // Inicializar mutex e variáveis de condição
    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&cond_leitor, NULL);
    pthread_cond_init(&cond_escritor, NULL);

    pthread_t leitores[NUM_LEITORES];
    pthread_t escritores[NUM_ESCRITORES];

    for (int i = 0; i < NUM_LEITORES; i++) {
        int *id = malloc(sizeof(int));
        *id = i;
        pthread_create(&leitores[i], NULL, leitor, id);
    }

    for (int i = 0; i < NUM_ESCRITORES; i++) {
        int *id = malloc(sizeof(int));
        *id = i;
        pthread_create(&escritores[i], NULL, escritor, id);
    }

    // Este código não termina devido ao ciclo infinito nas threads
    for (int i = 0; i < NUM_LEITORES; i++) {
        pthread_join(leitores[i], NULL);
    }

    for (int i = 0; i < NUM_ESCRITORES; i++) {
        pthread_join(escritores[i], NULL);
    }

    // Destruir mutex e variáveis de condição (boa prática, mesmo que não seja atingido)
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond_leitor);
    pthread_cond_destroy(&cond_escritor);
    return 0;
}