#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <fcntl.h>
#include <sys/wait.h>

int main() {
    // Unlink any old semaphores
    for (int i = 0; i < 6; i++) {
        char name[16];
        sprintf(name, "/sem_step%d", i);
        sem_unlink(name);
    }

    // Create and initialize 6 semaphores for 6 print steps
    sem_t *sems[6];
    for (int i = 0; i < 6; i++) {
        char name[16];
        sprintf(name, "/sem_step%d", i);
        sems[i] = sem_open(name, O_CREAT | O_EXCL, 0644, i == 0 ? 1 : 0);
        if (sems[i] == SEM_FAILED) {
            perror("sem_open");
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < 3; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // === CHILD PROCESS ===

            if (i == 0) {  // Child 1
                sem_wait(sems[0]);
                printf("Sistemas ");
                fflush(stdout);
                sem_post(sems[1]);

                sem_wait(sems[3]);
                printf("is ");
                fflush(stdout);
                sem_post(sems[4]);

            } else if (i == 1) {  // Child 2
                sem_wait(sems[2]);
                printf("de ");
                fflush(stdout);
                sem_post(sems[3]);

                sem_wait(sems[4]);
                printf("the ");
                fflush(stdout);
                sem_post(sems[5]);

            } else if (i == 2) {  // Child 3
                sem_wait(sems[1]);
                printf("Computadores ");
                fflush(stdout);
                sem_post(sems[2]);

                sem_wait(sems[5]);
                printf("best!\n");
                fflush(stdout);
            }

            // Close semaphores before exiting
            for (int j = 0; j < 6; j++) {
                sem_close(sems[j]);
            }
            exit(0);
        }
    }

    // Wait for all children to finish
    for (int i = 0; i < 3; i++) {
        wait(NULL);
    }

    // Cleanup
    for (int i = 0; i < 6; i++) {
        sem_close(sems[i]);
        char name[16];
        sprintf(name, "/sem_step%d", i);
        sem_unlink(name);
    }

    return 0;
}
