#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <semaphore.h>
#include <sys/mman.h>

int main()
{
    pid_t pid1,pid2,pid3;

    sem_t *sem1,*sem2,*sem3;

    char msg1[]="Sistemas ";
    char msg2[]="de ";
    char msg3[]="computadores ";
    char msg4[]="is ";
    char msg5[]="the ";
    char msg6[]="best ";

    if((sem1 = sem_open("/ex07_sem1",O_CREAT,0644,1)) == SEM_FAILED) // Child 1 starts
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    if((sem2 = sem_open("/ex07_sem2",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    if((sem3 = sem_open("/ex07_sem3",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    pid1 = fork();

    if(pid1 == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(pid1 == 0)
    {
        sem_wait(sem1);
        printf("%s\n",msg1);
        fflush(stdout);

        sem_post(sem2); // green light for child2

        sem_wait(sem1); // wait for green light
        printf("%s\n",msg4);
        fflush(stdout);

        sem_post(sem2); // green light for child2

        exit(0);
    }

    pid2 = fork();

    if(pid2 == -1)
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    if(pid2 == 0)
    {
        sem_wait(sem2); // wait for greenlight
        printf("%s\n",msg2);
        fflush(stdout);

        sem_post(sem3); // green light for child3

        sem_wait(sem2); // wait for greenlight
        printf("%s\n",msg5);

        sem_post(sem3); // green light for child3

        exit(0);
    }

    pid3 = fork();

    if(pid3 == -1)
    {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    if(pid3 == 0)
    {
        sem_wait(sem3); // wait for green
        printf("%s\n",msg3);
        fflush(stdout);

        sem_post(sem1); // green for child1

        sem_wait(sem3);
        printf("%s\n",msg6);
        fflush(stdout);

        exit(0);
    }

    for(int i = 0; i < 3; i++)
    {
        wait(NULL);
    }

    shm_unlink("/ex07_sem1");
    shm_unlink("/ex07_sem2");
    shm_unlink("/ex07_sem3");

    exit(0);
}