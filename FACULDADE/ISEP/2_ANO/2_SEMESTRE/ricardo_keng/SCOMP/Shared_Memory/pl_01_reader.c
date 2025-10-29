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
    char name[20];
    char address[30];
}shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    int local_number;
    char local_name[20];
    char local_address[20];

    /* opens shared memory area */
    if((fd = shm_open("/shm_ex1_PL4",O_RDWR,0)) == -1)
    {
        perror("shmopen");
        exit(1);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    /*Reads number from shm*/
    local_number == shm->number;
    /* reads string from shm */
    strcpy(local_name,shm->name);
    strcpy(local_address,shm->address);
    printf("Reader: reading from writer: number: %d\n name: %s\n address: %s\n",local_number,local_name,local_address);
    exit(0);
}
