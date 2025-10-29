#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

void *thread_func(void *arg)
{
    char *str = (char*)arg;
    write(STDOUT_FILENO,str,strlen(str));

    free(arg);
    pthread_exit(NULL);
} 

int main()
{
    pthread_t threads[2]; // create two threads
    char first_name[]="Ricardo ";
    char last_name[]="Silva";

    char *arg;

    //thread 1
    arg = malloc(strlen(first_name) + 1);
    strcpy(arg,first_name);
    pthread_create(&threads[0],NULL,thread_func,(void*)arg);

    //thread 2
    arg = malloc(strlen(last_name) + 1);
    strcpy(arg,last_name);
    pthread_create(&threads[1],NULL,thread_func,(void*)arg);

    for(int i = 0; i < 2; i++)
    {
        pthread_join(threads[i],NULL);
    }

    exit(0);
}