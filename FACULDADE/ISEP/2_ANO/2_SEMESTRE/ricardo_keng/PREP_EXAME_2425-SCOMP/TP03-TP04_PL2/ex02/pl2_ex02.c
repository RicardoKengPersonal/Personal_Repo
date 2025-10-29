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
    printf("Signal %d received. Division by zero is not allowed.\n", sig);
    exit(EXIT_FAILURE); // Exit the program with an error code
}

int main()
{
    int num1, num2, result;
    struct sigaction act;

    memset(&act, 0, sizeof(struct sigaction));
    act.sa_handler = handle_signal;
    act.sa_flags = SA_RESTART;
    sigaction(SIGFPE, &act, NULL);

    printf("Enter the divisior: ");
    scanf("%d", &num1);
    printf("Enter the dividend: ");
    scanf("%d", &num2);

    result = num2 / num1;
    printf("The result of %d / %d is: %d\n", num2, num1, result);

    return 0;

}