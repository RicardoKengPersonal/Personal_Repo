#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <sys/types.h>
#include <string.h>

#define SEM_NAME "/file_mutex"

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];
    sem_t *sem = sem_open(SEM_NAME, O_CREAT, 0644, 1);
    if (sem == SEM_FAILED) {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    // Acquire the lock
    sem_wait(sem);

    // Open the file in append mode
    FILE *file = fopen(filename, "a");
    if (!file) {
        perror("fopen");
        sem_post(sem);
        sem_close(sem);
        exit(EXIT_FAILURE);
    }

    // Write to the file
    fprintf(file, "I am the process with PID %d\n", getpid());
    fclose(file);

    // Simulate processing time
    sleep(2);

    // Release the lock
    sem_post(sem);

    // Close semaphore (unlink not done here to allow reuse)
    sem_close(sem);

    return 0;
}
