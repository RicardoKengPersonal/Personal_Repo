#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define SIZE 100000

int data[SIZE];
int result[SIZE];

void initialize_array() {
    // Seed for random number generation
    srand(time(NULL));
    for (int i = 0; i < SIZE; i++) {
        data[i] = (rand() % 10) + 1;
    }
}

void print_results(int start, int end) {
    for (int i = start; i < end; i++) {
        printf("result[%d] = %d\n", i, result[i]);
    }
}

int main() {
    pid_t pid;
    initialize_array();

    pid = fork();

    if (pid == -1) {
        // Fork failed
        perror("Fork failed");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        // Child process: handle second half
        for (int i = SIZE / 2; i < SIZE; i++) {
            result[i] = data[i] * 4 + 20;
        }
        exit(0);
    } else {
        // Parent process: handle first half
        for (int i = 0; i < SIZE / 2; i++) {
            result[i] = data[i] * 4 + 20;
        }
        wait(NULL); // Wait for child to finish

        // Sequential presentation
        print_results(0, SIZE / 2);
        print_results(SIZE / 2, SIZE);
    }

    return 0;
}
