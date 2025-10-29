#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <sys/wait.h>

int main() {
    // Ignore SIGUSR1 and SIGUSR2 so they have no effect on this process
    signal(SIGUSR1, SIG_IGN);
    signal(SIGUSR2, SIG_IGN);

    // Create a child process
    pid_t pid = fork();
    
    if (pid < 0) {
        // If fork fails, print error and exit
        perror("fork");
        exit(EXIT_FAILURE);
    }
    if (pid == 0) {
        // Child process: prompt user and read input
        write(STDOUT_FILENO, "Write a sentence: ", 18);
        char sentence[256];
        // Wait for user input
        if (fgets(sentence, sizeof(sentence), stdin) != NULL) {
            // If input is received, print it
            printf("You wrote: %s", sentence);
        }
        exit(0); // Child exits after handling input
    } else {
        // Parent process: wait up to 10 seconds for child to finish
        int status;
        for (int i = 0; i < 10; ++i) {
            sleep(1); // Sleep for 1 second
            // Check if child has finished without blocking
            if (waitpid(pid, &status, WNOHANG) > 0) {
                exit(0); // If child finished, parent exits
            }
        }
        // If 10 seconds pass and child hasn't finished, kill child
        kill(pid, SIGKILL);
        const char msg[] = "You are too slow!\n";
        write(STDOUT_FILENO, msg, sizeof(msg) - 1); // Inform user of timeout
        wait(NULL); // Clean up child process
    }
    return 0;