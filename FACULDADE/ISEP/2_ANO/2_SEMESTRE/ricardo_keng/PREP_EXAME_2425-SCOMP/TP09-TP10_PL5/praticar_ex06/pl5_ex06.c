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
    sem_t *sem_parent,*sem_child;

    if((sem_parent = sem_open("/sem_ex06_parent",O_CREAT,0644,1)) == SEM_FAILED) // father unlocked so father goes first
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    if((sem_child = sem_open("/sem_ex06_child",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open"),
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {
        for(int i = 0; i < 10; i++)
        {
            sem_wait(sem_parent); // wait for permission for father to go
            printf("I am the parent (MESSAGE NUMBER : %d)\n",i);
            sem_post(sem_child); // green light for the child to go
        }
    } else {
        for(int i = 0; i < 10; i++)
        {
            sem_wait(sem_child); // wait for green light
            printf("I am the child (MESSAGE NUMBER: %d)\n",i);
            sem_post(sem_parent); // give green light to father
        }
        exit(0);
    }

    wait(NULL);

    sem_unlink("sem_ex06_parent");
    sem_unlink("sem_ex06_child");

    exit(0);
}