#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <fcntl.h>
#include <time.h>
#include <string.h>

#define NUM_CHILDREN 5
#define NUMBERS_PER_CHILD 200
#define FILENAME "output_sequential.txt"

char *sem_names[NUM_CHILDREN] = {
    "/sem_child1",
    "/sem_child2",
    "/sem_child3", // one per child
    "/sem_child4",
    "/sem_child5"
};

int main() {
    sem_t *sems[NUM_CHILDREN]; //declaration
    pid_t pid; // for the fork
    int i;

    // Initialize semaphores
    for (i = 0; i < NUM_CHILDREN; i++) 
    {
        int initial_value = (i == 0) ? 1 : 0; // Only child 1 starts unlocked 

        /*
        1. int initial_value = (i == 0) ? 1 : 0;
        This means:

        For the first child (i = 0), initial_value = 1

        For all other children, initial_value = 0
        */

        sems[i] = sem_open(sem_names[i], O_CREAT | O_EXCL, 0644, initial_value);

        if (sems[i] == SEM_FAILED) 
        {
            perror("sem_open");

            // Cleanup any already created semaphores

            for (int j = 0; j < i; j++) sem_unlink(sem_names[j]);
            exit(EXIT_FAILURE);
        }
    }

    // Clear the file
    FILE *file = fopen(FILENAME, "w");
    if (file == NULL) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }
    fclose(file);

    srand(time(NULL));

    // Create child processes
    for (i = 0; i < NUM_CHILDREN; i++) 
    {
        pid = fork();

        if (pid < 0) 
        {
            perror("fork");

            exit(EXIT_FAILURE);

        } else if (pid == 0) 
        {
            // Child i (0-based)
            sem_wait(sems[i]);  // Wait for turn

            FILE *f = fopen(FILENAME, "a");
            if (f == NULL) {
                perror("fopen in child");
                exit(EXIT_FAILURE);
            }

            fprintf(f, "Child #%d (PID %d) writing:\n", i + 1, getpid());
            for (int j = 0; j < NUMBERS_PER_CHILD; j++) {
                fprintf(f, "%d ", rand() % 1000);
            }
            fprintf(f, "\n\n");
            fclose(f);

            if (i + 1 < NUM_CHILDREN) {
                sem_post(sems[i + 1]); // Signal the next child
            }

            // Cleanup
            for (int k = 0; k < NUM_CHILDREN; k++) {
                sem_close(sems[k]);
            }
            exit(0);
        }
        // Parent continues loop
    }

    // Parent waits for all children
    for (i = 0; i < NUM_CHILDREN; i++) {
        wait(NULL);
    }

    // Display file content
    printf("Contents of %s:\n", FILENAME);
    file = fopen(FILENAME, "r");
    if (file == NULL) {
        perror("fopen read");
        exit(EXIT_FAILURE);
    }

    char ch;
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);
    }
    fclose(file);

    // Cleanup semaphores
    for (i = 0; i < NUM_CHILDREN; i++) {
        sem_unlink(sem_names[i]);
    }

    return 0;
}
