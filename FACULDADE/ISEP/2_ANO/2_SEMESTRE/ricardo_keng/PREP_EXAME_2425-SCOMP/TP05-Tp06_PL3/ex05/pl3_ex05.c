#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define SIZE 1000
#define NCHILD 5

int main()
{
    int shared_pipe[2];
    int vec1[SIZE], vec2[SIZE];
    int end = SIZE / NCHILD;
    int result = 0;

    srand(time(NULL));

    for (int i = 0; i < SIZE; i++) {
        vec1[i] = rand() % 100; // 0-99
        vec2[i] = rand() % 100; // 0-99
    }

    if (pipe(shared_pipe) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < NCHILD; i++) {
        pid_t pid = fork();
        if (pid == -1) {
            perror("Fork Failure");
            exit(EXIT_FAILURE);
        }
        if (pid == 0) {
            // Child process
            close(shared_pipe[0]); // Close read end

            int start = i * end;
            int finish = (i + 1) * end;
            int sum = 0;
            for (int j = start; j < finish; j++) {
                sum += vec1[j] + vec2[j];
            }
            write(shared_pipe[1], &sum, sizeof(sum));
            close(shared_pipe[1]);
            exit(EXIT_SUCCESS);
        }
        // Parent continues to fork next child
    }

    // Parent process
    close(shared_pipe[1]); // Close write end

    for (int i = 0; i < NCHILD; i++) {
        int sum = 0;
        read(shared_pipe[0], &sum, sizeof(sum));
        printf("Sum from child %d: %d\n", i, sum);
        result += sum;
    }
    close(shared_pipe[0]);

    // Wait for all children
    for (int i = 0; i < NCHILD; i++) {
        wait(NULL);
    }

    printf("Total sum: %d\n", result);
    return 0;
}