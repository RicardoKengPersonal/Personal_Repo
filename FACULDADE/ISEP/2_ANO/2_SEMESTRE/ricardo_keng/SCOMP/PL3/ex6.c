#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 5
#define SIZE 1000
#define CHUNK (SIZE / N)

int main() {
    pid_t pids[N];
    int vec1[SIZE], vec2[SIZE], result[SIZE];
    int pipes[N][2]; // One pipe per child
    int i, j;

    srand(time(NULL));

    // Fill input arrays with random values
    for (i = 0; i < SIZE; i++) 
    {
        vec1[i] = rand() % 50;
        vec2[i] = rand() % 100;
    }

    // Create pipes
    for (i = 0; i < N; i++) 
    {
        if (pipe(pipes[i]) == -1) 
        {
            perror("pipe failed");
            exit(1);
        }
    }

    // Fork child processes
    for (i = 0; i < N; i++) 
    {
        pids[i] = fork();

        if (pids[i] == -1) 
        {
            perror("fork failed");
            exit(1);
        }

        if (pids[i] == 0) 
        {
            // Child process
            close(pipes[i][0]); // Close read end

            int start = i * CHUNK;
            int end = start + CHUNK;

            for (j = start; j < end; j++) 
            {
                int sum = vec1[j] + vec2[j];
                write(pipes[i][1], &sum, sizeof(int));
            }

            close(pipes[i][1]);
            exit(0);
        }
    }

    // Parent process
    for (i = 0; i < N; i++) 
    {
        close(pipes[i][1]); // Close write end
    }

    // Read results from each child pipe and store in the correct position
    for (i = 0; i < N; i++) 
    {
        int start = i * CHUNK;
        for (j = start; j < start + CHUNK; j++) 
        {
            int value;
            read(pipes[i][0], &value, sizeof(int));
            result[j] = value;
        }
        close(pipes[i][0]);
    }

    // Wait for all children to finish
    for (i = 0; i < N; i++) 
    {
        wait(NULL);
    }

    // Optional: print the result array
    printf("Result array (first 20 values):\n");
    for (i = 0; i < 20; i++) {
        printf("%d ", result[i]);
    }
    printf("\n");

    return 0;
}
