#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

#define TOTAL_RECORDS 50000
#define NUM_PROCESSES 10
#define CHUNK_SIZE (TOTAL_RECORDS / NUM_PROCESSES)
#define MAX_MATCHES TOTAL_RECORDS

typedef struct {
    int customer_code;
    int product_code;
    int quantity;
} record_t;

int main() {
    record_t sales[TOTAL_RECORDS];
    int larger[MAX_MATCHES];
    int larger_index = 0;

    int pipe_fd[2];
    pid_t pids[NUM_PROCESSES];

    srand(time(NULL));

    // Fill the sales array with random data
    for (int i = 0; i < TOTAL_RECORDS; i++) 
    {
        sales[i].customer_code = rand() % 50000;
        sales[i].product_code = rand() % 50000;
        sales[i].quantity = rand() % 50;  // some < 20, some > 20
    }

    // Create a pipe for IPC
    if (pipe(pipe_fd) == -1) 
    {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    // Spawn child processes
    for (int i = 0; i < NUM_PROCESSES; i++) 
    {
        pid_t pid = fork();

        if (pid == -1) 
        {
            perror("fork failed");
            exit(EXIT_FAILURE);
        }

        if (pid == 0) 
        {
            // Child process
            close(pipe_fd[0]);  // close read end

            int start = i * CHUNK_SIZE;
            int end = start + CHUNK_SIZE;

            for (int j = start; j < end; j++) 
            {
                if (sales[j].quantity > 20) 
                {
                    int code = sales[j].product_code;
                    write(pipe_fd[1], &code, sizeof(int));
                }
            }

            close(pipe_fd[1]);
            exit(EXIT_SUCCESS);
        } else 
        {
            pids[i] = pid;
        }
    }

    // Parent process
    close(pipe_fd[1]); // close write end

    int product_code;

    while (read(pipe_fd[0], &product_code, sizeof(int)) > 0) 
    {
        larger[larger_index++] = product_code;
    }

    close(pipe_fd[0]);

    // Wait for all children to finish
    for (int i = 0; i < NUM_PROCESSES; i++) 
    {
        waitpid(pids[i], NULL, 0);
    }

    // Print results
    printf("Product codes with quantity > 20:\n");
    for (int i = 0; i < larger_index; i++) 
    {
        printf("%d ", larger[i]);

        if (i % 20 == 19)
        {
            printf("\n"); // break lines for readability
        }
    }
    printf("\nTotal found: %d\n", larger_index);

    return 0;
}
