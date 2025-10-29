#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <fcntl.h>
#include <sys/wait.h>

#define SEM1 "/sem_child1"
#define SEM2 "/sem_child2"
#define SEM3 "/sem_child3"

void error_and_exit(const char *msg) {
    perror(msg);
    sem_unlink(SEM1);
    sem_unlink(SEM2);
    sem_unlink(SEM3);
    exit(EXIT_FAILURE);
}

int main() {
    sem_t *sem1 = sem_open(SEM1, O_CREAT | O_EXCL, 0644, 1);
    sem_t *sem2 = sem_open(SEM2, O_CREAT | O_EXCL, 0644, 0);
    sem_t *sem3 = sem_open(SEM3, O_CREAT | O_EXCL, 0644, 0);

    if (sem1 == SEM_FAILED || sem2 == SEM_FAILED || sem3 == SEM_FAILED) {
        error_and_exit("sem_open");
    }

    for (int i = 1; i <= 3; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // CHILD 1
            if (i == 1) {
                sem_wait(sem1);
                printf("Sistemas ");
                fflush(stdout);
                sem_post(sem2);

                sem_wait(sem1);
                printf("is ");
                fflush(stdout);
                sem_post(sem2);
            }
            // CHILD 2
            else if (i == 2) {
                sem_wait(sem2);
                printf("de ");
                fflush(stdout);
                sem_post(sem3);

                sem_wait(sem2);
                printf("the ");
                fflush(stdout);
                sem_post(sem3);
            }
            // CHILD 3
            else if (i == 3) {
                sem_wait(sem3);
                printf("Computadores ");
                fflush(stdout);
                sem_post(sem1);

                sem_wait(sem3);
                printf("best!\n");
                fflush(stdout);
            }

            // Cleanup per child
            sem_close(sem1);
            sem_close(sem2);
            sem_close(sem3);
            exit(0);
        }
    }

    // Parent waits
    for (int i = 0; i < 3; i++) wait(NULL);

    // Parent cleanup
    sem_close(sem1); sem_unlink(SEM1);
    sem_close(sem2); sem_unlink(SEM2);
    sem_close(sem3); sem_unlink(SEM3);

    return 0;
}
