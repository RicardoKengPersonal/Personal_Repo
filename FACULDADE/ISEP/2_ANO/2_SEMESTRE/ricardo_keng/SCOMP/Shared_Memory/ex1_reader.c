/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct{
    char str[80];
}shared_data_t;

int main(){
    int fd;
    shared_data_t *shm;
    char local_str[80];

    /* opens shared memory area */
    if((fd = shm_open("/shm_ex1",O_RDWR,0)) == -1){
        perror("shmopen");
        exit(1);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    /* reads string from shm */
    strcpy(local_str,shm->str);
    printf("Reader: read string from shm: %s\n",local_str);
    exit(0);
}