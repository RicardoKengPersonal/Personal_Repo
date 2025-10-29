#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

// Function to create two child processes
char create_twins(pid_t list[2]) 
{
    pid_t pid1, pid2;

    // Create first child
    pid1 = fork();

    if (pid1 == -1) 
    {
        perror("Fork failure");
        exit(EXIT_FAILURE);
    }

    if (pid1 == 0) // This accesses the child , whereas if (pid1 > 0) goes to the parent. This doesnt mean the process doesnt have a parent, as processes ALWAYS have parents
    { // First child
        printf("First child: PID = %d, Parent PID = %d\n", getpid(), getppid());
        exit('a');
    }

    // Create second child
    pid2 = fork();

    if (pid2 == -1) 
    {
        perror("Fork failure");
        exit(EXIT_FAILURE);
    }

    if (pid2 == 0) 
    { // Second child
        printf("Second child: PID = %d, Parent PID = %d\n", getpid(), getppid());
        exit('b');
    }

    // Store child PIDs in the list
    list[0] = pid1;
    list[1] = pid2;

    return 'p'; // Return 'p' for parent
}

int main() 
{
    pid_t twins[2];

    // Create 6 child processes (3 pairs of twins)
    for (int i = 0; i < 3; i++) 
    {
        char result = create_twins(twins);

        if (result == 'p') 
        { 
            // Parent process
            int status;

            // Wait for first child
            waitpid(twins[0], &status, 0);
            printf("Child PID = %d, Exit value = %c\n", twins[0], WEXITSTATUS(status));

            // Wait for second child
            waitpid(twins[1], &status, 0);
            printf("Child PID = %d, Exit value = %c\n", twins[1], WEXITSTATUS(status));
        }
    }

    return 0;
}