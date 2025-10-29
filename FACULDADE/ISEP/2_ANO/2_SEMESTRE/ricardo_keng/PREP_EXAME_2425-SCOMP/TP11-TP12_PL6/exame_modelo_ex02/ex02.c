#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

#define MAX_READERS 5
#define MAX_WRITERS 3

typedef struct {
    char str1[100]; // estrutura na heap
    char str2[100];
} SharedData;

SharedData *shared;

pthread_mutex_t mutex;
pthread_cond_t can_read;
pthread_cond_t can_write;

int active_readers = 0;
int waiting_writers = 0;
int writer_active = 0;

// Reader thread
void* reader(void* arg) 
{
    int id = *((int*)arg);
    free(arg);

    while (1) 
    {
        pthread_mutex_lock(&mutex);

        while (writer_active || waiting_writers > 0) 
        {
            pthread_cond_wait(&can_read, &mutex);
        }
        active_readers++;
        printf("Reader %d starts reading (active_readers: %d)\n", id, active_readers);
        pthread_mutex_unlock(&mutex);

        // Simulate reading
        usleep(rand() % 500000);
        printf("Reader %d read: str1=\"%s\", str2=\"%s\"\n", id, shared->str1, shared->str2);

        pthread_mutex_lock(&mutex);
        active_readers--;

        if (active_readers == 0)
        {
            pthread_cond_signal(&can_write);
        }

        pthread_mutex_unlock(&mutex);

        sleep(rand() % 3);
    }
    return NULL;
}

// Writer thread
void* writer(void* arg) 
{
    int id = *((int*)arg);
    free(arg);

    while (1) 
    {
        pthread_mutex_lock(&mutex);
        waiting_writers++;

        while (active_readers > 0 || writer_active) 
        {
            pthread_cond_wait(&can_write, &mutex);
        }

        waiting_writers--;
        writer_active = 1;
        printf("Writer %d starts writing\n", id);
        pthread_mutex_unlock(&mutex);

        // Simulate writing
        time_t now = time(NULL);
        snprintf(shared->str1, sizeof(shared->str1), "Writer %d", id);
        snprintf(shared->str2, sizeof(shared->str2), "Time: %s", ctime(&now));
        usleep(rand() % 500000);

        pthread_mutex_lock(&mutex);
        writer_active = 0;
        printf("Writer %d finished writing (active_readers: %d, waiting_writers: %d)\n",id, active_readers, waiting_writers);

        pthread_cond_broadcast(&can_read);
        pthread_cond_signal(&can_write);
        pthread_mutex_unlock(&mutex);

        sleep(rand() % 5);
    }
    return NULL;
}

int main() 
{
    srand(time(NULL));

    shared = malloc(sizeof(SharedData));
    strcpy(shared->str1, "Initial String 1");
    strcpy(shared->str2, "Initial String 2");

    pthread_t readers[MAX_READERS], writers[MAX_WRITERS];

    for (int i = 0; i < MAX_READERS; i++) 
    {
        int *id = malloc(sizeof(int));
        *id = i + 1;
        pthread_create(&readers[i], NULL, reader, id);
    }

    for (int i = 0; i < MAX_WRITERS; i++) 
    {
        int *id = malloc(sizeof(int));
        *id = i + 1;
        pthread_create(&writers[i], NULL, writer, id);
    }

    // Let it run indefinitely
    for (int i = 0; i < MAX_READERS; i++) 
    {
        pthread_join(readers[i], NULL);
    }

    for (int i = 0; i < MAX_WRITERS; i++) 
    {
        pthread_join(writers[i], NULL);
    }

    free(shared);
    return 0;
}
