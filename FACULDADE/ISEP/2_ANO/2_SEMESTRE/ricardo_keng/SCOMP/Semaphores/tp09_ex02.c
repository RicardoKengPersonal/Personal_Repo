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

typedef struct{
    int value1;
    int value2;
}shared_data_t;

#define N_SETS 3

int main(){
    int fd,i;
    pid_t pid;
    shared_data_t *shm;
    sem_t *sem_write, *sem_read;

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

    /* creates write sem */
    if((sem_write = sem_open("/sem_ex2_write",O_CREAT,0644,1))==SEM_FAILED){
        perror("sem_open sem_write");
        exit(4);
    }

    /* creates read sem */
    if((sem_read = sem_open("/sem_ex2_read",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open sem_read");
        exit(4);
    }

    srand(time(NULL));

    pid = fork();

    if(pid > 0){

        for(i = 0; i<N_SETS; i++){
            /* wait for write access */
            sem_wait(sem_write);
            shm->value1 = rand() % 50;
            shm->value2 = rand() % 50;
            printf("Parent wrote values %d and %d in shm...\n",shm->value1, shm->value2);
            /* notifies child of write operation */
            /* sleep(5); */
            sem_post(sem_read);
        }
        
    }else{
        for(i = 0; i<N_SETS; i++){
            /* wait for parent notification */
            sem_wait(sem_read);
            printf("Child: value1 = %d\n",shm->value1);
            printf("Child: value2 = %d\n",shm->value2);
            /* notifies parent of read operation*/
            sem_post(sem_write);
        }

        munmap(shm,sizeof(shared_data_t));
        close(fd);
        exit(0);
    }

    /* parent */
    wait(NULL);
    printf("Parent: child ended. Cleaning...\n");

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    /* removes sems and shm */
    shm_unlink("/shm_ex2");
    sem_unlink("/sem_ex2_write");
    sem_unlink("/sem_ex2_read");

    exit(0); 
}