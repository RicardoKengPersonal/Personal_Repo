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


#define N 10
#define SIZE 1000

typedef struct{
    int gmax;
    int max_count;
    int nproc_at_barrier;
}shared_data_t;

int main(){
    int fd,i,j,v[SIZE];
    pid_t pids[N];
    shared_data_t *shm;
    sem_t *sem_excl, *sem_barrier;
    int local_max = 0, local_count = 0;

    /* creates/opens shared memory area */
    if((fd = shm_open("/shm_ex3",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
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
    if((sem_excl = sem_open("/sem_ex3_excl",O_CREAT,0644,1))==SEM_FAILED){
        perror("sem_open sem_excl");
        exit(4);
    }

    /* creates sem for barrier */
    if((sem_barrier = sem_open("/sem_ex3_barrier",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open sem_barrier");
        exit(4);
    }

    srand(time(NULL));
    for(i=0;i<SIZE;i++){
        v[i] = rand() % 50;
    }

    for(i=0;i<N;i++){
        pids[i] = fork();

        if(pids[i] == 0){

            /* each child finds local max */
            for(j=i*SIZE/N;j<(i+1)*SIZE/N;j++){
                if(v[j]> local_max)
                    local_max = v[j];
            }   

            /* update global max */
            sem_wait(sem_excl);

            if(shm->gmax < local_max)
                shm->gmax = local_max;

            shm->nproc_at_barrier++;

            if(shm->nproc_at_barrier == N){
                printf("All children determined gmax = %d. Proceeding...\n",shm->gmax);
                sem_post(sem_barrier);
            }

            sem_post(sem_excl);

            /* wait in barrier */
            sem_wait(sem_barrier);
            sem_post(sem_barrier);

            /* all children have reached the barrier */

            /* find occurrences of global max */
            for(j=i*SIZE/N+1;j<(i+1)*SIZE/N;j++){
                if(v[j] == shm->gmax)
                    local_count++;
            }

            /* update global counter */
            sem_wait(sem_excl);
            shm->max_count += local_count;
            sem_post(sem_excl);

            exit(0);
        }

    }
    
    /* parent */
    for(i = 0; i<N; i++)
        wait(NULL);
  
    printf("Parent: gmax %d found %d times in v[]\n",shm->gmax,shm->max_count);

    close(fd);
    munmap(shm,sizeof(shared_data_t));
    /* removes sems and shm */
    shm_unlink("/shm_ex3");
    sem_unlink("/sem_ex3_excl");
    sem_unlink("/sem_ex3_barrier");
    exit(0); 
}