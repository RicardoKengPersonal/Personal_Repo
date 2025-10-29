#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/wait.h>

typedef struct{
    int nums[10];
    int busy;
}shared_data_t;


int main()
{
    int fd;
    shared_data_t *shm;

    if((fd = shm_open("/praticar_pl4_ex03",O_CREAT | O_RDWR,S_IRUSR|S_IWUSR)) == -1)
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

    while(shm->busy == 0)
    {
        printf("[READER]: WAITING FOR DATA....\n");
        sleep(2);
    }

    float avg = 0;

    for(int i = 0; i < 10; i++)
    {
        avg += shm->nums[i];
    }

    avg = avg / 10.0;

    printf("[READER]: Average is %.2f\n",avg);

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    shm_unlink("/praticar_pl4_ex03");

    exit(0);
}