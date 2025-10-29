#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define SIZE 120

int main()
{
    pid_t pid;
    int array[SIZE];

    srand(time(NULL));

    for (int i = 0; i < 120; i++)
    {
        array[i] = rand() % 121; // Random numbers between 0 and 120
    }

    for(int i = 0 ; i < 6 ; i++)
    {
        pid = fork();

        if (pid == -1)
        {
            perror("Fork Failure.\n");
            exit(1);
        }

        if (pid == 0)
        {
            // Child
            for (int j = 0; j < SIZE / 6; j++ )
            {
                printf("Child %d [PID = %d]: %d\n", i + 1,getpid(), array[i * (SIZE / 6) + j]);
            }
            exit(0); // Child process exits after printing its part
        } 
    }
    // Parent process waits for all children to finish
    for (int i = 0; i < 6; i++)
    {
        wait(NULL); // Wait for all children to finish
    }
    printf("All children have finished.\n");
    return 0;
}