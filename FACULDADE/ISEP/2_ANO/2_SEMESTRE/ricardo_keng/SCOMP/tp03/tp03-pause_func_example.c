#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>

int main()
{
    pid_t pid;

    pid = fork();

    if (pid > 0)
    {
        printf("Hello child!\n");
        sleep(5);
        printf("Goodbye Child!\n");
        kill(pid,SIGUSR1);
        wait(NULL);
    } else
    {
        printf("Child is running in an infinite loop..\n");
        pause();
    }

    return 0;
}