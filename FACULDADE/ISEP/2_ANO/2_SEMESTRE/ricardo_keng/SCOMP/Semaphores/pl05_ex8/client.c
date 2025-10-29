#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/stat.h>
#include <time.h>

#define SHM_NAME "/shm_ticket"
#define SEM_CLIENT "/sem_client"
#define SEM_SELLER "/sem_seller"

typedef struct {
    int next_ticket;
    int current_serving;
} shared_data_t;

int main() {
    srand(getpid() + time(NULL));

    int fd = shm_open(SHM_NAME, O_RDWR, 0666);
    shared_data_t *shm = mmap(NULL, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    sem_t *sem_client = sem_open(SEM_CLIENT, 0);
    sem_t *sem_seller = sem_open(SEM_SELLER, 0);

    sem_post(sem_client);        // Notify seller
    sem_wait(sem_seller);        // Wait for ticket

    printf("Client [%d] received ticket #%d\n", getpid(), shm->current_serving);

    int service_time = (rand() % 10) + 1;
    sleep(service_time);         // Simulate being served
    printf("Client [%d] done (service time: %d sec)\n", getpid(), service_time);

    munmap(shm, sizeof(shared_data_t));
    close(fd);

    return 0;
}
