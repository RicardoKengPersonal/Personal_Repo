#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#define N 5
#define SIZE 1000

typedef struct {
    int *array;              // pointer to shared array
    int start;
    int end;
    int number_to_search;
    int thread_num;
} ThreadArgs;

void* search_func(void* arg)
{
    ThreadArgs *func_args = (ThreadArgs*)arg;

    for(int i = func_args->start; i < func_args->end; i++)
    {
        if(func_args->number_to_search == func_args->array[i])
        {
            printf("Number %d found at position: %d by THREAD %d\n",
                   func_args->number_to_search, i, func_args->thread_num);

            // Return thread number via malloc
            int *ret = malloc(sizeof(int));
            *ret = func_args->thread_num;
            pthread_exit(ret);
        }
    }

    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[N];
    ThreadArgs thread_args[N];
    int array[SIZE];
    int number_to_search;
    int *retval = NULL;
    int found = 0;

    srand(time(NULL));

    printf("Enter a number to search for: ");
    scanf("%d", &number_to_search);

    // Fill array with random numbers
    for (int i = 0; i < SIZE; i++)
        array[i] = rand() % 1000 + 1;

    // Create threads
    for (int i = 0; i < N; i++)
    {
        thread_args[i].array = array;
        thread_args[i].start = i * (SIZE / N);
        thread_args[i].end = (i + 1) * (SIZE / N);
        thread_args[i].number_to_search = number_to_search;
        thread_args[i].thread_num = i + 1;

        pthread_create(&threads[i], NULL, search_func, &thread_args[i]);
    }

    // Join threads
    for (int i = 0; i < N; i++)
    {
        void *res;
        pthread_join(threads[i], &res);

        if (res != NULL)
        {
            retval = (int*)res;
            found = 1;
            printf("Thread that found the number: %d (USING THE RETURN VALUE)\n", *retval);
            free(retval); // clean up
        }
    }

    if (!found)
        printf("Number not found in the array\n");

    exit (0);
}
