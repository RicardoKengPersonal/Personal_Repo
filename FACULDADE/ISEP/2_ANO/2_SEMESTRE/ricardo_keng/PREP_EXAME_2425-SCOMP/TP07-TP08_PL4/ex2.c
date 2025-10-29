/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

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
    int number;
    int can_read;
    int can_write;
}shared_data_t;

int main(){
    int fd,i,n;
    pid_t pid;
    shared_data_t *shm;

    /* creates/opens shared memory area */
    if((fd = shm_open("/shm_ex2",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
        perror("shmopen");
        exit(1);
    }

    /* defines size of shm */
    if(ftruncate(fd,sizeof(shared_data_t)) == -1){
        perror("ftruncate");
        exit(2);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    shm->number = -1;

    /* synchronization variables */
    shm->can_read = 0;
    shm->can_write = 1;
    srand(time(NULL));

    pid = fork();

    if(pid > 0){
        while(shm->number != 5){
            /* busy wait for reader */
            while(shm->can_write == 0);
            shm->number = rand() % 5 + 1;
            printf("Writer: wrote %d in shm...\n",shm->number);
            /* sync vars*/
            shm->can_write = 0;
            shm->can_read = 1;
        }
        printf("Writer: ending!\n");
    }
    else{
        while(shm->number != 5){
            /* busy wait for writer */
            while(shm->can_read==0);
            n = shm->number;
            printf("Reader: got %d from shm...\n",n);
            /* sync vars */
            shm->can_read = 0;
            shm->can_write = 1;
        }   
        printf("Reader: ending!\n");
        exit(0);
    }

    wait(NULL);
    exit(0);
}