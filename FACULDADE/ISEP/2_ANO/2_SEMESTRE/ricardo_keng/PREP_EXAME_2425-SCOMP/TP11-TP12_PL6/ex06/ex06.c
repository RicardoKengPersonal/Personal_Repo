#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>
#include <time.h>

#define SIZE 200

typedef struct{
    int nums_to_write;
    int thread_num;
}Args;

pthread_mutex_t mutex;

void* write_on_file(void* arg)
{
    pthread_mutex_lock(&mutex);

    Args func_struct = *((Args*)arg);

    FILE *file = fopen("output.txt","a");

    if(file == NULL)
    {
        perror("File doesnt exist");
        exit(EXIT_FAILURE);
        pthread_exit(NULL);
    }

    for(int i = 0; i < func_struct.nums_to_write; i++)
    {
        fprintf(file,"Written by thread %d ---- Num: %d\n",func_struct.thread_num,i);
    }
    fclose(file);

    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[5];

    // Clear file contents first
    FILE *file = fopen("output.txt", "w");

    if (file == NULL) 
    {
        perror("Error clearing file");
        return 1;
    }

    fclose(file);

    pthread_mutex_init(&mutex,NULL);

    for(int i = 0; i < 5; i++)
    {
        Args *arg;

        arg = malloc(sizeof(Args));

        arg->nums_to_write = SIZE;
        arg->thread_num = i;

        pthread_create(&threads[i],NULL,write_on_file,(void*)arg);
    }

    for(int i = 0; i < 5; i++)
    {
        pthread_join(threads[i],NULL); // wait for threads to finish
    }

    // Read and display the file content
    printf("\n--- File Contents ---\n");
    file = fopen("output.txt", "r");
    if (file == NULL) {
        perror("Error reading file");
        return 1;
    }

    char buffer[256];
    while (fgets(buffer, sizeof(buffer), file)) {
        printf("%s", buffer);
    }
    fclose(file);

    pthread_mutex_destroy(&mutex);

    exit(0);
}