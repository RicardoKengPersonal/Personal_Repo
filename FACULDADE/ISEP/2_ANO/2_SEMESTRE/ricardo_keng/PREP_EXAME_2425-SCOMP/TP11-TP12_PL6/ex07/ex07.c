#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

pthread_mutex_t mutex;
pthread_cond_t cond;

int drinks_bought = 0;
int chips_bought = 0;

void* buy_chips()
{
    pthread_mutex_lock(&mutex);

    char buffer[200];

    int len = snprintf(buffer,sizeof(buffer),"Buying the drinks.\n");
    write(STDOUT_FILENO,buffer,len);

    sleep(3); // simulate the delay of buying the drinks

    chips_bought = 1;

    pthread_cond_broadcast(&cond);

    while(drinks_bought == 0)
    {
        pthread_cond_wait(&cond,&mutex);
    }

    len = snprintf(buffer,sizeof(buffer),"T1: Eating and drinking\n");
    write(STDOUT_FILENO,buffer,len);

    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

void* buy_drink()
{
    pthread_mutex_lock(&mutex);

    char buffer[256];

    int len = snprintf(buffer,sizeof(buffer),"Buying the chips.\n");
    write(STDOUT_FILENO,buffer,len);

    sleep(7);

    drinks_bought = 1;

    pthread_cond_broadcast(&cond); // signal that drinks were bought

    while(chips_bought == 0)
    {
        pthread_cond_wait(&cond,&mutex);
    }

    len = snprintf(buffer,sizeof(buffer),"T2: Eating and drinking\n");
    write(STDOUT_FILENO,buffer,len);

    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[2]; // create two threads

    pthread_mutex_init(&mutex,NULL);
    pthread_cond_init(&cond,NULL);

    pthread_create(&threads[0],NULL,buy_chips,NULL);
    pthread_create(&threads[1],NULL,buy_drink,NULL);

    pthread_join(threads[0],NULL);
    pthread_join(threads[1],NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond);

    return 0;
}