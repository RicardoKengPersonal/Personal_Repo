#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include<semaphore.h>

int main()
{
    pid_t pid;
    sem_t *sem;

    if((sem = sem_open("/sem_ex05",O_CREAT,0644,0)) == SEM_FAILED) // start value for the semaphore is 0, because we want to synch the processes
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("Fork Failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    {
        printf("I am the child.\n");
        sem_wait(sem); // waits for the parent notification 
        exit(0);
    } else {
        printf("I am the father\n");
        sem_post(sem); // increments the semaphore by 1, meaning its ready to be used
    }

    wait(NULL);

    sem_unlink("/sem_ex05");

    exit(0);
}