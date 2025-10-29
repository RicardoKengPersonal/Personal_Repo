#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <time.h>
#include <signal.h>

#define ARRAY_SIZE 100000
#define CHILD_SIZE 20000

int arr[ARRAY_SIZE];

// Function to populate the array with unique values
void populate_array() 
{
    for (int i = 0; i < ARRAY_SIZE; i++) {
        arr[i] = i + 1;  // Assigning values from 1 to 100000
    }
    
}

// Function to search for the number in the assigned range
void search_in_array(int start, int end, int number, int child_num) 
{
    for (int i = start; i < end; i++) {
        if (arr[i] == number) {
            printf("Child %d: Number %d found at index %d\n", child_num, number, i);
            exit(child_num);  // Exit with the child number if the number is found
        }
    }
    exit(0);  // Exit with 0 if the number is not found in this range
}

int main() 
{

    srand(time(NULL)); //generate the random number

    populate_array();

    int number_to_search = rand() % ARRAY_SIZE + 1;  // Random number to search between 1 and 100000

    printf("Parent: Searching for number %d\n", number_to_search);

    pid_t child_pid[5]; // array of 5 children

    int found = 0;

    // Forking 5 child processes
    for (int i = 0; i < 5; i++) 
    {
        child_pid[i] = fork();

        if (child_pid[i] == 0) 
        {
            // Each child searches its own section of the array
            search_in_array(i * CHILD_SIZE, (i + 1) * CHILD_SIZE, number_to_search, i + 1);
        }
    }

    // Parent process: Wait for child processes to terminate
    int status;
    pid_t pid;

    for (int i = 0; i < 5; i++) 
    {
        pid = waitpid(child_pid[i], &status, 0);  // Wait for each child to terminate

        if (pid == -1) 
        {
            perror("waitpid failed");
            exit(EXIT_FAILURE);
        }

        if (WIFEXITED(status)) 
        {
            int child_exit_code = WEXITSTATUS(status);

            if (child_exit_code > 0) 
            {
                // If the child found the number, print its PID and number
                printf("Parent: Number %d was found by child %d with PID %d.\n", number_to_search, child_exit_code, pid);
                found = 1;
                // Kill remaining child processes if number is found
                for (int j = 0; j < 5; j++) 
                {
                    if (child_pid[j] != pid) {
                        kill(child_pid[j], SIGKILL);
                    }
                }
                break;  // Exit the loop as the number is already found
            }
        }
    }

    if (!found) {
        printf("Parent: Number %d not found in the array.\n", number_to_search);
    }

    return 0;
}
