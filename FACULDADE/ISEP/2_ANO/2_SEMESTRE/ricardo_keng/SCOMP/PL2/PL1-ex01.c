#define _XOPEN_SOURCE 700
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <string.h>


void handle_signal(int sig)
{
    execlp("prog","prog",NULL);
}

int main()
{
    pid_t pid;
    int i;

    struct sigaction act;

    memset(&act, 0, sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_RESTART;
    sigaction(SIGUSR1, &act, NULL);

    pid = fork();

    if(pid == 0)
    {
        for(i = 0; i < 5; i++)
        {
            kill(getppid(), SIGUSR1);
            sleep(2);
        }
    } else
    {
        for(;  ;)
            pause();
    }
}