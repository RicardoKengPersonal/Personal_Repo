#define _GNU_SOURCE
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MSG_SIZE 100

int main()
{
    pid_t pid; // pid

    int a; // integer to pass through the pipe
    char msg[MSG_SIZE]; // buffer for user input message

    int fd[2]; // pipe

    if(pipe(fd) == -1) // check if the pipe was successfully created
    {
        perror("Pipe failed.\n");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1) // check if fork was successfully created
    {
        perror("Fork Failed.\n");
        exit(EXIT_FAILURE);
    }

    if(pid > 0) // Parent
    {
        close(fd[0]); // close the unused descriptor

        printf("a = ");
        scanf("%d", &a); // read the integer

        getchar(); // consume leftover newline

        printf("Enter a message to send: ");
        fgets(msg, MSG_SIZE, stdin); // read a line including spaces
        msg[strcspn(msg, "\n")] = 0; // remove trailing newline

        write(fd[1], &a, sizeof(int));
        write(fd[1], msg, strlen(msg)+1); // +1 to include null terminator

        close(fd[1]); // close write end

        wait(NULL);
    }
    else // Child
    {
        close(fd[1]); // close the unused descriptor

        read(fd[0], &a, sizeof(int));

        char received_msg[MSG_SIZE];
        read(fd[0], received_msg, MSG_SIZE);

        close(fd[0]); // close the read descriptor

        printf("I'm the child and got %d from my father.\n", a);
        printf("This is the message I got from the father: %s\n", received_msg);
    }

    return 0;
}
