#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

int main()
{
    pid_t pid;

    int fd[2];
    int num;
    char msg[50];

    if(pipe(fd)==-1)
    {
        perror("Pipe failed.");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("Fork");
        exit(EXIT_FAILURE);
    }

    if(pid > 0)
    {
        close(fd[0]);

        printf("[Father]: number:");
        scanf("%d",&num);
        getchar();
        printf("[Father]: Message:");
        fgets(msg,sizeof(msg),stdin);
        
        write(fd[1],&num,sizeof(num));
        write(fd[1],msg,sizeof(msg));

        close(fd[1]);
    } else {
        close(fd[1]);

        read(fd[0],&num,sizeof(num));
        read(fd[0],msg,sizeof(msg));

        printf("[Child]: Number: %d\n",num);
        printf("[Child]: Message: %s\n",msg);

        close(fd[0]);

        exit(0);
    }

    wait(NULL);

    exit(0);

}