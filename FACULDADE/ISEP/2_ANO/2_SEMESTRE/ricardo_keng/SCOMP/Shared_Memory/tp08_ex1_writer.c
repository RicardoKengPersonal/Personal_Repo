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

typedef struct{
    char location[20];
    int temps[10];
    int can_read;
}shared_data_t;

int main(){
    int fd,i,sum;
    pid_t pid;
    shared_data_t *shm;

    /* creates/opens shared memory area */
    if((fd = shm_open("/shm_ex1",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
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

    /* writes data in shm */
    printf("Location: ");
    scanf("%s",shm->location);
    for(i=0;i<10;i++){
        printf("temp[%d] = ",i);
        scanf("%d",&shm->temps[i]);
    }

    /* sync with reader */
    shm->can_read = 1;

    munmap(shm,sizeof(shared_data_t));
    close(fd);

    printf("Writer: ending!\n");
    exit(0);
}