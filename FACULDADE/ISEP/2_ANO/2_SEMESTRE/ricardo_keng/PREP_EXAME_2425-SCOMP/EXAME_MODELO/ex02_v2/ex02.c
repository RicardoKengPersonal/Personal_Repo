#define _POSIX_C_SOURCE 200809L
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <errno.h>
#include <signal.h>
#include <ctype.h>
#include <time.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <pthread.h>
#include <limits.h>

#define NUM_WRITERS 5
#define NUM_READERS 5
#define SHM_NAME "/ex02_shm"

typedef struct {
    char str1[50];
    char str2[50];
} SharedMem;

int writers_active = 0;
int readers_active = 0;
int total_readers = 0;

pthread_mutex_t mutex;
pthread_cond_t writers_cond;
pthread_cond_t readers_cond;

void *writer(void *arg) 
{
    SharedMem shared_mem = (*(SharedMem *)arg);

    pthread_mutex_lock(&mutex);
    while (writers_active > 0 || readers_active > 0) 
    {
        pthread_cond_wait(&writers_cond, &mutex);
    }
    writers_active++;

    char buffer[200];
    time_t t = time(NULL);
    struct tm *hora = localtime(&t);

    snprintf(shared_mem.str1, sizeof(shared_mem.str1), "Thread %lu", pthread_self());
    snprintf(shared_mem.str2, sizeof(shared_mem.str2), "Time: %02d:%02d:%02d",
                 hora->tm_hour, hora->tm_min, hora->tm_sec);
    snprintf(buffer, sizeof(buffer), "[WRITER %lu] Escreveu str1 e str2.\n", pthread_self());
    write(STDOUT_FILENO, buffer, strlen(buffer));

    pthread_cond_broadcast(&readers_cond);
    pthread_cond_signal(&writers_cond);

    writers_active--;

    if (readers_active == 0) 
    {
        pthread_cond_signal(&writers_cond);
    }
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

void *reader(void *arg) 
{
    SharedMem shared_mem = *((SharedMem *)arg);

    pthread_mutex_lock(&mutex);

    while (writers_active > 0) 
    {
        pthread_cond_wait(&readers_cond, &mutex);
    }
    readers_active++;
    char buffer[200]; 
    total_readers++;
    snprintf(buffer, sizeof(buffer), "String 1: %s\nString 2: %s\nReader count: %d\n", shared_mem.str1, shared_mem.str2, total_readers);
    write(STDOUT_FILENO, buffer, strlen(buffer));
    readers_active--;
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int main() 
{
    pthread_t writers[NUM_WRITERS];
    pthread_t readers[NUM_READERS];
    int shm_fd;
    SharedMem *mem;

    // --- SHARED MEMORY SETUP ---
    if ((shm_fd = shm_open("ex02_shm",O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) == -1) 
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }
    if (ftruncate(shm_fd, sizeof(SharedMem)) == -1) 
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if ((mem = (SharedMem*)mmap(0,sizeof(SharedMem),PROT_READ | PROT_WRITE, MAP_SHARED,shm_fd,0))== MAP_FAILED) 
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    strcpy(mem->str1, "Mensagem inicial 1");
    strcpy(mem->str2, "Mensagem inicial 2");

    // --- THREADS ---
    for (int i = 0; i < NUM_READERS; i++) 
    {
        pthread_create(&readers[i], NULL, reader, mem);
    }
    
    for (int i = 0; i < NUM_WRITERS; i++) 
    {
        pthread_create(&writers[i], NULL, writer, mem);
    }

    for (int i = 0; i < NUM_WRITERS; i++) 
    {
        pthread_join(writers[i], NULL);
    }

    for (int i = 0; i < NUM_READERS; i++) 
    {
        pthread_join(readers[i], NULL);
    }

    close(shm_fd);
    munmap(mem, sizeof(SharedMem));
    shm_unlink(SHM_NAME);

    return EXIT_SUCCESS;
}