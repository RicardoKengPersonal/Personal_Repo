#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>

void convert_to_uppercase(char *str)
{
    for (int i = 0; str[i]; i++) {
        str[i] = toupper(str[i]);
    }
}

int main()
{
    pid_t pid;

    char message[256];

    int up_pipe[2];
    int down_pipe[2];

    if (pipe(up_pipe) == -1 || pipe(down_pipe) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1 )
    {
        perror("Fork Failure.\n");
        exit(EXIT_FAILURE); 
    }

    if (pid > 0)
    {
        // Father -> Server
        close(up_pipe[1]); // Close the read end of the up pipe, because the father will write on it
        close(down_pipe[0]); // Close the write end of the down pipe, because the father will read from it

        read(up_pipe[0], message, sizeof(message)); // Read the message from the up pipe
        close(up_pipe[0]); // Close the write end of the up pipe after reading

        convert_to_uppercase(message); // Convert the message to uppercase

        printf("[Server]: Received message: %s\n", message);
        write(down_pipe[1], message, sizeof(message)); // Write the response to the down pipe
        close(down_pipe[1]); // Close the read end of the down pipe after writing

        wait(NULL); // Wait for the child process to finish

    } else {
        // Child -> Client
        close(up_pipe[0]); // Close the read end of the up pipe, because the child will write on it
        close(down_pipe[1]); // Close the write end of the down pipe, because the child will read from it

        printf("[Client]: Reading from stdin..\n");

        printf("[Client]: Reading a message:");
        fgets(message, sizeof(message), stdin);

        write(up_pipe[1], message, sizeof(message)); // Write the message to the up pipe
        close(up_pipe[1]); // Close the read end of the up pipe after writing

        read(down_pipe[0], message, sizeof(message)); // Read the response from the down pipe
        close(down_pipe[0]); // Close the write end of the down pipe after reading

        printf("[Client]: Received response: %s\n", message);

        exit(EXIT_SUCCESS); // Exit the child process

    }

    return 0;
}

/*
How it works:

When the child calls read(down_pipe[1], message, sizeof(message));, it will wait until the parent writes a response and closes the corresponding write end.
When the parent writes to the pipe and closes it, the child’s read() will return with the data.
Summary:
The pipe and the blocking nature of read() handle synchronization for you.
You do not need an extra flag or signal.


## NOTE ##

For a pipe int fd[2];
fd[0] is read end
fd[1] is write end

*/