#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

// Function for child process to print numbers in a given range
void print_numbers(long start, long end)
{
    for (long i = start; i <= end; i++)
    {
        printf("%ld\n", i);
    }
}

int main()
{
    pid_t child;
    long start = 1;
    long range = 200000;

    // Loop to create 6 child processes
    for (int i = 0; i < 6; i++)
    {
        child = fork(); // Create a new process

        if (child == -1) // Check for fork failure
        {
            perror("Fork Failure.\n");
            exit(EXIT_FAILURE);
        }

        if (child == 0) // Child process
        {
            print_numbers(start, start + range - 1);
            exit(0); // Exit child process
        }
        else // Parent process
        {
            start += range; // Update start for the next child
        }
    }

    // Parent process waits for all children to finish
    for (int i = 0; i < 6; i++)
    {
        wait(NULL);
    }

    return 0;
}

/*
a) Solution Provided:
The current implementation creates six child processes, each printing a unique range of 200,000 numbers, as specified.

b) Is the output always sequential?
No, the output is not guaranteed to be sequential. Since the child processes run concurrently, their outputs may interleave, resulting in a non-deterministic order.

c) Changes for Sequential Output:
To impose sequential output:

Create one child process at a time.

Use wait(NULL) after each child finishes before starting the next one.

This ensures that each child completes its range of numbers before the next starts.

d) Results of Running ps Command:
Running ps during execution may show multiple active child processes simultaneously, confirming parallel execution. Each child process will appear in the process 
list until it terminates. Let me know if you need help modifying the program for sequential output! 😊
*/