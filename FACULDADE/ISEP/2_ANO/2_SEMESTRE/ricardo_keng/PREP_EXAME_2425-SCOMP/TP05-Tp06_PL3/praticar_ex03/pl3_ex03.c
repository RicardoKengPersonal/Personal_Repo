#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define SIZE 100

//NOT USING A FILE

int main()
{
    pid_t pid;

    int fd[2];

    int num1,num2;

    char msg[SIZE];

    if(pipe(fd) == -1)
    {
        perror("Pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid == -1)
    {
        perror("Fork");
        exit(EXIT_FAILURE);
    }

    if (pid > 0)
    {
        // FATHER

        close(fd[0]); // close read descriptor, as i want the father to write

        printf("Write a number:");
        scanf("%d",&num1);
        printf("Write another number:");
        scanf("%d",&num2);

        getchar();

        printf("Now write a message:");
        fgets(msg,sizeof(msg),stdin);

        write(fd[1],&num1,sizeof(num1));
        write(fd[1],&num2,sizeof(num2));
        write(fd[1],msg,sizeof(msg));

        close(fd[1]);
    } else {
        // CHILD

        close(fd[1]);

        read(fd[0],&num1,sizeof(num1));
        read(fd[0],&num2,sizeof(num2));
        read(fd[0],msg,sizeof(msg));

        close(fd[0]); // DONE READING

        printf("Number 1 (GOT FROM PIPE): %d\n",num1);
        printf("Number 2 (GOT FROM PIPE): %d\n",num2);
        printf("String (GOT FROM PIPE) :%s\n",msg);

        exit(0);
    }

    wait(NULL);

    exit(0);
}