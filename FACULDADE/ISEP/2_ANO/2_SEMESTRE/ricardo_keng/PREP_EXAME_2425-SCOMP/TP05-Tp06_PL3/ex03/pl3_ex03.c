#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>

#define BUFFER_SIZE 1024

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int fd[2];
    if(pipe(fd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }   

    pid_t pid = fork();
    if (pid == -1) {
        perror("Fork failed");
        exit(EXIT_FAILURE); 
    }

    if (pid > 0) {
        // Parent: send file contents to child
        close(fd[0]); // Close unused read end

        int file_fd = open(argv[1], O_RDONLY);
        if (file_fd < 0) {
            perror("open");
            close(fd[1]);
            exit(EXIT_FAILURE);
        }

        char buffer[BUFFER_SIZE];
        ssize_t bytes_read;
        while ((bytes_read = read(file_fd, buffer, BUFFER_SIZE)) > 0) {
            if (write(fd[1], buffer, bytes_read) != bytes_read) {
                perror("write to pipe");
                close(file_fd);
                close(fd[1]);
                exit(EXIT_FAILURE);
            }
        }
        close(file_fd);
        close(fd[1]); // Done writing
        wait(NULL); // Wait for child
    } else {
        // Child: read from pipe and display
        close(fd[1]); // Close unused write end

        char buffer[BUFFER_SIZE];
        ssize_t bytes_read;
        while ((bytes_read = read(fd[0], buffer, BUFFER_SIZE)) > 0) {
            if (write(STDOUT_FILENO, buffer, bytes_read) != bytes_read) {
                perror("write to stdout");
                close(fd[0]);
                exit(EXIT_FAILURE);
            }
        }
        close(fd[0]);
        exit(EXIT_SUCCESS);
    }

    printf("\n");

    return 0;
}