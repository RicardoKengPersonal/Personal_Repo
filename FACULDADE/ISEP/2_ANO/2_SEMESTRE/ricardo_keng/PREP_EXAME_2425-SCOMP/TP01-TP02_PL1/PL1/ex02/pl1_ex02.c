#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() 
{

    pid_t pid1, pid2;
    int status;

    pid1 = fork();

    if (pid1 < 0) 
    {
        perror("Error creating first child");
        exit(1);
    }

    if (pid1 == 0) 
    {
        // First child
        printf("I am the first child, PID = %d\n", getpid());
        sleep(5);
        exit(1);
    }

    // Only the parent creates the second child
    pid2 = fork();

    if (pid2 < 0) 
    {
        perror("Error creating second child");
        exit(1);
    }

    if (pid2 == 0) 
    {
        // Second child
        printf("I am the second child, PID = %d\n", getpid());
        exit(2);
    }

    // Parent process
    printf("I am the father, PID = %d\n", getpid());

    // Wait for first child
    waitpid(pid1, &status, 0);

    if (WIFEXITED(status)) 
    {
        printf("First child terminated with exit code %d\n", WEXITSTATUS(status));
    }

    // Wait for second child
    waitpid(pid2, &status, 0);

    if (WIFEXITED(status)) 
    {
        printf("Second child terminated with exit code %d\n", WEXITSTATUS(status));
    }

    return 0;
}
