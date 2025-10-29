#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

// Define the structure to store command and its max execution time
typedef struct {
    char cmd[32];
    int time;
} command_t;

// Array of commands with their respective max execution times
command_t commands[] = {
    {"ls", 3},
    {"date", 2},
    {"echo 'Hello, World!'", 4},
    {"sleep 10", 5} // This command will be terminated before completion // "sleep 10" means it will execute sleep for 10 seconds, if it was "sleep 1" it wouldn't fail
};

int num_commands = sizeof(commands) / sizeof(commands[0]);

int main() 
{
    for (int i = 0; i < num_commands; i++) 
    {
        pid_t pid = fork(); // Create a new process

        if (pid < 0) 
        {
            perror("fork"); // Print error if fork fails
            exit(EXIT_FAILURE);
        }
        if (pid == 0) 
        { // Child process
            execlp("/bin/sh", "sh", "-c", commands[i].cmd, NULL); //is used to execute a shell command stored in commands[i].cmd.
            perror("execlp"); // If execlp fails, print error and exit
            exit(EXIT_FAILURE);
        }
        
        // Parent process: monitor execution time
        sleep(commands[i].time); // Allow the process to run for its max time

        if (waitpid(pid, NULL, WNOHANG) == 0) // If we used waitpid(pid, NULL, 0); instead, the parent process would block (pause execution) until the child process finishes. Using WNOHANG allows the program to check if the process is still running without waiting.
        { // Check if process is still running
            printf("Command '%s' exceeded time limit (%d sec), terminating...\n", commands[i].cmd, commands[i].time);
            kill(pid, SIGKILL); // Terminate the process
            waitpid(pid, NULL, 0); // Clean up the terminated process
        }
    }

    return 0;
}
