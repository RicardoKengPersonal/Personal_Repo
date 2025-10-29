#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct {
    int number[10];
    int can_read;
    int can_write;
} shared_data_t;

int main() 
{
    int fd;
    pid_t pid;
    shared_data_t *shm;

    // Create and open shared memory
    if ((fd = shm_open("/shm_ex4", O_CREAT | O_EXCL | O_RDWR, S_IRUSR | S_IWUSR)) == -1) 
    {
        perror("shm_open");
        exit(1);
    }

    // Set size of shared memory
    if (ftruncate(fd, sizeof(shared_data_t)) == -1) 
    {
        perror("ftruncate");
        exit(2);
    }

    // Map shared memory
    if ((shm = (shared_data_t *)mmap(0, sizeof(shared_data_t),PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0)) == MAP_FAILED) 
    {
        perror("mmap");
        exit(3);
    }

    // Initialize synchronization variables
    shm->can_read = 0;
    shm->can_write = 1;

    srand(time(NULL));

    pid = fork();

    if (pid == -1) 
    {
        perror("Fork failed");
        exit(4);
    }

    if (pid > 0) 
    {
        // Parent process - Writer
        while (shm->can_write == 0);  // Busy wait

        printf("Writer: generating numbers...\n");

        for (int i = 0; i < 10; i++) 
        {
            shm->number[i] = (rand() % 20) + 1;
            printf("Writer: number[%d] = %d\n", i, shm->number[i]);
        }

        shm->can_write = 0;
        shm->can_read = 1;

        wait(NULL); // Wait for child
        printf("Writer: done.\n");

        // Clean up
        shm_unlink("/shm_ex4");

    } else {
        
        // Child process - Reader
        while (shm->can_read == 0);  // Busy wait

        float avg = 0;

        for (int i = 0; i < 10; i++) 
        {
            avg += shm->number[i];
        }
        avg /= 10.0;

        printf("Reader: average = %.2f\n", avg);

        exit(0);
    }

    return 0;
}
