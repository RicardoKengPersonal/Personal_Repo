#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 10
#define TOTAL_VALUES 30

typedef struct {
    int buffer[BUFFER_SIZE];
    int in; // where the next number comes in
    int out;    // where the customer will read the element
    int count; // number of elements currently in the buffer
} shared_data_t;

int main() {
    int fd;
    pid_t pid;
    shared_data_t *shm;

    // Create shared memory
    if ((fd = shm_open("/pl4_ex10", O_CREAT | O_EXCL | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shm_open");
        exit(1);
    }

    if (ftruncate(fd, sizeof(shared_data_t)) == -1) {
        perror("ftruncate");
        exit(2);
    }

    shm = mmap(NULL, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (shm == MAP_FAILED) {
        perror("mmap");
        exit(3);
    }

    // Initialize circular buffer indices and count
    shm->in = 0;
    shm->out = 0;
    shm->count = 0;

    pid = fork();

    if (pid == -1) 
    {
        perror("fork");
        exit(4);
    }

    if (pid == 0) 
    {
        // Child = Consumer
        for (int i = 0; i < TOTAL_VALUES; i++) 
        {
            // Busy wait if buffer is empty
            while (shm->count == 0);

            int val = shm->buffer[shm->out];

            printf("Consumer: read %d from buffer[%d]\n", val, shm->out);
            shm->out = (shm->out + 1) % BUFFER_SIZE;

            // Decrement count
            shm->count--;
        }
        exit(0);
    } else {
        // Parent = Producer
        for (int i = 1; i <= TOTAL_VALUES; i++) {
            // Busy wait if buffer is full
            while (shm->count == BUFFER_SIZE);

            shm->buffer[shm->in] = i;
            printf("Producer: wrote %d to buffer[%d]\n", i, shm->in);
            shm->in = (shm->in + 1) % BUFFER_SIZE;

            // Increment count
            shm->count++;
        }

        wait(NULL); // Wait for child to finish
        
        shm_unlink("/pl4_ex10"); // Cleanup
        printf("Done.\n");
    }

    return 0;
}
