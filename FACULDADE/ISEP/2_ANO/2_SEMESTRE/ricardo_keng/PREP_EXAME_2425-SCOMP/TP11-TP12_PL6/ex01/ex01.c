#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>


void* thread_func(void* arg)
{
    char *name = (char *)arg;

    write(STDOUT_FILENO,name,strlen(name));

    free(arg);
    pthread_exit(NULL);
}

int main()
{
    pthread_t thread[2]; // two threads

    char first_name[] = "Ricardo\t";
    char last_name[] = "Silva\n";

    char* arg;

    //thread 1
    arg = malloc(strlen(first_name) + 1);
    strcpy(arg,first_name);
    pthread_create(&thread[0],NULL,thread_func,(void*)arg);

    //thread 2
    arg = malloc(strlen(last_name) + 1);
    strcpy(arg,last_name);
    pthread_create(&thread[1],NULL,thread_func,(void*)arg);

    for(int i = 0; i < 2; i++)
    {
        pthread_join(thread[i],NULL); // wait for threads to finish
    }

    exit(0);
}