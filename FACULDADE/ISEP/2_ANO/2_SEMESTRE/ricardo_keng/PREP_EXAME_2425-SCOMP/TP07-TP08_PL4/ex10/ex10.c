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

typedef struct{
    int circular_buffer[10];
}shared_data_t;

int main()
{
    pid_t pid;
    int fd;
    shared_data_t *shm;

    if((fd = shm_open("/ex10_shm",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(NULL,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(pid == 0)
    {
        //consumer
    } else {
        // producer
        
    }

}