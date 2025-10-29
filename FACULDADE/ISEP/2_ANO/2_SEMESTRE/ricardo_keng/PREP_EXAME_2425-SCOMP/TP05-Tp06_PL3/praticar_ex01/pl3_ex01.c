#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


int main()
{
    pid_t pid;

    int fd[2];
    int fathers_pid;

    if(pipe(fd) == -1)
    {
        perror("Pipe failure.");
        exit(EXIT_FAILURE);
    }

    pid = fork(); // must be after creating the pipe

    if (pid == -1)
    {
        perror("Fork failure.");
        exit(EXIT_FAILURE);
    }

    if (pid > 0)
    {
        close(fd[0]); // close the read descriptor

        fathers_pid = getpid();

        printf("[Father]: My PID is: %d\n",fathers_pid);

        write(fd[1],&fathers_pid,sizeof(fathers_pid));

        close(fd[1]);

    } else {
        close(fd[1]);

        read(fd[0],&fathers_pid,sizeof(fathers_pid));
        printf("[Child]: I got %d from the pipe\n",fathers_pid);

        close(fd[0]);

        exit(0);
    }

    wait(NULL);

    exit(0);
}