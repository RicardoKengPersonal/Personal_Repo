#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

#define SIZE 1000

typedef struct {
    int *array;
    int size;       
} ThreadArgs;

// global variables
int global_max = -1;
int global_min = 10000000;
double global_avg = 0.0;

void* search_max(void* arg)
{
    ThreadArgs *args = (ThreadArgs*)arg;
    int *array = args->array;
    int max = array[0];
    for(int i = 1; i < args->size; i++)
    {
        if(array[i] > max)
        {
            max = array[i];
        }
    }
    global_max = max;
    free(arg);
    pthread_exit(NULL);
}

void* search_min(void* arg)
{
    ThreadArgs *args = (ThreadArgs*)arg;
    int *array = args->array;
    int min = array[0];
    for(int i = 1; i < args->size; i++)
    {
        if(array[i] < min)
        {
            min = array[i];
        }
    }
    global_min = min;
    free(arg);
    pthread_exit(NULL);
}

void* avg_balance(void *arg)
{
    ThreadArgs *args = (ThreadArgs*)arg;
    int *array = args->array;
    long sum = 0;
    for(int i = 0; i < args->size; i++)
    {
        sum += array[i];
    }
    global_avg = (double)sum / args->size;
    free(arg);
    pthread_exit(NULL);
}

int main()
{
    srand(time(NULL));

    pthread_t threads[3];

    int *array = malloc(SIZE * sizeof(int));
    if (array == NULL) {
        perror("malloc failed");
        exit(1);
    }

    for(int i = 0; i < SIZE; i++)
    {
        array[i] = rand() % 1500 + 1;
    }

    // Prepare and launch threads
    for (int i = 0; i < 3; i++) {
        ThreadArgs *args = malloc(sizeof(ThreadArgs));
        args->array = array;
        args->size = SIZE;
        if (i == 0)
            pthread_create(&threads[i], NULL, search_max, (void*)args);
        else if (i == 1)
            pthread_create(&threads[i], NULL, search_min, (void*)args);
        else
            pthread_create(&threads[i], NULL, avg_balance, (void*)args);
    }

    for(int i = 0; i < 3; i++)
    {
        pthread_join(threads[i], NULL);
    }

    char buffer[200];
    int len = snprintf(buffer, sizeof(buffer),
        "Max found: %d\nMin found: %d\nAverage Balance: %.2f\n",
        global_max, global_min, global_avg);
    write(STDOUT_FILENO, buffer, len);

    free(array);
    return 0;
}