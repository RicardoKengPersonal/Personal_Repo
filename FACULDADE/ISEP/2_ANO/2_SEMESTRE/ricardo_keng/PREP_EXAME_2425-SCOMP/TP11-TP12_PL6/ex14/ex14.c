#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#define MAX_CAP 10              // Circular buffer max capacity
#define MAX_EXCHANGE 30         // Max number of numbers exchanged

int circular_buffer[MAX_CAP];   // The circular buffer
int in = 0;                     // Next position to insert (producer index)
int out = 0;                    // Next position to remove (consumer index)
int buffer_count = 0;           // Number of elements currently in the buffer
int produced = 0;               // Total produced values
int consumed = 0;               // Total consumed values

pthread_mutex_t mutex;          // Mutex for mutual exclusion on buffer
pthread_cond_t not_full;        // Condition variable: buffer is not full
pthread_cond_t not_empty;       // Condition variable: buffer is not empty

// Producer thread function
void *insert_increment(void* arg)
{
    (void)arg; // Mark argument as unused

    while(1)
    {
        pthread_mutex_lock(&mutex); // Only one thread accesses the buffer at a time

        // Stop if enough values have been produced
        if(produced >= MAX_EXCHANGE)
        {
            pthread_mutex_unlock(&mutex);
            break;
        }

        // If the buffer is full, wait for the consumer to consume an item
        while(buffer_count == MAX_CAP)
        {
            pthread_cond_wait(&not_full, &mutex);
            // When signaled by the consumer, check again if space is available
        }

        // Double check after waiting (another producer may have finished)
        if(produced >= MAX_EXCHANGE)
        {
            pthread_mutex_unlock(&mutex);
            break;
        }

        int value = produced + 1; // Value to insert in the buffer (incrementing)
        circular_buffer[in] = value; // Insert value at the 'in' position

        // Move 'in' to the next position, wrapping around if needed (circular buffer)
        in = (in + 1) % MAX_CAP;

        buffer_count++; // Increase the count of items in the buffer
        produced++;     // Increase the total produced count

        printf("[Producer %lu] Produced: %d (Buffer Count: %d)\n",
               (unsigned long)pthread_self(), value, buffer_count);

        // Signal the consumer that the buffer is not empty anymore
        pthread_cond_signal(&not_empty);

        pthread_mutex_unlock(&mutex);

        usleep(100000); // Simulate work
    }

    return NULL;
}

// Consumer thread function
void* consumer_func(void* arg)
{
    (void)arg; // Mark argument as unused

    while (1) {
        pthread_mutex_lock(&mutex);

        // Stop if enough values have been consumed
        if (consumed >= MAX_EXCHANGE) {
            pthread_mutex_unlock(&mutex);
            break;
        }

        // Wait if the buffer is empty
        while (buffer_count == 0) {
            pthread_cond_wait(&not_empty, &mutex);
            // When signaled by a producer, check again if there is data to consume
        }

        // Double-check after waiting (another consumer may have finished)
        if (consumed >= MAX_EXCHANGE) {
            pthread_mutex_unlock(&mutex);
            break;
        }

        int value = circular_buffer[out]; // Take value from the 'out' position

        // Move 'out' to the next position, wrapping around if needed (circular buffer)
        out = (out + 1) % MAX_CAP;

        buffer_count--; // Decrease the count of items in the buffer
        consumed++;     // Increase the total consumed count

        printf("[Consumer] Consumed: %d (Buffer count: %d)\n", value, buffer_count);

        // Signal the producer that the buffer is not full anymore
        pthread_cond_signal(&not_full);

        pthread_mutex_unlock(&mutex);

        usleep(150000); // Simulate work
    }
    return NULL;
}

int main()
{
    pthread_t producer1, producer2, consumer; // 3 threads: 2 producers, 1 consumer

    // Create threads
    pthread_create(&producer1, NULL, insert_increment, NULL);
    pthread_create(&producer2, NULL, insert_increment, NULL);
    pthread_create(&consumer, NULL, consumer_func, NULL);

    // Wait for threads to finish
    pthread_join(producer1, NULL);
    pthread_join(producer2, NULL);
    pthread_join(consumer, NULL);

    exit(0);
}