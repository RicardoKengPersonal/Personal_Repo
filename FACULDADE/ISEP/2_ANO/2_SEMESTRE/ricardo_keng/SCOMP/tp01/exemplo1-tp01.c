#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

/*Example - fork() return values -- tp01*/

int main()
{
    pid_t pid;

    pid = fork();

    if (pid == -1)
    {
        perror("Fork Failed\n");
        exit(EXIT_FAILURE);
    }

    if (pid > 0)
    {
        printf("Luke I am your father\n");
    } else {
        printf("I am the child\n");
    }

    printf("Who executes this line?\n");

    return 0;
}