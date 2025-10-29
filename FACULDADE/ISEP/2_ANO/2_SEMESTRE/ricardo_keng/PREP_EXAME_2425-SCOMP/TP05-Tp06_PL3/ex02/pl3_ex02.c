#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t pid;
    int fd[2]; // pipe file descriptors

    int num; 
    char str[100];

    if (pipe(fd) == -1) {
        perror("pipe failed:");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1) {
        perror("fork failed:");
        exit(EXIT_FAILURE);
    }

    if (pid > 0) 
    {
        printf("[Father]: Asking for a number:");
        scanf("%d", &num);

        getchar(); // Consume leftover newline


        printf("[Father]: Asking for a string:");
        fgets(str, sizeof(str), stdin);

        
        close(fd[0]); // Close read end of the pipe
        write(fd[1], &num, sizeof(num)); // Write number to pipe
        write(fd[1], str, sizeof(str)); // Write string to pipe
        close(fd[1]); // Close write end of the pipe after writing


        printf("[Father]: Sending number %d and string '%s' to child.\n", num, str);

        wait(NULL); // Wait for the child process to finish

    } else {
        close(fd[1]); // Close the unused write end of the pipe

        read(fd[0], &num, sizeof(num)); // Read number from pipe
        read(fd[0], str, sizeof(str)); // Read string from pipe
        close(fd[0]); // Close read end of the pipe after reading

        printf("[Child]: Received number %d and string '%s' from father.\n", num, str);
        exit(EXIT_SUCCESS); // Exit child process
    }

    return 0;
}