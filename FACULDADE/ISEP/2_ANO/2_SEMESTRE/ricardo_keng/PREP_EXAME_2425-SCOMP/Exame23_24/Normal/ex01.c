#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>

#define MAX_JOBS 4
#define NUM_CLIENTS 5
#define NUM_PRINTERS 3

// Estrutura do trabalho de impressão
typedef struct {
    int job_id;
    char client_name[50];
} PrintJob;

// Buffer circular para fila de impressão
PrintJob queue[MAX_JOBS];
int head = 0, tail = 0, count = 0;

// Mutex e variáveis de condição
pthread_mutex_t mutex;
pthread_cond_t not_full;
pthread_cond_t not_empty;

// Gerador de IDs de trabalho
int job_counter = 1;

// Função para adicionar trabalho na fila
void add_job(PrintJob job) 
{
    queue[tail] = job;
    tail = (tail + 1) % MAX_JOBS;
    count++;
}

// Função para remover trabalho da fila
PrintJob remove_job() 
{
    PrintJob job = queue[head];
    head = (head + 1) % MAX_JOBS;
    count--;
    return job;
}

// Thread cliente
void* client_thread(void* arg) 
{
    char* client_name = (char*)arg;

    while (1) 
    {
        sleep(2);

        pthread_mutex_lock(&mutex);

        while (count == MAX_JOBS) 
        {
            pthread_cond_wait(&not_full, &mutex);
        }

        PrintJob job;
        job.job_id = job_counter++;
        snprintf(job.client_name, sizeof(job.client_name), "%s", client_name);

        add_job(job);

        printf("[CLIENTE %s] Adicionou trabalho %d à fila\n", job.client_name, job.job_id);

        pthread_cond_signal(&not_empty);
        pthread_mutex_unlock(&mutex);
    }

    return NULL;
}

// Thread impressora
void* printer_thread(void* arg) 
{
    int printer_id = *(int*)arg;
    free(arg);

    while (1) 
    {
        pthread_mutex_lock(&mutex);

        while (count == 0) 
        {
            pthread_cond_wait(&not_empty, &mutex);
        }

        PrintJob job = remove_job();

        printf("[IMPRESSORA %d] Processando trabalho %d de %s...\n", printer_id, job.job_id, job.client_name);

        pthread_cond_signal(&not_full);
        pthread_mutex_unlock(&mutex);

        sleep(5); // tempo para processar o trabalho

        printf("[IMPRESSORA %d] Concluiu trabalho %d de %s\n", printer_id, job.job_id, job.client_name);
    }

    return NULL;
}

int main() 
{
    pthread_t clients[NUM_CLIENTS];
    pthread_t printers[NUM_PRINTERS];

    // Criar threads cliente
    for (int i = 0; i < NUM_CLIENTS; i++) 
    {
        char* name = malloc(16);
        snprintf(name, 16, "Cliente_%d", i + 1);
        pthread_create(&clients[i], NULL, client_thread, name);
    }

    // Criar threads impressora
    for (int i = 0; i < NUM_PRINTERS; i++) 
    {
        int* id = malloc(sizeof(int));
        *id = i + 1;
        pthread_create(&printers[i], NULL, printer_thread, id);
    }

    // Nunca termina
    for (int i = 0; i < NUM_CLIENTS; i++) 
    {
        pthread_join(clients[i], NULL);
    }

    for (int i = 0; i < NUM_PRINTERS; i++) 
    {
        pthread_join(printers[i], NULL);
    }

    return 0;
}
