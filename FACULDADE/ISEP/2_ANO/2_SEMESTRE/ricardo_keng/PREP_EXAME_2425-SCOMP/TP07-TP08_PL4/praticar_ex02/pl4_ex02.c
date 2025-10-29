#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <sys/wait.h>

typedef struct{
    int num1;
    int num2;
    int iterations;
    int waiting_flag;
}shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    pid_t pid;

    if((fd = shm_open("/praticar_ex02",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("Ftruncate");
        exit(EXIT_FAILURE);
    } 

    if((shm = (shared_data_t*)mmap(NULL,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    shm->num1 = 500;
    shm->num2 = 1000;
    shm->iterations = 10;
    pid = fork();

    if(pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {
        // FATHER
        for(int i = 0; i < shm->iterations ; i++)
        {
            while(shm->waiting_flag == 1)
            {
                printf("[FATHER]: Waiting....\n");
                sleep(1);
            }
            shm->iterations--;

            shm->num1++;
            shm->num2--;
            shm->waiting_flag++;

        }
    } else {
        //CHILD
        for(int i = 0; i < shm->iterations; i++)
        {
            while(shm->waiting_flag == 0)
            {
                printf("[CHILD]: Waiting...\n");
                sleep(1);
            }
            shm->iterations--;

            shm->num2++;

            shm->waiting_flag--;
        }

        exit(0);
    }

    wait(NULL);

    printf("[FATHER]: Final values:\nNum1 = %d\nNum2 = %d\n",shm->num1,shm->num2);

    exit(0);
}