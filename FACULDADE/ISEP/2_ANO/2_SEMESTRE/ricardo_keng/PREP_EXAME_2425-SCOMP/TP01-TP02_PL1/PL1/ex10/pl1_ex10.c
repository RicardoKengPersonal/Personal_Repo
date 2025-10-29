#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define SIZE 100000
#define CHILDREN 5
#define SEGMENT (SIZE / CHILDREN)

int main()
{
    pid_t pids[CHILDREN];
    int num;
    int vec[SIZE];
    int found_child = 0, found_pid = 0;

    // Fill array with 0 to SIZE-1
    for (int i = 0; i < SIZE; i++) {
        vec[i] = i;
    }

    // Shuffle array (Fisher-Yates)
    srand(time(NULL));
    for (int i = SIZE - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = vec[i];
        vec[i] = vec[j];
        vec[j] = temp;
    }

    printf("Number to search for: ");
    scanf("%d", &num);

    for (int i = 0; i < CHILDREN; i++)
    {
        pids[i] = fork();

        if (pids[i] < 0)
        {
            perror("Fork failed");
            exit(EXIT_FAILURE);
        }

        if (pids[i] == 0)
        {
            // Child process
            int start = i * SEGMENT;
            int end = start + SEGMENT;
            for (int j = start; j < end; j++)
            {
                if (vec[j] == num)
                {
                    printf("Child %d [PID=%d] found number %d at position %d\n", i + 1, getpid(), num, j);
                    exit(i + 1); // Exit with child number
                }
            }
            exit(0); // Not found
        }
    }

    // Parent process waits for all children
    for (int i = 0; i < CHILDREN; i++)
    {
        int status;
        pid_t pid = wait(&status);
        if (WIFEXITED(status) && WEXITSTATUS(status) > 0 && found_child == 0)
        {
            found_child = WEXITSTATUS(status);
            found_pid = pid;
        }
    }

    if (found_child)
    {
        printf("Parent: Number found by child %d (PID=%d)\n", found_child, found_pid);
    }
    else
    {
        printf("Parent: Number not found in the array.\n");
    }

    return 0;
}