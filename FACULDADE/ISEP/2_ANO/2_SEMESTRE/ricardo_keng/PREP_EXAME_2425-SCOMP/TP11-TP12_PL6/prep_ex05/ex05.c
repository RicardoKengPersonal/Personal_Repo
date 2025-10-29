#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

#define SIZE 1000

int highest = 0;
int lowest = 0;
float avg = 0.0;

void* find_highest(void* arg)
{
    int *func_array = (int*)arg;

    for(int i = 0; i < SIZE; i++)
    {
        if(func_array[i] < highest)
        {
            highest = func_array[i];
        }
    }

    pthread_exit(NULL);
}

void* find_lowest(void *arg)
{
    int *func_array = (int *)arg;

    for(int i = 0; i < SIZE; i++)
    {
        if(func_array[i] < lowest)
        {
            lowest = func_array[i];
        }
    }
}

void* compute_avg(void* arg)
{
    int *func_array = (int *)arg;

    int sum;

    for(int i = 0; i < SIZE;  i++)
    {
        sum += func_array[i];
    }

    avg = sum /(float) SIZE;

    pthread_exit(NULL);
}

int main()
{
    pthread_t thread1,thread2,thread3;

    int array[SIZE];
    srand(time(NULL));

    for(int i = 0; i < SIZE; i++)
    {
        array[i] = rand() % 1000 + 1;
    }

    highest = array[0];
    lowest = array[0];

    pthread_create(&thread1,NULL,find_highest,array);
    pthread_create(&thread2,NULL,find_lowest,array);
    pthread_create(&thread3,NULL,compute_avg,array);

    pthread_join(thread1,NULL);
    pthread_join(thread2,NULL);
    pthread_join(thread3,NULL);

    exit(0);
}