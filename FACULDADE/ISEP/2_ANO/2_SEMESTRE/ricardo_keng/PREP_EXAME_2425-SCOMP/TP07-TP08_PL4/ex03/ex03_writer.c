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

    // create or open the shared memory
    if((fd = shm_open("/shm_ex03",O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1)
    {
        perror("Shm_open");
        exit(EXIT_FAILURE);
    }


    //Define the size

    if (ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    shm->data_available = 0; // data not available yet

    srand(time(NULL));

    for(int i = 0; i < 10; i++)
    {
        shm->vec[i] = rand() % 21;
        printf("[Writer]: Vec[%d] = %d\n",i,shm->vec[i]);   
    }

    shm->data_available = 1; // Data available to read
    printf("[Writer]: There is data available.\n");

    exit(0);
}