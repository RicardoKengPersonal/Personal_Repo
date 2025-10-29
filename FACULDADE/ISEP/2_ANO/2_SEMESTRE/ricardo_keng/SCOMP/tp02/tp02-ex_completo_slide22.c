#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t pid; 
    int status;

    pid = fork();

    if (pid > 0)
    {
        printf("Parent process (PID: %d) is waiting for child process to finish...\n",getpid());
    }

    wait(&status);
    if(WIFEXITED(status))
    {
        printf("Child process (exec) ended with exit value: %d\n",WEXITSTATUS(status));
    } else 
    {
        printf("Child process terminated abnormally!\n");
    }
    else 
    {
        printf("Child process (PID: %d) will execute a new program..\n",getpid());
        execlp("prog","prog",(char*)NULL);
        exit(EXIT_FAILURE);
    }
}


// EXEMPLO NAO FUNCIONAL