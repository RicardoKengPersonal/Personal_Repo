#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>

void handle_signal(int sig) 
{
    const char msg[] = "Parent received SIGSEV from child.\n";
    write(STDOUT_FILENO, msg, sizeof(msg) - 1);
    exit(EXIT_FAILURE); // Exit the program with an error code
}

int main()
{
    int a; 

    struct sigaction act;
    memset(&act, 0, sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_RESTART;
    sigaction(SIGSEGV, &act, NULL); 

    // Trigger a segmentation fault by dereferencing a null pointer
    // or accessing an invalid memory location.
    // This will cause the SIGSEGV signal to be raised.
    a = *(int *)0;

    return 0; 
}