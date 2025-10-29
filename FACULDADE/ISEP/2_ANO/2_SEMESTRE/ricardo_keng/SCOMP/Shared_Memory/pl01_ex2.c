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
    int var1;
    int var2;
    int can_read;
    int can_write;
}shared_data_t;

int main()
{
    pid_t pid;
    int fd,i = 0,n = 0;
    shared_data_t *shm;

    /* creates/opens shared memory area */
    if((fd = shm_open("/pl02_shm_ex2",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1)
    {
        perror("shmopen");
        exit(1);
    }

    /* defines size of shm */
    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(2);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(3);
    }

    /* synchronization variables */
    shm->can_read = 0;
    shm->can_write = 1;
    srand(time(NULL));


    shm->var1 = 10000;
    shm->var2 = 500;

    pid = fork();

        if(pid > 0)
        {
        while(i != 1000000)
        {
            /* busy wait for reader */
            while(shm->can_write == 0);
            shm->var1--;
            shm->var2++;

            /* sync vars*/
            shm->can_write = 0;
            shm->can_read = 1;
            i++;
        }
        
        printf("[Father] = final value var1 = %d, var2 = %d\n Ending...\n",shm->var1,shm->var2);

        printf("Writer: ending!\n");
    }
    else{
        while(i != 1000000)
        {
            /* busy wait for writer */
            while(shm->can_read==0);
            shm->var1++;
            shm->var2--;
            /* sync vars */
            shm->can_read = 0;
            shm->can_write = 1;
            i++;
        }   
        printf("Reader: ending!\n");
        exit(0);
    }

    wait(NULL);
    exit(0);

}