/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/
#define _GNU_SOURCE
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>

int main()
{
    pid_t pid;
    int a,b,status;
    int fd[2];

    if(pipe(fd) == -1)
    {
        perror("pipe failed:");
        exit(1);
    }

    pid = fork();

    if(pid > 0)
    {
    /*close unused read descriptor*/
    close(fd[0]);

    printf("a = ");
    scanf("%d",&a);
    printf("b = ");
    scanf("%d",&b);

    /* write values to pipe */
    write(fd[1],&a,sizeof(int));
    write(fd[1],&b,sizeof(int));

    /* close write descriptor */
    close(fd[1]);

    wait(&status);
    if(WIFEXITED(status))
    printf("%d + %d = %d\n",a,b,WEXITSTATUS(status));
    }
    else{
    /*close unused write descriptor*/
        close(fd[1]);

        /* read values from pipe */
        read(fd[0],&a,sizeof(int));
        read(fd[0],&b,sizeof(int));

        /* close read deescriptor */
        close(fd[0]);
        printf("Child got from pipe: %d and %d\n",a,b);
        exit(a+b);
    }

}