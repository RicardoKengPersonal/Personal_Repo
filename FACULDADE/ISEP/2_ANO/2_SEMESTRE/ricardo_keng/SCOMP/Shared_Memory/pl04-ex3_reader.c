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
    int number[10];
}shared_data_t;

int main()
{
    int fd;
    int local_array[10];
    shared_data_t *shm;

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

    for(int i = 0; i < 10; i++)
    {
        local_array[i] = shm->number[i];
        printf("local_array[%d] = %d\n",i,local_array[i]);
    }

    printf("Reader done.\n");
}