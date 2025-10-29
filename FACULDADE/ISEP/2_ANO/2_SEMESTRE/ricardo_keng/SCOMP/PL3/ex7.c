#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>

#define NUM_CHILDREN 10
#define NUM_ROUNDS 10

typedef struct {
    char message[10];
    int round;
} GameMessage;

int main() 
{
    int pipe_fd[2];
    pid_t children[NUM_CHILDREN];

    if (pipe(pipe_fd) == -1) 
    {
        perror("Pipe failed");
        exit(EXIT_FAILURE);
    }

    // Create 10 children
    for (int i = 0; i < NUM_CHILDREN; i++) 
    {
        pid_t pid = fork();

        if (pid == -1) 
        {
            perror("Fork failed");
            exit(EXIT_FAILURE);
        }

        if (pid == 0) 
        {
            // Child process
            close(pipe_fd[1]); // Close write end

            GameMessage msg;
            while (read(pipe_fd[0], &msg, sizeof(GameMessage)) > 0)
            {
                // Successfully read a message
                printf("Child PID %d: received message '%s' in round %d\n", getpid(), msg.message, msg.round);
                close(pipe_fd[0]);
                exit(msg.round); // Exit with round number
            }

            close(pipe_fd[0]);
            exit(0); // If pipe is closed before reading
        } else 
        {
            children[i] = pid;
        }
    }

    // Parent process
    close(pipe_fd[0]); // Close read end

    for (int round = 1; round <= NUM_ROUNDS; round++) 
    {
        GameMessage msg;
        strcpy(msg.message, "Win!");
        msg.round = round;

        write(pipe_fd[1], &msg, sizeof(GameMessage));
        printf("Parent: sent message for round %d\n", round);
        sleep(2);
    }

    close(pipe_fd[1]); // Close write end so children stop trying to read

    // Collect results
    printf("\n--- Race Results ---\n");

    for (int i = 0; i < NUM_CHILDREN; i++) 
    {
        int status;
        waitpid(children[i], &status, 0);

        if (WIFEXITED(status)) 
        {
            printf("Child PID %d won in round %d\n", children[i], WEXITSTATUS(status));
        }
    }

    return 0;
}
