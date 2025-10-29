#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdbool.h>

// Function to check if a number is prime
bool is_prime(int num) 
{
    if (num < 2) return false;
    for (int i = 2; i*i <= num; i++) {
        if (num % i == 0) return false;
    }
    return true;
}

// Thread function to print all primes up to a given limit
void* print_primes(void* arg) 
{
    int max = *((int*)arg);
    printf("Prime numbers up to %d:\n", max);
    
    for (int i = 2; i <= max; i++) 
    {
        if (is_prime(i)) {
            printf("%d ", i);
        }
    }
    printf("\n");
    pthread_exit(NULL);
}

int main() 
{
    int max_value;
    pthread_t prime_thread;

    // Get user input
    printf("Enter the highest positive value: ");
    scanf("%d", &max_value);

    if (max_value < 2) 
    {
        printf("No primes to display.\n");
        return 0;
    }

    // Create the thread
    if (pthread_create(&prime_thread, NULL, print_primes, &max_value) != 0) 
    {
        perror("Failed to create thread");
        exit(1);
    }

    // Wait for the thread to complete
    pthread_join(prime_thread, NULL);

    return 0;
}
