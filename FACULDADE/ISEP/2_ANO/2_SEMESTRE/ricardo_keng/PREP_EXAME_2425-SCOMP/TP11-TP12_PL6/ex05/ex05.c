#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

int global_max = 0;
int global_min = 0;
float avg = 0.0;

void* compute_max(void* arg)
{
    int *values = (int*)arg;

    global_max = values[0];

    for(int i = 0; i < 1000; i++)
    {
        if(global_max < values[i])
        {
            global_max = values[i];
        }
    }
    pthread_exit(NULL);
}

void* compute_min(void* arg)
{

    int* values = (int*)arg;

    global_min = values[0];

    for(int i = 0; i < 1000; i++)
    {
        if(global_min > values[i])
        {
            global_min = values[i];
        }
    }
    pthread_exit(NULL);
}

void* compute_avg (void* arg)
{
    float sum = 0;
    int* values = (int*)arg;

    for(int i = 0; i < 1000; i++)
    {
        sum += values[i];
    }
    avg = sum / 1000.0;

    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[3];// create 3 threads

    char buffer[200];

    int values[1000];

    for(int i = 0; i < 1000; i++)
    {
        values[i] = rand() % 200000 + 1;
    }

    for(int i = 0; i < 3; i++)
    {
        if(i == 0)
        {
            pthread_create(&threads[i],NULL,compute_max,(void*)values);
        }
        if(i == 1)
        {
            pthread_create(&threads[i],NULL,compute_min,(void*)values);
        }
        if(i == 2)
        {
            pthread_create(&threads[i],NULL,compute_avg,(void*)values);
        }
    }

    for(int i = 0; i < 3; i++)
    {
        pthread_join(threads[i],NULL);
    }

    int len = snprintf(buffer,sizeof(buffer),"Global Max: %d\n Global min: %d\nAverage: %.2f\n",global_max,global_min,avg);
    write(STDOUT_FILENO,buffer,len);

    exit(0);
}