#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

#define SIZE 1000
#define NTHREADS 5

typedef struct{
    int *array_struct;
    int num_to_search;
    int start;
    int end;
    int thread_num;
}Args;

void* search_func(void* arg)
{
    Args func_struct = (*(Args*)arg);
    free(arg);

    int start = func_struct.start;
    int end = func_struct.end;
    int search = func_struct.num_to_search;
    

    char buffer[100];

    for(int i = start; i < end; i++)
    {
        if(search == func_struct.array_struct[i])
        {
            int len = snprintf(buffer, sizeof(buffer),"FOUND %d in position %d\nFound by thread: %d\n",search,i,func_struct.thread_num);
            write(STDOUT_FILENO,buffer,len);

            int *ret = malloc(sizeof(int));
            *ret = func_struct.thread_num;
            pthread_exit(ret);
        }
    }

    pthread_exit(NULL);
}


int main()
{
    srand(time(NULL));

    pthread_t threads[NTHREADS]; // 5 threads

    int array[SIZE];
    int num;

    for(int i = 0; i < SIZE; i++)
    {
        array[i] = i;
    }

    printf("Number to search:");
    scanf("%d",&num);


    for(int i = 0; i < NTHREADS; i++)
    {
        Args *arg = malloc(sizeof(Args));
        arg->array_struct = array;
        arg->num_to_search = num;
        arg->start = (SIZE / NTHREADS) * i;
        arg->end = (i + 1) * (SIZE / NTHREADS);
        arg->thread_num = (i + 1);

        pthread_create(&threads[i],NULL,search_func,(void*)arg);
    }

    for(int i = 0; i < NTHREADS; i++)
    {
        pthread_join(threads[i],NULL);
    }

    int found_by = 0;

    for(int i = 0; i < NTHREADS; i++)
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

    exit(0);
}