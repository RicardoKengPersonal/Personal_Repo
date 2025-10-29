#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int is_prime(int n) 
{
    if (n < 2) 
    {
        return 0;
    }

    for (int i = 2; i * i <= n; i++) 
    {
        if (n % i == 0) return 0;
    }
    return 1;
}

void* thread_func(void* arg) 
{
    int num = *((int*)arg);
    free(arg);

    char buffer[64];

    for (int i = 2; i <= num; i++) 
    {
        if (is_prime(i)) 
        {
            int len = snprintf(buffer, sizeof(buffer), "%d is prime\n", i);
            write(STDOUT_FILENO, buffer, len); // thread safe approach
        }
    }
    pthread_exit(NULL);
}

int main() 
{
    pthread_t thread;
    int num;

    printf("Enter a positive number: ");
    scanf("%d", &num);

    int *arg = malloc(sizeof(int));
    *arg = num;

    pthread_create(&thread, NULL, thread_func, arg);
    pthread_join(thread, NULL);

    return 0;
}