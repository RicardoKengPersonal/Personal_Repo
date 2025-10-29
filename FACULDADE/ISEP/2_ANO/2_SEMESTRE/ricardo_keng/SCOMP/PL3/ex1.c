#define _GNU_SOURCE
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>

int main()
{
    pid_t pid;

    int fathers_pid = 0;

    int fd[2];

    if(pipe(fd) == -1)
    {
        perror("Pipe failed.\n");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if (pid > 0) //Father process
    {
        close(fd[0]); // close the unused descriptor, in this case the read descriptor since want the father to write in the pipe going to write

        fathers_pid = getpid(); //place the father's pid in the variable fathers_pid

        printf("I am the father and this is my pid: %d\n",fathers_pid); // Father prints its pid

        write(fd[1],&fathers_pid,sizeof(int)); // Write in the pipe

        close(fd[1]);

        wait(NULL); // wait until the child is finished
    } else
    {
        close(fd[1]); //close the unused descriptor, in this case the write descriptor since we want the child to read from the pipe

        read(fd[0], &fathers_pid, sizeof(int)); // read the fathers pid stored in fatherts_pid variable

        close(fd[1]); //close the read descriptor

        printf("I am the child and this is my fathers pid: %d\n",fathers_pid);
    }

    return 0;
}