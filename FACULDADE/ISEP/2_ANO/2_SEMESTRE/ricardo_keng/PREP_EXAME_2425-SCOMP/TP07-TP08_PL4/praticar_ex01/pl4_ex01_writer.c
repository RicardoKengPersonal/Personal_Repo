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

    if((fd = shm_open("/shm_praticar_ex01",O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) == -1)
    {
        perror("SHM_OPEN");
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

    shm->waiting_flag = 0;

    printf("Student number:");
    scanf("%d",&shm->number);

    getchar();

    printf("\nStudent name:");
    fgets(shm->name,sizeof(shm->name),stdin);

    printf("\nStudent address:");
    fgets(shm->address,sizeof(shm->address),stdin);

    printf("[Writer]: DATA AVAILABLE.\n");

    shm->waiting_flag = 1;

    exit(0);
}