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
    sem_t *sem; // initialize the sempahore

     // Create the semaphore with initial value 0 (child blocks)
    if((sem = sem_open("/sem_ex5",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open");
        exit(4);
    }

    pid = fork();

    if (pid < 0)
    {
        perror("Fork failure");
        sem_unlink("/sem_ex5");
        exit(1);
    }

    if (pid > 0) // parent
    {
        printf("[Father]: I am the parent.\n");
        sem_post(sem);
        wait(NULL); // wait for child 

        // Clean up
        sem_close(sem);
        sem_unlink("/sem_ex5");

    } else { //Child
        
        sem_wait(sem);

        printf("[Child]: I am the child.\n");

        sem_close(sem);
    }

    return 0;

}