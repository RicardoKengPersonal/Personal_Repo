#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

#define SIZE 1000
#define THREADS 5

typedef struct {
    int *array;
    int num;
    int start;          // array with the args
    int end;
    int thread_num;
} ThreadArgs; 

void* search_fun(void* arg) 
{
    ThreadArgs *args = (ThreadArgs*)arg;

    for (int i = args->start; i < args->end; i++) 
    {
        if (args->array[i] == args->num)
        {
            char buffer[100];
            int len = snprintf(buffer, sizeof(buffer), "Thread %d found number at position %d\n", args->thread_num, i);
            write(STDOUT_FILENO, buffer, len);

            int *ret = malloc(sizeof(int));
            *ret = args->thread_num;
            free(arg);
            pthread_exit(ret);
        }
    }
    free(arg);
    pthread_exit(NULL);
}

int main() 
{
    srand(time(NULL));
    pthread_t threads[THREADS];
    int array[SIZE];

    // Fill array with unique values
    for (int i = 0; i < SIZE; i++) 
    {
        array[i] = i + 1;
    }

    // Shuffle array to randomize
    for (int i = SIZE - 1; i > 0; i--) 
    {
        int j = rand() % (i + 1);
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    int num;
    printf("Number to search: ");
    scanf("%d", &num);

    // Create threads
    for (int i = 0; i < THREADS; i++) 
    {
        ThreadArgs *args = malloc(sizeof(ThreadArgs));
        args->array = array;
        args->num = num;
        args->start = i * (SIZE / THREADS);
        args->end = (i + 1) * (SIZE / THREADS);
        args->thread_num = i + 1;
        pthread_create(&threads[i], NULL, search_fun,(void*)args);
    }

    // Wait for threads and check results
    int found_by = 0;
    for (int i = 0; i < THREADS; i++) 
    {
        void *ret;
        pthread_join(threads[i], &ret);

        if (ret != NULL) 
        {
            found_by = *(int*)ret;
            free(ret);
        }
    }

    if (found_by)
    {
        printf("Number was found by thread %d.\n", found_by);
    } else 
    {
        printf("Number not found in array.\n");
    }

    return 0;
}