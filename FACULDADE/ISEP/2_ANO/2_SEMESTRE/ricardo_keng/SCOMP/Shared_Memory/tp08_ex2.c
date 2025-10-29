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

#define SIZE 1000
#define N 10

typedef struct{
    char v[SIZE];
    int max[N];
}shared_data_t;

int main(){
    int fd,i,j,local_max;
    pid_t pid[N];
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

    /* fills v[] */
    srand(time(NULL));
    for(i=0;i<SIZE;i++){
        shm->v[i] = rand() % 1000 + 1;
    }


    local_max = 0;

    for(i=0;i<N;i++){
        pid[i] = fork();

        /* child */
        if(pid[i] == 0){

            for(j=i*SIZE/N;j<(i+1)*SIZE/N;j++){
                if(shm->v[j] > local_max)
                    local_max = shm->v[j];
            }

            shm->max[i] = local_max;
            printf("Child %d wrote %d in shm\n",i+1,local_max);

            close(fd);
            munmap(shm,sizeof(shared_data_t));
            exit(0);
        }
    }

    for(i=0;i<N;i++){
        wait(NULL);
    }
    printf("Parent: all children ended. Determining global max...\n");

    for(i=0;i<N;i++){
        if(shm->max[i] > local_max)
            local_max = shm->max[i];
    }

    printf("Parent: global max is %d\n",local_max);
    close(fd);
    munmap(shm,sizeof(shared_data_t));

    /* removes shm */
    shm_unlink("/shm_ex2");

    exit(0); 
}