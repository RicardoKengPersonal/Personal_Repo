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
#include<semaphore.h>


#define N 5

typedef struct{
    int tickets;
    int bought[N];
}shared_data_t;

int main(){
    int fd,i,bought;
    pid_t pids[N];
    shared_data_t *shm;
    sem_t *sem_excl;

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

    /* creates sem for mutual exclusion */
    if((sem_excl = sem_open("/sem_ex2",O_CREAT,0644,1))==SEM_FAILED){
        perror("sem_open");
        exit(4);
    }

    /* init values */
    shm->tickets = 20000;
    bought = 0;

    for(i=0;i<N;i++){
        pids[i] = fork();

        if(pids[i] == 0){

            /* analyse the program's behaviour if 
               you acquire the mutex before testing the condition */

            while(1){
                /* guarantees mutual exclusion */
                sem_wait(sem_excl);
                /* only now can test condition */       
                if(shm->tickets > 0){
                    shm->tickets --;
                    bought++;
                }
                sem_post(sem_excl);

                if(shm->tickets == 0)
                    break;
            }

            /* does not need synchronization */
            /* should be outside of critical zone */
            if(bought)
                shm->bought[i] = bought;

            exit(0);
        }

    }
    
    /* parent */
    for(i = 0; i<N; i++)
        wait(NULL);
  
    if(shm->tickets == 0){
        printf("All tickets have been sold!\n");
    }

    for(i = 0; i<N; i++){
        printf("Child %d bought %d tickets\n",i+1,shm->bought[i]);
    }

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    /* removes sem and shm */
    shm_unlink("/shm_ex2");
    sem_unlink("/sem_ex2");

    exit(0); 
}