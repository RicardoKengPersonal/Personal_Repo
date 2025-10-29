#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t pid;
    int fathers_pid,childs_pid;
    int fd[2]; // pipe file descriptors

    if (pipe(fd) == -1) {
        perror("pipe failed:");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("fork failed:");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {

        close(fd[0]); // Close unused read descriptor, because the parent will not read from the pipe, it will only write to it.
        // Parent
        fathers_pid = getpid();
        printf("[Father]: PID = %d\n", fathers_pid);
        write(fd[1], &fathers_pid, sizeof(fathers_pid)); // Write father's PID to the pipe
        close(fd[1]); // Close write descriptor after writing to the pipe
        wait(NULL); // Wait for the child process to finish
    } else 
    {
        close(fd[1]); // Close unused write descriptor, because the child will not write to the pipe, it will only read from it.
        // Child
        childs_pid = getpid();
        printf("[Child]: DEBUGGING --> My father's PID is %d (before reading from the pipe)\n",fathers_pid);

        
        read(fd[0], &fathers_pid, sizeof(fathers_pid)); // Read father's PID from the pipe
        close(fd[0]); // Close read descriptor after reading

        printf("[Child]: My PID is %d, my father's PID is %d (got it from the pipe)\n", childs_pid, fathers_pid);

        exit(EXIT_SUCCESS);
    }

    return 0;
}