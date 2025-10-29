#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <fcntl.h>

#define NUM_MESSAGES 5
#define PARENT_SEM "/sem_parent"
#define CHILD_SEM  "/sem_child"

int main() {
    sem_t *sem_parent, *sem_child;

    // Initialize semaphores
    sem_parent = sem_open(PARENT_SEM, O_CREAT | O_EXCL, 0644, 1); // parent goes first
    sem_child  = sem_open(CHILD_SEM,  O_CREAT | O_EXCL, 0644, 0); // child waits

    if (sem_parent == SEM_FAILED || sem_child == SEM_FAILED) {
        perror("sem_open");
        sem_unlink(PARENT_SEM);
        sem_unlink(CHILD_SEM);
        exit(EXIT_FAILURE);
    }

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        exit(EXIT_FAILURE);
    }
    else if (pid == 0) {
        // Child process
        for (int i = 0; i < NUM_MESSAGES; i++) {
            sem_wait(sem_child);
            printf("Child: Message %d\n", i + 1);
            sem_post(sem_parent);
        }

        sem_close(sem_child);
        sem_close(sem_parent);
        exit(0);
    }
    else {
        // Parent process
        for (int i = 0; i < NUM_MESSAGES; i++) {
            sem_wait(sem_parent);
            printf("Parent: Message %d\n", i + 1);
            sem_post(sem_child);
        }

        wait(NULL); // Wait for child

        sem_close(sem_parent);
        sem_close(sem_child);
        sem_unlink(PARENT_SEM);
        sem_unlink(CHILD_SEM);

        printf("Parent: Done.\n");
    }

    return 0;
}
