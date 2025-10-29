#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

void *thread_func(void* arg)
{
    int local = *((int*)arg);
    free(arg);

    char buffer[100];
    int len = snprintf(buffer,sizeof(buffer),"Number entered: %d\n",local);
    write(STDOUT_FILENO,buffer,len);

    if(local <= 0)
    {
        len = snprintf(buffer,sizeof(buffer),"Number must be greater than 0\n");
        write(STDOUT_FILENO,buffer,len);
        pthread_exit(NULL);
    }

    if(local == 1)
    {
        len = snprintf(buffer,sizeof(buffer),"Number 1 is prime\n");
        write(STDOUT_FILENO,buffer,len);
    }
    else
    {
        for(int i = 2; i <= local; i++)
        {
            int is_prime = 1;
            for(int j = 2; j <= i/2; j++)
            {
                if(i % j == 0)
                {
                    is_prime = 0;
                    break;
                }
            }
            if(is_prime)
            {
                len = snprintf(buffer,sizeof(buffer),"Number %d is prime\n",i);
                write(STDOUT_FILENO,buffer,len);
            }
        }
    }

    len = snprintf(buffer,sizeof(buffer),"Completed execution.\n");
    write(STDOUT_FILENO,buffer,len);

    pthread_exit(NULL);
}

int main()
{
    int num;
    pthread_t t1;

    int *arg;

    printf("Enter a number:");
    scanf("%d",&num);

    arg = malloc(sizeof(int));

    *arg = num;

    pthread_create(&t1,NULL,thread_func,(void*)arg);

    pthread_join(t1,NULL);
}