#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/stat.h>
#include <string.h>

#define SHM_NAME "/shm_ticket"
#define SEM_CLIENT "/sem_client"
#define SEM_SELLER "/sem_seller"

typedef struct {
    int next_ticket;
    int current_serving;
} shared_data_t;

int main() {
    int fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, 0666);
    ftruncate(fd, sizeof(shared_data_t));
    shared_data_t *shm = mmap(NULL, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    shm->next_ticket = 1;
    shm->current_serving = 0;

    sem_t *sem_client = sem_open(SEM_CLIENT, O_CREAT, 0666, 0);
    sem_t *sem_seller = sem_open(SEM_SELLER, O_CREAT, 0666, 0);

    printf("Seller is ready to sell tickets...\n");

    while (1) {
        sem_wait(sem_client); // Wait for a client to arrive
        shm->current_serving = shm->next_ticket;
        printf("Seller issued ticket #%d\n", shm->next_ticket);
        shm->next_ticket++;
        sem_post(sem_seller); // Notify client that ticket is ready
    }

    // Normally unreachable, but for cleanup:
    munmap(shm, sizeof(shared_data_t));
    close(fd);
    shm_unlink(SHM_NAME);
    sem_unlink(SEM_CLIENT);
    sem_unlink(SEM_SELLER);

    return 0;
}
