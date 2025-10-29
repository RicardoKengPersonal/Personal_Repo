#include <stdio.h>      // Standard I/O library for printf function
#include <sys/types.h>  // Defines data types used in system calls
#include <unistd.h>     // Provides access to the POSIX API, including fork()
#include <stdlib.h>

int main(void) {
    pid_t p, a;  // Declare two process ID variables

    p = fork();  // First fork creates a new child process

    if (p == -1)
    {
        perror("Fork Failed\n");
        exit(EXIT_FAILURE);
    }

    a = fork();  // Second fork creates another new child process for both parent and child

    if (a == -1)
    {
        perror("Fork Failed\n");
        exit(EXIT_FAILURE);
    }

    // At this point, multiple processes exist due to the two fork() calls.
    // Each fork() creates a new process, potentially doubling the number of processes.

    printf("Concurrent Programming\n"); // This message is printed by each process

    return 0;  // Program exits successfully
}
