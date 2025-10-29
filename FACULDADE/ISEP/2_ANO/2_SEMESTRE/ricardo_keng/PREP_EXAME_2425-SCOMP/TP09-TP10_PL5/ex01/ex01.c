#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <semaphore.h>
#include <sys/mman.h>

#define NUM_PROCESSES 5
#define NUMBERS_PER_PROCESS 200
#define OUTPUT_FILE "output.txt"

int main() 
{
    sem_t *sem = mmap(NULL, sizeof(sem_t), PROT_READ | PROT_WRITE,MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    
    if (sem == MAP_FAILED) 
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    // Initialize semaphore to 1 (binary semaphore)
    sem_init(sem, 1, 1); // 1 = shared between processes

    // Clear file before writing
    FILE *f = fopen(OUTPUT_FILE, "w");
    if (f) fclose(f);

    for (int i = 0; i < NUM_PROCESSES; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // Child process
            sem_wait(sem); // Wait (lock)

            FILE *file = fopen(OUTPUT_FILE, "a");
            if (!file) {
                perror("fopen");
                sem_post(sem);
                exit(EXIT_FAILURE);
            }

            for (int j = 0; j < NUMBERS_PER_PROCESS; j++) {
                fprintf(file, "Process %d: %d\n", getpid(), j + 1);
            }

            fclose(file);
            sem_post(sem); // Signal (unlock)
            exit(0);
        }
    }

    // Parent process waits for all children
    for (int i = 0; i < NUM_PROCESSES; i++) {
        wait(NULL);
    }

    printf("All child processes completed.\n\nContents of the file:\n\n");

    // Display file contents
    FILE *file = fopen(OUTPUT_FILE, "r");
    if (!file) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }

    char buffer[256];
    while (fgets(buffer, sizeof(buffer), file)) {
        fputs(buffer, stdout);
    }

    fclose(file);
    sem_destroy(sem);
    munmap(sem, sizeof(sem_t));

    return 0;
}
