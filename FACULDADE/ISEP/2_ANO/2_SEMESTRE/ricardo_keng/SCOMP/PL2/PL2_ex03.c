#define _XOPEN_SOURCE 700
#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <string.h>

void handle_sigsegv(int signum, siginfo_t *info, void *context)
{
    printf("I got signal SIGSEGV.\n");
    exit(EXIT_FAILURE);
}

int main()
{
    struct sigaction sa;

    memset(&sa, 0, sizeof(sa));
    sa.sa_sigaction = handle_sigsegv;   // Use the handler with additional info
    sa.sa_flags = SA_SIGINFO;          // Specify that we want siginfo_t to be passed to the handler

    // Set up signal handler for SIGFPE (Floating Point Exception)
    sigaction(SIGSEGV, &sa, NULL);

    int a;
    a = *(int*)0;

    return 0;
}

