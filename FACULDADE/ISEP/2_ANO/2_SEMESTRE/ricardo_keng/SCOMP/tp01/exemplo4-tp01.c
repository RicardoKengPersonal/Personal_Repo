#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/*Example - Process exit information*/

int main()
{
    pid_t pid1,pid2;

    int status;

    pid1 = fork();

    if (pid1 > 0)
    {
        /*Parent waits for the child to terminate*/

        pid2 = wait(&status);

        if(WIFEXITED(status))
        {
            printf("Parent : Child %d returned %d\n",pid2,WIFEXITED(status));
        } else {
            sleep(5);
            exit(10);
        }
    }
}