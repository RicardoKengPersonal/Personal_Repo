#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>  // For exit() function

int main(void)
{
    pid_t p, a;

    // First fork
    p = fork();
    if (p == -1) {
        // Handle fork failure for the first fork
        perror("First fork failed");
        exit(1);  // Exit the program with a non-zero status indicating failure
    }

    // Second fork
    a = fork();
    if (a == -1) {
        // Handle fork failure for the second fork
        perror("Second fork failed");
        exit(1);  // Exit the program with a non-zero status indicating failure
    }

    printf("Concurrent Programming\n");

    return 0;
}
