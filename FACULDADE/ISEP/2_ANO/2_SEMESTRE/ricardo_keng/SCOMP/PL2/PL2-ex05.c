#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

#define TIME_SLICE 5 // Time slice in seconds for each process

int main() {
    // List of commands to be executed in a cyclic manner
    char *commands[] = {"ls", "date", "echo 'Hello, World!'"};
    int num_processes = sizeof(commands) / sizeof(commands[0]);
    pid_t pids[num_processes]; // Array to store process IDs

    // Fork child processes for each command
    for (int i = 0; i < num_processes; i++) {
        pid_t pid = fork(); // Create a new process
        if (pid < 0) {
            perror("fork"); // Print error if fork fails
            exit(EXIT_FAILURE);
        }
        if (pid == 0) { // Child process
            // Execute the command using /bin/sh -c to allow shell interpretation
            execlp("/bin/sh", "sh", "-c", commands[i], NULL);
            perror("execlp"); // If execlp fails, print error and exit
            exit(EXIT_FAILURE);
        }
        pids[i] = pid; // Store child process ID
    }

    int current = 0; // Index to track the currently running process
    while (1) {
        kill(pids[current], SIGCONT); // Continue execution of the current process
        sleep(TIME_SLICE); // Allow it to run for TIME_SLICE seconds
        kill(pids[current], SIGSTOP); // Stop the current process
        current = (current + 1) % num_processes; // Move to the next process in a cyclic manner
    }

    // Cleanup (not reached under normal execution, as the loop runs indefinitely)
    for (int i = 0; i < num_processes; i++) {
        kill(pids[i], SIGKILL); // Ensure all child processes are terminated
        waitpid(pids[i], NULL, 0); // Wait for the child process to exit
    }

    return 0; // Program should never reach here
}
