#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

#define N 5

typedef struct{
    int number;
    char name[50];
    float grade;
}Data_struct;

pthread_mutex_t mutex; // just to make sure

void* thread_func(void* arg)
{
    Data_struct func_struct = (*(Data_struct*)arg);
    free(arg);

    pthread_mutex_lock(&mutex);

    printf("Student Number: %d, Name : %s , Grade: %.2f\n",func_struct.number,func_struct.name,func_struct.grade);
    
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[N];

    pthread_mutex_init(&mutex,NULL);

    Data_struct data[5]={
        {1,"Ricardo",12.00},
        {2,"Joao",13.00},
        {3,"Paulo",9.95},
        {4,"Diogo",8.45},
        {5,"Pedro",9.00}
    };

    for(int i = 0; i < N; i++)
    {
        Data_struct *arg;
        arg = malloc(sizeof(data[i]));
        *arg = data[i];
        pthread_create(&threads[i],NULL,thread_func,(void*)arg);
    }

    for(int i = 0; i < N; i++)
    {
        pthread_join(threads[i],NULL);
    }

    pthread_mutex_destroy(&mutex);

    exit(0);
}