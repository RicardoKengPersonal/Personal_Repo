#define _GNU_SOURCE
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MSG_SIZE 100

// Define a struct to hold both the int and the message
typedef struct {
    int a;
    char msg[MSG_SIZE];
} data_t;

int main()
{
    pid_t pid;
    data_t data; // variable to send through the pipe

    int fd[2]; // pipe

    if(pipe(fd) == -1)
    {
        perror("Pipe failed.\n");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1)
    {
        perror("Fork failed.\n");
        exit(EXIT_FAILURE);
    }

    if(pid > 0) // Parent
    {
        close(fd[0]); // close unused read end

        printf("a = ");
        scanf("%d", &data.a);

        getchar(); // consume leftover newline

        printf("Enter a message to send: ");
        fgets(data.msg, MSG_SIZE, stdin);
        data.msg[strcspn(data.msg, "\n")] = 0; // remove newline

        // One write
        write(fd[1], &data, sizeof(data_t));

        close(fd[1]);

        wait(NULL);
    }
    else // Child
    {
        close(fd[1]); // close unused write end

        // One read
        read(fd[0], &data, sizeof(data_t));

        close(fd[0]);

        printf("I'm the child and got %d from my father.\n", data.a);
        printf("This is the message I got from the father: %s\n", data.msg);
    }

    return 0;
}
