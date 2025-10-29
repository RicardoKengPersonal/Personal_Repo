#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define SIZE 100

typedef struct{
    int number;
    char name[SIZE];
    char address[SIZE];
    int waiting_flag;
}shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;

    int local_number;
    char local_name[SIZE];
    char local_address[SIZE];


    if((fd = shm_open("/shm_praticar_ex01",O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) == -1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(NULL,sizeof(shared_data_t),PROT_READ | PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    while(shm->waiting_flag == 0)
    {
        printf("[READER]: Waiting...\n");
        fflush(stdout);
        sleep(1);
    }

    local_number = shm->number;
    strcpy(local_name,shm->name);
    strcpy(local_address,shm->address);

    printf("\n[READER]: Student: %s\n[READER]: Number: %d\n[READER]: Address: %s\n",local_name,local_number,local_address);

    exit(0);

}