#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

// Function that the threads will run
void* print_string(void* arg) {
    char* str = (char*)arg;
    printf("%s\n", str);
    pthread_exit(NULL);
}

int main() {
    pthread_t thread1, thread2;

    // Strings containing first and last name
    char first_name[] = "Ricardo";
    char last_name[] = "Keng";

    // Create two threads
    if (pthread_create(&thread1, NULL, print_string, first_name) != 0) {
        perror("Error creating thread1");
        exit(1);
    }

    if (pthread_create(&thread2, NULL, print_string, last_name) != 0) {
        perror("Error creating thread2");
        exit(1);
    }

    // Wait for both threads to finish
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    return 0;
}
