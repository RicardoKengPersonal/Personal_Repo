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
    int ticket_number;
}shared_data_t;

int main()
{
    sem_unlink("/sem_response_ex08");
    sem_unlink("/sem_request_ex08");
    sem_unlink("/sem_done_ex08");

    int tickets = 3;
    int ticket_order = 0;
    int fd;
    shared_data_t *shm;
    sem_t *sem_request,*sem_response,*sem_done;

    if((fd = shm_open("/ex08_shm",O_CREAT | O_RDWR,S_IRUSR|S_IWUSR))==-1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if(ftruncate(fd,sizeof(shared_data_t)) == -1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(NULL,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    if((sem_response = sem_open("/sem_response_ex08",O_CREAT | O_EXCL,0644,0)) == SEM_FAILED) // green to sell
    {
        perror("sem_open: sem_response");
        exit(EXIT_FAILURE);
    }

    if((sem_request = sem_open("/sem_request_ex08",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open: sem_request");
        exit(EXIT_FAILURE);
    }

    if((sem_done = sem_open("/sem_done_ex08",O_CREAT,0644,0)) == SEM_FAILED)
    {
        perror("sem_open: sem_done");
        exit(EXIT_FAILURE);
    }
    printf("[SELLER]: NOW SELLING!\n");

    for(int i = 0; i < tickets; i++) 
    {
        sem_wait(sem_request); // Wait for client
        ticket_order++;
        shm->ticket_number = ticket_order;
        printf("[SELLER] Ticket Sold! Number: %d\n", ticket_order);
        sem_post(sem_response); // Notify client
        sem_wait(sem_done);
    }

    printf("[SELLER]: Sold out!\n");

    // Notify extra clients (e.g., 5 more times)
    for (int i = 0; i < 5; i++) 
    {
        sem_wait(sem_request);
        shm->ticket_number = -1; // Special value for sold out
        sem_post(sem_response);
    }

    shm_unlink("/ex08_shm");
    close(fd);
    munmap(shm,sizeof(shared_data_t));

    exit(0);
}