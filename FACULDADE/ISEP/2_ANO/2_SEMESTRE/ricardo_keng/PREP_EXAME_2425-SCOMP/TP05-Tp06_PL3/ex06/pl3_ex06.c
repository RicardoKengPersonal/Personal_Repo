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
    pid_t pid;
    int vec1[SIZE], vec2[SIZE];
    int individual_pipes[NCHILD][2];

    int result = 0;


    int end = SIZE / NCHILD;

    srand(time(NULL));

    for(int i = 0; i < SIZE; i++) {
        vec1[i] = rand() % 100; // 0-99
        vec2[i] = rand() % 100; // 0-99
    }

    for(int i = 0; i < NCHILD; i++)
    {
        if (pipe(individual_pipes[i]) == -1)
        {
            perror("Pipe failed");
            exit(EXIT_FAILURE);
        }
    }

    for(int i = 0; i < NCHILD; i++)
    {
        pid = fork();
        if (pid == -1)
        {
            perror("Fork Failed.\n");
            exit(EXIT_FAILURE);
        }

        if (pid == 0)
        {
            // Child
            close(individual_pipes[i][0]); // Close read end of the pipe

            int start = i * end;
            int finish = (i + 1) * end;
            int sum = 0;

            for (int j = start; j < finish; j++)
            {
                sum += vec1[j] + vec2[j];
            }
            write(individual_pipes[i][1], &sum, sizeof(sum));
            close(individual_pipes[i][1]); // Close write end of the pipe
            exit(EXIT_SUCCESS);
        } else {
            // Parent
            close(individual_pipes[i][1]); // Close write end of the pipe
        }
    }

    // Parent reads all results
    for(int i = 0; i < NCHILD; i++) 
    {
        int sum = 0;
        read(individual_pipes[i][0], &sum, sizeof(sum));
        printf("Sum from child %d: %d\n", i, sum);
        result += sum;
        close(individual_pipes[i][0]);
    }

    // Wait for all children
    for(int i = 0; i < NCHILD; i++) 
    {
        wait(NULL);
    }

    printf("Total sum: %d\n", result);
    return 0;
}