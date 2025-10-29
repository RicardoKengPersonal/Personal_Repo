#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

#define SIZE 1000
#define NTHREADS 5

pthread_mutex_t mutex;
pthread_cond_t cond;
int current_thread = 1;

typedef struct {
    int *data;
    int *result;
    int start;
    int end;
    int thread_num;
}Args;

void* calculate_partial(void* arg)
{
    Args func_struct = *((Args*)arg);
    free(arg);

    for(int i = func_struct.start; i < func_struct.end; i++)
    {
        func_struct.result[i] = (func_struct.data[i] * 10) + 2; 
    }

    pthread_mutex_lock(&mutex);

    while(func_struct.thread_num != current_thread)
    {
        pthread_cond_wait(&cond,&mutex);
    }

    char buffer[100];

    for(int i = func_struct.start; i < func_struct.end; i++)
    {
        int len = snprintf(buffer,sizeof(buffer),"Thread %d result[%d] = %d\n", func_struct.thread_num,i,func_struct.result[i]);
        write(STDOUT_FILENO,buffer,len);
    }

    current_thread++;
    pthread_cond_broadcast(&cond);
    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

int main()
{
    int data[SIZE];
    int result[SIZE];

    pthread_mutex_init(&mutex,NULL);; //initiate mutex
    pthread_cond_init(&cond,NULL); // initiate condition variable

    Args *arg;

    srand(time(NULL));

    for(int i = 0; i < SIZE; i++)
    {
        data[i] = rand() % 1000 + 1; // fill the array with random values
    }

    pthread_t threads[NTHREADS]; // create 5 threads;

    for(int i = 0; i < NTHREADS; i++) // for each thread
    {
        arg = malloc(sizeof(Args));
        arg->data = data;
        arg->result = result;
        arg->start = i * (SIZE / 5);
        arg->end = ( i + 1) * (SIZE/5);
        arg->thread_num = i + 1;

        pthread_create(&threads[i],NULL,calculate_partial,(void*)arg);
    }

    for(int i = 0; i < NTHREADS; i++)
    {
        pthread_join(threads[i],NULL);
    }

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond);

    exit(0);
}