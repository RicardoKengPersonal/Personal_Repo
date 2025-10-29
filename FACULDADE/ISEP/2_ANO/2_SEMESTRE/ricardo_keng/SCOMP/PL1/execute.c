// PL1 - ex15

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t child1, child2, child3;
    
    // Fork the first child for "ps"
    child1 = fork();

    if (child1 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child1 == 0)
    {
        printf("Executing ps:\n");
        // First child process: execute "ps"
        execlp("ps", "ps", NULL);
        //In case execlp fails
        perror("execlp failed for ps.\n");
        exit(EXIT_FAILURE);
    }

    // Fork the second child for "ls"
    child2 = fork();

    if (child2 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child2 == 0)
    {
        printf("Executing ls:\n");
        // Second child process: execute "ls"
        execlp("ls", "ls", NULL);
        // In case execlp fails
        perror("execlp failed for ls.\n");
        exit(EXIT_FAILURE);
    }

    // Fork the third child for "pwd"
    child3 = fork();

    if (child3 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child3 == 0)
    {
        // Third child process: execute "pwd"
        printf("Executing pwd:\n");
        execlp("pwd", "pwd", NULL);

        // In case execlp fails
        perror("execlp failed for pwd.\n");
        exit(EXIT_FAILURE);
    }

    // Parent waits for all children to finish
    wait(NULL);
    wait(NULL);
    wait(NULL);

    return 0;
}


/*
Code using execvp:

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t child1, child2, child3;
    
    // Fork the first child for "ps"
    child1 = fork();

    if (child1 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child1 == 0)
    {
        // First child process: execute "ps"
        char *args1[] = {"ps", NULL};  // Arguments for ps command
        execvp("ps", args1);
        perror("execvp failed for ps.\n");
        exit(EXIT_FAILURE);
    }

    // Fork the second child for "ls"
    child2 = fork();

    if (child2 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child2 == 0)
    {
        // Second child process: execute "ls"
        char *args2[] = {"ls", NULL};  // Arguments for ls command
        execvp("ls", args2);
        perror("execvp failed for ls.\n");
        exit(EXIT_FAILURE);
    }

    // Fork the third child for "pwd"
    child3 = fork();

    if (child3 == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (child3 == 0)
    {
        // Third child process: execute "pwd"
        char *args3[] = {"pwd", NULL};  // Arguments for pwd command
        execvp("pwd", args3);
        perror("execvp failed for pwd.\n");
        exit(EXIT_FAILURE);
    }

    // Parent waits for all children to finish
    wait(NULL);
    wait(NULL);
    wait(NULL);

    return 0;
}

*/