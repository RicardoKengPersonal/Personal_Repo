#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t pid;
    pid_t child2_pid = -1;
    int status;
    int i;

    for (i = 1; i <= 3; i++)
    {
        pid = fork();

        if (pid == -1)
        {
            perror("Error forking");
            exit(1);
        }

        if (pid == 0)
        {
            // Child process
            printf("Child %d with PID = %d\n", i, getpid());
            sleep(2 * i); // Different sleep to simulate different durations
            exit(i);
        }
        else
        {
            // Parent process
            if (i == 2) {
                child2_pid = pid;
            }
        }
    }

    // Parent attempts non-blocking wait for second child
    printf("Parent is checking on second child (PID = %d) without blocking...\n", child2_pid);

    int result = waitpid(child2_pid, &status, WNOHANG);
    if (result == 0) {
        printf("Second child is still running.\n");
    } else if (result == -1) {
        perror("waitpid failed");
    } else {
        if (WIFEXITED(status)) {
            printf("Second child exited with code %d\n", WEXITSTATUS(status));
        }
    }

    // Optional: wait for all remaining children normally
    while (wait(NULL) > 0);

    return 0;
}



// a) 2^3 processos, 8 processos
// c) colocar exit(1) no processo filho
// e) if (pid == -1) ....
// f) wait(NULL) no pai