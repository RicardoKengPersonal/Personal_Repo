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

#define READERS 5
#define WRITERS 5

typedef struct{
    char string1[50];
} shared_data_t;

pthread_mutex_t mutex;
pthread_cond_t can_write;
int active_reader_count = 0;

pthread_mutex_t print_mutex;

void* readers_func(void* arg)
{
    shared_data_t *shm = (shared_data_t*)arg;

    pthread_mutex_lock(&mutex);
    active_reader_count++;
    pthread_mutex_unlock(&mutex);

    char buffer[256];
    int len = snprintf(buffer,sizeof(buffer),"[READER]: String: %s\t Active readers: %d\n",shm->string1,active_reader_count);
    write(STDOUT_FILENO,buffer,len);

    // done reading so decrement the active reader_count and unlock for others to print

    pthread_mutex_lock(&mutex);
    active_reader_count--;

    if(active_reader_count == 0)
    {
        pthread_cond_signal(&can_write);
    }

    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int active_writer_count = 0;

void* writers_func(void* arg)
{
    shared_data_t *shm = (shared_data_t*)arg;

    pthread_mutex_lock(&mutex);
    while(active_reader_count > 0 || active_writer_count > 0)
        pthread_cond_wait(&can_write, &mutex);

    active_writer_count++;

    time_t now = time(NULL);
    char timebuf[26];
    ctime_r(&now, timebuf);
    snprintf(shm->string1, sizeof(shm->string1), "Writer %lu at %s", (unsigned long)pthread_self(), timebuf);

    pthread_mutex_lock(&print_mutex);
    char buffer[256];
    int len = snprintf(buffer, sizeof(buffer), "[WRITER]: Wrote string. Active writers: %d, Active readers: %d\n", active_writer_count, active_reader_count);
    write(STDOUT_FILENO, buffer, len);
    pthread_mutex_unlock(&print_mutex);

    active_writer_count--;

    pthread_cond_broadcast(&can_write);
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int main()
{
    pthread_t readers[READERS];
    pthread_t writers[WRITERS];

    shared_data_t *shm;
    int fd;

    if((fd = shm_open("/shm_ex15",O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ | PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    pthread_mutex_init(&print_mutex,NULL);

    strcpy(shm->string1, "Initial string from shared memory");

    int max_threads = READERS > WRITERS ? READERS : WRITERS;
    
    for (int i = 0; i < max_threads; i++) 
    {
        if (i < READERS)
            pthread_create(&readers[i], NULL, readers_func, shm);
        if (i < WRITERS)
            pthread_create(&writers[i], NULL, writers_func, shm);
    }

    for(int i = 0; i < READERS; i++)
    {
        pthread_join(readers[i],NULL);
    }

    for(int i = 0; i < WRITERS; i++)
    {
        pthread_join(writers[i],NULL);
    }

    close(fd);
    munmap(shm,sizeof(shared_data_t));
    shm_unlink("/shm_ex15");

    exit(0);
}