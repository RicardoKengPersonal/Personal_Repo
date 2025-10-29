#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

void handle_signal(int sig) 
{
    execlp("prog","prog", NULL);
}

int main()
{
    pid_t pid;
    int i;

    struct sigaction act;

    memset(&act,0,sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_RESTART;
    sigaction(SIGUSR1, &act, NULL);

    pid = fork();
    if (pid == 0)
    {
        for(i = 0; i < 5; i++)
        {
            kill(getppid(), SIGUSR1);
            sleep(2);
        }
    } else {
        for(; ;)
        {
            pause(); // Wait for signal
        }
    }
}

/* 
Justification:

The child process (pid == 0) runs a loop 5 times, and in each iteration, it sends the signal SIGUSR1 to its parent (kill(getppid(), SIGUSR1);), then sleeps for 2 seconds.
The parent process installs a signal handler for SIGUSR1 (handle_signal). This handler calls execlp("prog", "prog", NULL);, which replaces the parent process image with the "prog" program.
After the first signal is received, the parent process is replaced by "prog", so it cannot handle any further signals.
Therefore, "prog" is executed only once, not five times.
Final answer:

The program "prog" is executed once.
After the parent receives the first SIGUSR1, it is replaced by "prog", so it cannot handle further signals. 
*/