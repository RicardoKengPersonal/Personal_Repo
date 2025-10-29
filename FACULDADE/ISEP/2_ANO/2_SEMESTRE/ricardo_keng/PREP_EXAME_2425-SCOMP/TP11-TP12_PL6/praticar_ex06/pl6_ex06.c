#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

#define NUMS 200
#define THREADS 5
#define FILENAME "numbers.txt"

pthread_mutex_t file_mutex;

void* write_numbers(void* arg) 
{
    int thread_num = *((int*)arg);
    FILE *fp;

    pthread_mutex_lock(&file_mutex);
    fp = fopen(FILENAME, "a");

    if (fp == NULL) 
    {
        perror("Error opening file");
        pthread_mutex_unlock(&file_mutex);
        pthread_exit(NULL);
    }
    
    for (int i = 0; i < NUMS; i++) 
    {
        fprintf(fp, "Thread %d: %d\n", thread_num, i + 1);
    }
    fclose(fp);
    pthread_mutex_unlock(&file_mutex);
    pthread_exit(NULL);
}

int main() 
{
    pthread_t threads[THREADS];
    int thread_ids[THREADS];

    // Clear file before writing
    FILE *fp = fopen(FILENAME, "w");

    if (fp == NULL) 
    {
        perror("Error creating file");
        return 1;
    }
    fclose(fp);

    pthread_mutex_init(&file_mutex, NULL);

    for (int i = 0; i < THREADS; i++) 
    {
        thread_ids[i] = i + 1;
        pthread_create(&threads[i], NULL, write_numbers, &thread_ids[i]);
    }

    for (int i = 0; i < THREADS; i++) 
    {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&file_mutex);

    // Display file contents
    fp = fopen(FILENAME, "r");

    if (fp == NULL) 
    {
        perror("Error opening file for reading");
        return 1;
    }
    
    char buffer[128];

    while (fgets(buffer, sizeof(buffer), fp)) 
    {
        printf("%s", buffer);
    }
    fclose(fp);

    return 0;
}