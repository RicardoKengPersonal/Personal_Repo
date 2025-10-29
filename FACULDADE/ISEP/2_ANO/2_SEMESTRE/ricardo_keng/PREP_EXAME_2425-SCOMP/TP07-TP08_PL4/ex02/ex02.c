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


typedef struct {
    int num1;
    int num2;
    int can_read;
    int iterations;
} shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    pid_t pid;

        /* creates/opens shared memory area */
    if((fd = shm_open("/shm_ex02",O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) == -1)
    {
        perror("shm_open failed.");
        exit(EXIT_FAILURE);
    }

        /* defines size of shm */

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

        /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(3);
    }

    shm->num1 = 1000;
    shm->num2 = 500;

    shm->can_read = 1; // Father starts

    shm->iterations = 10;

    pid = fork();

    if(pid == -1)
    {
        perror("Fork Failure.\n");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {
        // Parent
        while(shm->iterations > 0)
        { 
            while(shm->can_read == 0)
            {
                printf("[Father]: Waiting.\n");
            }

            shm->num1++;
            shm->num2--;

            shm->iterations--;

            shm->can_read = 0;
        }

    } else {
        // Child
        while(shm->iterations > 0)
        {
            while(shm->can_read == 1)
            {
                printf("[Child]: Waiting.\n");
            }

            shm->num1--;
            shm->num2++;

            shm->iterations--;

            shm->can_read = 1;
        }
        exit(0);
    }

    wait(NULL);

    printf("Final resutl: num1 = %d || num2 = %d\n",shm->num1,shm->num2);

    return 0;
}