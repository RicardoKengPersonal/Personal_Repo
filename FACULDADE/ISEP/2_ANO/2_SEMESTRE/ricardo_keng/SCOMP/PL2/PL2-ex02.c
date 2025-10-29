#define _GNU_SOURCE // é preciso para o siginfo funcionar
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>

void handle_sigfpe(int signum, siginfo_t *info, void *context) {
    printf("Error: Division by zero caught!\n");
    exit(EXIT_FAILURE);
}

int main() {
    int a = 5;
    int b = 0;

    struct sigaction sa;

    // Set up the sigaction structure
    memset(&sa, 0, sizeof(sa));
    sa.sa_sigaction = handle_sigfpe;   // Use the handler with additional info
    sa.sa_flags = SA_SIGINFO;          // Specify that we want siginfo_t to be passed to the handler

    // Set up signal handler for SIGFPE (Floating Point Exception)
    sigaction(SIGFPE, &sa, NULL);

    // This will trigger a SIGFPE due to division by zero
    int result = a / b;

    // This line will never be reached because the program will exit in the handler
    printf("Result: %d / %d = %d\n", a, b, result);

    return 0;
}
