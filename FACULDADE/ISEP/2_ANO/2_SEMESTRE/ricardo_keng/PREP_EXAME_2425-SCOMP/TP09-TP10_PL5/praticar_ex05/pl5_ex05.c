#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <semaphore.h>
#include <sys/mman.h>


int main()
{
    pid_t pid;
    sem_t *sem;

    if((sem = sem_open("/sem_ex05",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {
        printf("I am the parent.\n");
        sem_post(sem);
    } else {
        sem_wait(sem);
        printf("I am the child.\n");
        exit(0);
    }

    wait(NULL);

    sem_unlink("/sem_ex05");

    exit(0);
}