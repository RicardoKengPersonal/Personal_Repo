/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>

void child1(int *fd){
    /* close unused read descriptor */
    close(fd[0]);

    dup2(fd[1],1);
    close(fd[1]);
    execlp("cat","cat","ex2.c",NULL);
}

void child2(int *fd){
    /* close unused read descriptor */
    close(fd[1]);
    
    dup2(fd[0],0);
    close(fd[0]);
    execlp("sort","sort",NULL);
}

int main(){
    pid_t pid;
    int i,fd[2];

    if(pipe(fd) == -1){
        perror("pipe:");
        exit(1);
    }

    for(i=0;i<2;i++){
        pid = fork();

        if(pid == 0){
            if(i == 0)
                child1(fd);
            else
                child2(fd);

            perror("exec");
            exit(1);
        }
    }

    /* close unused file descriptors */
    /* try comment these lines, analyse the behaviour and explain it */
    close(fd[0]);
    close(fd[1]);

    for(i=0;i<2;i++)
        wait(NULL);
    
    printf("Parent: all children ended...\n");

    exit(0);
}