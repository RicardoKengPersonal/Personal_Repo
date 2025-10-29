#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h> // para usar o wait()

/*Example -- Synchronizing with wait()*/

int main()
{
    pid_t pid;

    pid = fork();

    if (pid > 0)
    {
        printf("I am the father\n");

        /*Parent waits for the child's termination*/

        wait(NULL);

        printf("The child has terminated");
    } else {
        printf("I'm the child\n");
        /*(......)*/

        exit (0);
    }

    return 0;
}