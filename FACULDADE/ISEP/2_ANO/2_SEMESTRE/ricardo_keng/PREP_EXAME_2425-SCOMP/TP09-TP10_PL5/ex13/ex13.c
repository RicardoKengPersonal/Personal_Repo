#define _POSIX_C_SOURCE 200809L
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <semaphore.h>
#include <errno.h>

typedef struct {
    char string[100];
    int active_readers;
    int total_readers;
    int total_writers;
} shared_data_t;

const char *shm_name = "/ex13_shm";
const char *sem_mutex_name = "/sem_mutex_ex13";
const char *sem_wrt_name = "/sem_wrt_ex13";
const char *sem_readTry_name = "/sem_readTry_ex13";

void reader(shared_data_t *shm, sem_t *mutex, sem_t *wrt, sem_t *readTry) {
    // Reader entry section
    sem_wait(readTry);       // prevent writer from locking if readers active
    sem_wait(mutex);
    shm->active_readers++;
    shm->total_readers++;
    if (shm->active_readers == 1) {
        sem_wait(wrt);       // first reader locks writer out
    }
    sem_post(mutex);
    sem_post(readTry);

    // Critical section: read shared string and print info
    printf("[READER %d] Read: %s | Active Readers: %d\n", shm->total_readers, shm->string, shm->active_readers);
    fflush(stdout);

    // Simulate reading time
    usleep(100000);

    // Reader exit section
    sem_wait(mutex);
    shm->active_readers--;
    if (shm->active_readers == 0) {
        sem_post(wrt);       // last reader releases writer lock
    }
    sem_post(mutex);

    exit(0);
}

void writer(shared_data_t *shm, sem_t *mutex, sem_t *wrt, sem_t *readTry) {
    sem_wait(wrt);       // lock writer exclusive access

    shm->total_writers++;
    time_t now = time(NULL);
    char time_str[64];
    strftime(time_str, sizeof(time_str), "%c", localtime(&now));
    snprintf(shm->string, sizeof(shm->string), "PID %d, Time: %s", getpid(), time_str);

    // Print info
    sem_wait(mutex);
    int readers = shm->active_readers;
    int writers = shm->total_writers;
    sem_post(mutex);

    printf("[WRITER %d] Wrote string. Active Readers: %d\n", writers, readers);
    fflush(stdout);

    // Simulate writing time
    usleep(150000);

    sem_post(wrt);

    exit(0);
}

int main() {
    int fd;
    shared_data_t *shm;
    sem_t *mutex, *wrt, *readTry;

    // Unlink old semaphores and shm (cleanup)
    sem_unlink(sem_mutex_name);
    sem_unlink(sem_wrt_name);
    sem_unlink(sem_readTry_name);
    shm_unlink(shm_name);

    // Create and initialize semaphores
    mutex = sem_open(sem_mutex_name, O_CREAT | O_EXCL, 0644, 1);
    wrt = sem_open(sem_wrt_name, O_CREAT | O_EXCL, 0644, 1);
    readTry = sem_open(sem_readTry_name, O_CREAT | O_EXCL, 0644, 1);
    if (mutex == SEM_FAILED || wrt == SEM_FAILED || readTry == SEM_FAILED) {
        perror("sem_open");
        exit(EXIT_FAILURE);
    }

    // Create and size shared memory
    fd = shm_open(shm_name, O_CREAT | O_RDWR, 0644);
    if (fd == -1) {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if (ftruncate(fd, sizeof(shared_data_t)) == -1) {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    shm = mmap(NULL, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (shm == MAP_FAILED) {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    // Initialize shared memory
    strcpy(shm->string, "Initial string");
    shm->active_readers = 0;
    shm->total_readers = 0;
    shm->total_writers = 0;

    // Create multiple readers and writers
    const int num_readers = 5;
    const int num_writers = 3;

    pid_t pids[num_readers + num_writers];
    int idx = 0;

    for (int i = 0; i < num_readers; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // Reader child
            reader(shm, mutex, wrt, readTry);
        } else if (pid > 0) {
            pids[idx++] = pid;
        } else {
            perror("fork");
        }
    }

    for (int i = 0; i < num_writers; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // Writer child
            writer(shm, mutex, wrt, readTry);
        } else if (pid > 0) {
            pids[idx++] = pid;
        } else {
            perror("fork");
        }
    }

    // Wait for all children
    for (int i = 0; i < idx; i++) {
        waitpid(pids[i], NULL, 0);
    }

    // Cleanup
    munmap(shm, sizeof(shared_data_t));
    close(fd);
    shm_unlink(shm_name);

    sem_close(mutex);
    sem_close(wrt);
    sem_close(readTry);
    sem_unlink(sem_mutex_name);
    sem_unlink(sem_wrt_name);
    sem_unlink(sem_readTry_name);

    return 0;
}
