#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

typedef struct{
    int number;
    char name[100];
    int grade;
}Data;

void *thread_printing(void* arg)
{
    Data data = *((Data*)arg);
    free (arg);

    char buffer[300];

    int len = snprintf(buffer,sizeof(buffer),"Student number: %d\nStudent name: %s\nStudent grade: %d\n",data.number,data.name,data.grade);
    write(STDOUT_FILENO,buffer,len);

    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[5]; // 5 threads

    Data vec[5] = {     // hold 5 elements of struct type
        {1,"John",12},
        {2,"Paul",13},
        {3,"Biggie",14},
        {4,"Bitch",15},
        {5,"Ricardo",16}
    };

    Data *arg;

    for (int i = 0; i < 5; i++)
    {
        // loop for each thread to print each element
        arg = malloc(sizeof(vec[i]));
        *arg = vec[i];

        pthread_create(&threads[i],NULL,thread_printing,(void*)arg);
    }

    for(int i = 0; i < 5; i++)
    {
        pthread_join(threads[i],NULL); // wait for the all threads to terminate
    }

    exit(0);
}