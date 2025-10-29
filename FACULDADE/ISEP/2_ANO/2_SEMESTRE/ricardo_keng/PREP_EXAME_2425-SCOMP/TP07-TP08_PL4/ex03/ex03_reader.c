#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <time.h>

typedef struct {
    int vec[10];
    int data_available;
}shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    float avg;

    /* opens shared memory area */
    if((fd = shm_open("/shm_ex03",O_RDWR,0)) == -1)
    {
        perror("Shm_open");
        exit(1);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(3);
    }

    while(shm->data_available == 0)
    {
        printf("[Reader]: Waiting until there is data available.\n");
        sleep(1);
    }

    for (int i = 0; i < 10; i++)
    {
        printf("[Reader]: Vec[%d] = %d\n",i,shm->vec[i]);

        avg += shm->vec[i];
    }

    avg = avg / 10.0;

    printf("[Reader]: The average i got is : %.2f\n",avg);

    exit(0);
    
}