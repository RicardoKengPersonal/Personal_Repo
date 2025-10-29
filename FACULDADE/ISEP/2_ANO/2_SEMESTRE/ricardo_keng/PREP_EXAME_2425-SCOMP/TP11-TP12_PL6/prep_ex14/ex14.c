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

#define BUFFER_CAP 10
#define MAX_EXCHANGES 30

pthread_mutex_t mutex;
pthread_cond_t wrote_in_buffer;

int buffer[BUFFER_CAP];
int in = 0;
int buffer_full = 0;
int buffer_count = 0;
int current_count = 0;
int new_data = 0;

void* producer_func(void* arg)
{
    (void*) arg; // mark as unused

    pthread_mutex_lock(&mutex); // ensure mutual exclusion

    buffer[in] = in + 1; // produce numbers into the buffer
    buffer_count++; // elements exchanged (produced) MAX 30!
    current_count++;

    pthread_cond_signal(&wrote_in_buffer); //signal that number was written in the buffer

    pthread_unlock(&mutex);

    pthread_exit(NULL);
}

void* consumer_func(void* arg)
{
    (void* )arg; //mark as unused;

    pthread_mutex_lock(&mutex);

    while(!new_data)
    {
        pthread_cond_wait(&wrote_in_buffer,&mutex);
    }

    printf("[Consumer] : read %d from the buffer.\n",buffer[0]);

    buffer_count++; //update numbers exchanged
    current_count--;

    for(int i = 0; i < BUFFER_CAP; i++)
    {

    }
}

int main()
{
    pthread_t producer1,producer2,consumer; // create 3 threads, two producers and one consumer

    pthread_mutex_init(&mutex,NULL);
    pthread_cond_init(&wrote_in_buffer,NULL);

    pthread_create(&producer1,NULL,producer_func,NULL);
    pthread_create(&producer2,NULL,producer_func,NULL);
    pthread_create(&consumer,NULL,consumer_func,NULL);

    pthread_join(producer1,NULL);
    phtread_join(producer2,NULL);
    pthread_join(consumer,NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&wrote_in_buffer);

    exit(0);
}