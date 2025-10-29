#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

#define NUM_PROCESSES 10
#define DATABASE_SIZE 10000

// Record structure for a workshop
typedef struct {
    char name[50];
    char region[50];
    int phone;
} record_t;

// Example database (in practice, you might load this from a file)
record_t database[DATABASE_SIZE];

// Flag to check if the region has been found
int region_found = 0;
pid_t children[NUM_PROCESSES];

// Signal handler for terminating the child processes
void handle_sigint(int sig) {
    // If a region has been found, terminate remaining children
    if (region_found) {
        for (int i = 0; i < NUM_PROCESSES; i++) {
            if (children[i] != 0) {
                kill(children[i], SIGKILL);  // Terminate the child process
            }
        }
    }
}

// Function to simulate searching for the workshop in the database
void search_for_workshop(int start, int end, const char *region) {
    for (int i = start; i < end; i++) {
        if (region_found) return;  // Stop searching if the region is already found
        if (strcmp(database[i].region, region) == 0) {
            // Region found, print the workshop and phone number
            printf("Workshop Found: %s, Phone: %d\n", database[i].name, database[i].phone);
            region_found = 1;
            kill(getppid(), SIGINT);  // Notify the parent that region is found
            return;
        }
    }
}

int main() {
    // Initialize the database with some example data (in practice, this might come from a file)
    for (int i = 0; i < DATABASE_SIZE; i++) {
        snprintf(database[i].name, sizeof(database[i].name), "Workshop %d", i + 1);
        snprintf(database[i].region, sizeof(database[i].region), "Region %d", (i % NUM_PROCESSES) + 1);  // Distribute regions
        database[i].phone = 1000000 + i;
    }

    // Prompt for the region to search for
    char region_to_find[50];
    printf("Enter the region to search for: ");
    scanf("%s", region_to_find);

    // Set up the signal handler to terminate child processes if region is found
    signal(SIGINT, handle_sigint);

    // Create child processes to search the database concurrently
    int chunk_size = DATABASE_SIZE / NUM_PROCESSES;
    for (int i = 0; i < NUM_PROCESSES; i++) {
        pid_t pid = fork();
        
        if (pid < 0) {
            perror("Fork failed");
            exit(1);
        }

        if (pid == 0) {
            // Child process searches a portion of the database
            int start = i * chunk_size;
            int end = (i == NUM_PROCESSES - 1) ? DATABASE_SIZE : (i + 1) * chunk_size;
            search_for_workshop(start, end, region_to_find);
            exit(0);  // Exit child process
        } else {
            // Parent stores child PID for later management
            children[i] = pid;
        }
    }

    // Parent process waits for child processes to finish
    for (int i = 0; i < NUM_PROCESSES; i++) {
        wait(NULL);
    }

    // If no region was found, inform the user
    if (!region_found) {
        printf("Region not found in the database.\n");
    }

    return 0;
}
