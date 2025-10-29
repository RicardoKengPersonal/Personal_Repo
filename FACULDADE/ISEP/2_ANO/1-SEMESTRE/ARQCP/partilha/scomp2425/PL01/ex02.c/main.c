#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>  // For exit() and status handling

int main(void)
{
    pid_t p1, p2;
    int status;

    // First fork - create the first child process
    p1 = fork();
    if (p1 == -1) {
        perror("First fork failed");
        exit(1);
    }

    if (p1 == 0) {
        // In the first child process
        printf("I am the first child. My PID is %d\n", getpid());
        sleep(5);  // Sleep for 5 seconds
        exit(1);  // Exit with status 1
    }

    // Second fork - create the second child process
    p2 = fork();
    if (p2 == -1) {
        perror("Second fork failed");
        exit(1);
    }

    if (p2 == 0) {
        // In the second child process
        printf("I am the second child. My PID is %d\n", getpid());
        exit(2);  // Exit with status 2
    }

    // Parent process
    printf("I am the father. My PID is %d\n", getpid());

    // Wait for the first child to finish
    waitpid(p1, &status, 0);
    if (WIFEXITED(status)) {
        printf("The first child finished. Exit status: %d\n", WEXITSTATUS(status));
    } else {
        printf("The first child did not terminate normally.\n");
    }

    // Wait for the second child to finish
    waitpid(p2, &status, 0);
    if (WIFEXITED(status)) {
        printf("The second child finished. Exit status: %d\n", WEXITSTATUS(status));
    } else {
        printf("The second child did not terminate normally.\n");
    }

    return 0;
}
