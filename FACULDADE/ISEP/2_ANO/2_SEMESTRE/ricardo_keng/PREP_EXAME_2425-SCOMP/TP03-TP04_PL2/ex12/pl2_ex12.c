#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>

void handle_sigusr1(int sig, siginfo_t *info, void *context) {
    (void)sig;
    (void)context;
    char msg[100];
    int len = snprintf(
        msg, sizeof(msg),
        "I captured a SIGUSR1 sent by the process with PID %d\n",
        info->si_pid
    );
    write(STDOUT_FILENO, msg, len);
}

int main() {
    struct sigaction act;
    memset(&act, 0, sizeof(act));
    act.sa_sigaction = handle_sigusr1; // Use sa_sigaction for extra info
    act.sa_flags = SA_SIGINFO;         // Enable extended signal info

    // Register handler for SIGUSR1
    sigaction(SIGUSR1, &act, NULL);

    // Print own PID for testing
    printf("My PID is %d. Send SIGUSR1 to me (e.g., kill -USR1 %d)\n", getpid(), getpid());
    fflush(stdout);

    // Wait for signals indefinitely
    while (1) {
        pause();
    }
}