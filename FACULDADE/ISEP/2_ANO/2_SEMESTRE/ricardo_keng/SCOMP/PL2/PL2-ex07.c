#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define NUM_CHILDREN 50

// Global variables for parent-child communication
int successful_searches = 0;
int processed_count = 0;

void simulate1(int *result) {
    // Simulate the execution time of simulate1
    sleep(rand() % 3); // Random sleep to simulate computation time

    // Simulate success (1) or failure (0)
    *result = rand() % 2; // 0 or 1 randomly
}

void simulate2() {
    // Simulate the second phase of the algorithm (arbitrary computation)
    sleep(rand() % 3); // Random sleep to simulate computation time
}

void handle_sigusr1(int sig) {
    // Parent receives SIGUSR1, child was successful in simulate1
    successful_searches++;
}

void handle_sigusr2(int sig) {
    // Parent receives SIGUSR2, child failed in simulate1
    // Nothing to do here specifically
}

void child_process(pid_t pid) {
    int result = 0;
    
    simulate1(&result); // Execute simulate1()

    if (result == 1) {
        // Signal parent that we were successful
        kill(getppid(), SIGUSR1);
    } else {
        // Signal parent that we failed
        kill(getppid(), SIGUSR2);
    }

    // Wait for the signal from the parent to proceed with simulate2
    pause();  // Wait for any signal (SIGCONT from parent)

    simulate2();  // Execute simulate2()
}

void parent_process(pid_t children[]) {
    // Set up signal handlers
    struct sigaction sa_sigusr1, sa_sigusr2;
    sa_sigusr1.sa_handler = handle_sigusr1;
    sa_sigusr2.sa_handler = handle_sigusr2;
    sigaction(SIGUSR1, &sa_sigusr1, NULL);
    sigaction(SIGUSR2, &sa_sigusr2, NULL);

    while (processed_count < NUM_CHILDREN) {
        // Block until 25 children have finished their first phase
        if (processed_count == 25) {
            // Check if we found any successful results
            if (successful_searches == 0) {
                printf("Inefficient Algorithm!\n");
                // Terminate all children
                for (int i = 0; i < NUM_CHILDREN; i++) {
                    kill(children[i], SIGKILL);
                }
                return;
            }

            // If there were successful results, send signal to all children to start simulate2
            for (int i = 0; i < NUM_CHILDREN; i++) {
                kill(children[i], SIGCONT);  // Continue each child's execution
            }
        }
        sleep(1);  // Prevent busy waiting
    }

    // Wait for all children to finish
    for (int i = 0; i < NUM_CHILDREN; i++) {
        wait(NULL);
    }
}

int main() {
    srand(time(NULL));  // Seed random number generator

    pid_t children[NUM_CHILDREN];

    // Create child processes
    for (int i = 0; i < NUM_CHILDREN; i++) {
        pid_t pid = fork();
        
        if (pid < 0) {
            perror("Fork failed");
            exit(1);
        }

        if (pid == 0) {
            // Child process: execute the child function
            child_process(getpid());
            exit(0);
        } else {
            // Parent process: store child PID
            children[i] = pid;
        }
    }

    // Parent process manages the simulation
    parent_process(children);

    return 0;
}
