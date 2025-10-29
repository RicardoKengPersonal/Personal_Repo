#define _GNU_SOURCE
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>     // for open()
#include <errno.h>

#define BUFFER_SIZE 1024

int main() 
{
    int fd[2]; //pipe 
    pid_t pid; //child

    if (pipe(fd) == -1) 
    {
        perror("Pipe failed");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1) 
    {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid > 0) 
    {
        // Parent process
        close(fd[0]); // Close unused read end

        int file_fd = open("input.txt", O_RDONLY);
        
        if (file_fd == -1) 
        {
            perror("Failed to open input.txt");
            close(fd[1]);
            exit(EXIT_FAILURE);
        }

        char buffer[BUFFER_SIZE];
        ssize_t bytes_read;

        while ((bytes_read = read(file_fd, buffer, sizeof(buffer))) > 0) {
            write(fd[1], buffer, bytes_read);
        }

        close(file_fd);
        close(fd[1]); // Finished writing
        wait(NULL);   // Wait for child to finish

    } else {
        // Child process
        close(fd[1]); // Close unused write end

        char buffer[BUFFER_SIZE];
        ssize_t bytes_read;

        while ((bytes_read = read(fd[0], buffer, sizeof(buffer))) > 0) {
            write(STDOUT_FILENO, buffer, bytes_read);
        }

        close(fd[0]);
        exit(EXIT_SUCCESS);
    }

    return 0;
}
