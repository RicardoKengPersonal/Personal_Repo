#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct {
    int number;
    char name[80];
    int can_read_flag;
} shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    int local_number;
    char local_name[80];

    if((fd = shm_open("shm_ex01",O_RDWR,0)) == -1)
    {
        perror("Shm_open");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ | PROT_WRITE,MAP_SHARED,fd,0) == MAP_FAILED ))
    {
        perror("mmap.");
        exit(EXIT_FAILURE);
    }
    
    while(shm->can_read_flag == 0)
    {
        // Wait until the writer has written data
        printf("Reader: Waiting for the writer to write data...\n");
        fflush(stdout);
        // Sleep for a while to avoid busy waiting
        sleep(1);
    }

    strcpy(local_name,shm->name);
    printf("Reader: I got %s from the shared memory.\n",local_name);

    local_number = shm->number;

    printf("Reader: I got %d from the shared memory.\n",local_number);

    return 0;
}