#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>

volatile sig_atomic_t done = 0;

// Signal handler for SIGUSR1
void handler(int sig) {
    done = 1;
}

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <seconds>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    int n = atoi(argv[1]);
    if (n <= 0) {
        fprintf(stderr, "Please provide a positive number of seconds.\n");
        exit(EXIT_FAILURE);
    }

    // Block all signals except SIGUSR1
    sigset_t mask, oldmask;
    sigfillset(&mask);
    sigdelset(&mask, SIGUSR1); // Allow SIGUSR1

    // Set up handler for SIGUSR1
    struct sigaction act;
    memset(&act, 0, sizeof(act));
    act.sa_handler = handler;
    sigaction(SIGUSR1, &act, NULL);

    // Set alarm to send SIGUSR1 after n seconds
    alarm(n);

    // Wait for SIGUSR1
    while (!done) {
        sigsuspend(&mask);
    }

    printf("Slept for %d seconds (simulated sleep).\n", n);
    return 0;
}