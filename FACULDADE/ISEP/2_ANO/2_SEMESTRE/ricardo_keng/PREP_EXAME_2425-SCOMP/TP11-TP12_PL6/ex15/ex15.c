#define _POSIX_C_SOURCE 200809L
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <pthread.h>

typedef struct {
    char string1[100];
    int data_flag;  // 1 = data ready, 0 = data consumed
} shared_struct;

pthread_mutex_t mutex;
pthread_cond_t wrote_string;
pthread_cond_t can_write;

int writers_count = 0;
int readers_count = 0;
int active_readers = 0;
int waiting_readers = 0;

void* reader_func(void* arg)
{
    shared_struct *local = (shared_struct*)arg;
    
    pthread_mutex_lock(&mutex);

    while(!local->data_flag)
    {
        pthread_cond_wait(&wrote_string, &mutex);
    }

    readers_count++;        // total readers that have run (for info)
    active_readers++;       // increment count of active readers

    printf("[READER %d] Read: %s | Active Readers: %d\n", readers_count, local->string1, active_readers);
    
    active_readers--;       // reader finished reading

    if(active_readers == 0)
    {
        pthread_cond_signal(&can_write); // signal writers no readers active
    }

    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}


void* writer_func(void* arg)
{
    shared_struct *local = (shared_struct*)arg;

    pthread_mutex_lock(&mutex);

    while(active_readers > 0)
    {
        pthread_cond_wait(&can_write, &mutex);
    }

    time_t now = time(NULL);
    char *time_str = ctime(&now);
    snprintf(local->string1, sizeof(local->string1), "Thread ID %lu, Time: %s",
             (unsigned long)pthread_self(), time_str);

    writers_count++;
    local->data_flag = 1;

    printf("[WRITER %d] Wrote new string. Total Readers: %d\n", writers_count, readers_count);

    pthread_cond_broadcast(&wrote_string);

    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}


int main() {
    pthread_t readers[5], writers[3];

    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&wrote_string, NULL);
    pthread_cond_init(&can_write, NULL);

    shm_unlink("/ex15_shm");

    int fd = shm_open("/ex15_shm", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if (ftruncate(fd, sizeof(shared_struct)) == -1) {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    shared_struct* shm = mmap(NULL, sizeof(shared_struct), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (shm == MAP_FAILED) {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    strcpy(shm->string1, "Initial string");
    shm->data_flag = 0;

    for (int i = 0; i < 5; i++) {
        if (pthread_create(&readers[i], NULL, reader_func, shm) != 0) {
            perror("pthread_create reader");
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < 3; i++) {
        if (pthread_create(&writers[i], NULL, writer_func, shm) != 0) {
            perror("pthread_create writer");
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < 5; i++) {
        pthread_join(readers[i], NULL);
    }

    for (int i = 0; i < 3; i++) {
        pthread_join(writers[i], NULL);
    }

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&wrote_string);
    pthread_cond_destroy(&can_write);

    munmap(shm, sizeof(shared_struct));
    close(fd);
    shm_unlink("/ex15_shm");

    return 0;
}
