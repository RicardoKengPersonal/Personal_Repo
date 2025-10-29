#define _GNU_SOURCE
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <ctype.h>

int main()
{
    pid_t pid; //create parent and child processes
    int up_fd[2],down_fd[2]; //UP pipe and Down pipe

    char msg[21]; //message to send to client


    if(pipe(up_fd) == -1 || pipe(down_fd) == -1) //Check pipe creation
    {
        perror("Pipe failed.\n");
        exit(EXIT_FAILURE);
    }

    pid = fork(); // Parent and child

    if(pid == -1)
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if(pid > 0) // Parent 
    {
        close(up_fd[1]);
        close(down_fd[0]);

        read(up_fd[0],msg,sizeof(msg));

        printf("(Father): I received this message: %s\n",msg);

        for (int i = 0; msg[i] != '\0'; ++i) {
            if (islower(msg[i])) {
                msg[i] = toupper(msg[i]);
            } else if (isupper(msg[i])) {
                msg[i] = tolower(msg[i]);
            }
        }

        printf("(Father) I converted this message to: %s\n",msg);

        write(down_fd[1], msg, strlen(msg) + 1);

        close(up_fd[0]);
        close(down_fd[1]);

    } else // Child
    {
        close(up_fd[0]); //close the read descriptor of up pipe because it will never read from this pipe
        close(down_fd[1]); // close the write descriptor of down pipe because it will never write on this pipe

        printf("(Child) Enter a message to send:");
        fgets(msg, sizeof(msg), stdin);
        msg[strcspn(msg, "\n")] = '\0'; // Remove newline character if present
        printf("(Child) Message entered: %s\n",msg);

        write(up_fd[1],msg,strlen(msg)+1);

        read(down_fd[0],msg,sizeof(msg));

        printf("(Child) The message i received: %s\n",msg);

        close(up_fd[1]);
        close(down_fd[0]);

    }

    return 0;
}