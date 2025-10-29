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
    int fd;
    shared_data_t *shm;

    sem_t *sem_request,*sem_response,*sem_done;

    if((fd = shm_open("/ex08_shm",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1)
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

    if((sem_request = sem_open("/sem_request_ex08",O_CREAT)) == SEM_FAILED) // start with a green light to ask for a ticket
    {
        perror("sem_open: sem_request");
        exit(EXIT_FAILURE);
    }

    if((sem_response = sem_open("/sem_response_ex08",O_CREAT)) == SEM_FAILED) // opens, because it already exists in seller code
    {
        perror("sem_open: sem_response");
        exit(EXIT_FAILURE);
    }

    if((sem_done = sem_open("/sem_done_ex08",O_CREAT)) == SEM_FAILED)
    {
        perror("sem_open: sem_done");
        exit(EXIT_FAILURE);
    }

    srand(time(NULL));

    int waiting_time = rand() % 10 + 1;

    printf("[CLIENT]: Buying a ticket....\n");
    sleep(waiting_time);
    printf("[CLIENT]: ORDER PLACED!\n");
    sem_post(sem_request); // place request

    sem_wait(sem_response); // wait for the response by the seller

    if (shm->ticket_number == -1) 
    {
        printf("[CLIENT]: Sold out! No ticket available.\n");
    } else 
    {
        printf("[CLIENT]: Got ticket %d\n", shm->ticket_number);
    }

    sem_post(sem_done);
    
    exit(0);
}