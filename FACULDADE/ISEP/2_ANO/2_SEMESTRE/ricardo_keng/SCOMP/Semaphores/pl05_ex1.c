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

#define NUM_CHILDREN 5
#define NUMBERS_PER_CHILD 200
#define FILENAME "output.txt"

int main() {
    sem_t *sem;
    pid_t pid;
    int i;

    // Initialize named semaphore with initial value 1
    sem = sem_open("/file_sem", O_CREAT | O_EXCL, 0644, 1);
    if (sem == SEM_FAILED) {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    // Clear file before starting
    FILE *file = fopen(FILENAME, "w");
    if (file == NULL) {
        perror("fopen");
        sem_unlink("/file_sem");
        exit(EXIT_FAILURE);
    }
    fclose(file);

    srand(time(NULL));

    // Create child processes
    for (i = 0; i < NUM_CHILDREN; i++) {

        pid = fork();

        if (pid < 0) {

            perror("fork");
            sem_unlink("/file_sem");
            exit(EXIT_FAILURE);

        } else if (pid == 0) {
            // Child process
            sem_wait(sem);  // Lock semaphore

            FILE *f = fopen(FILENAME, "a");
            
            if (f == NULL) {
                perror("fopen in child");
                sem_post(sem);
                exit(EXIT_FAILURE);
            }

            fprintf(f, "Child PID %d writing:\n", getpid());
            for (int j = 0; j < NUMBERS_PER_CHILD; j++) {
                fprintf(f, "%d ", rand() % 1000);
            }
            fprintf(f, "\n\n");
            fclose(f);

            sem_post(sem);  // Unlock semaphore
            exit(0);  // Child exits
        }
        // Parent continues to next child
    }

    // Parent waits for all children to finish
    for (i = 0; i < NUM_CHILDREN; i++) {
        wait(NULL);
    }

    // Display contents of the file
    printf("Contents of %s:\n", FILENAME);
    file = fopen(FILENAME, "r");
    if (file == NULL) {
        perror("fopen read");
        sem_unlink("/file_sem");
        exit(EXIT_FAILURE);
    }

    char ch;
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);
    }
    fclose(file);

    // Clean up
    sem_close(sem);
    sem_unlink("/file_sem");

    return 0;
}