Introduction
 Multiple processes often need to share resources (e.g.,
memory, files, devices)
 Without proper coordination, data corruption, inconsistency,
and unpredictable behavior can occur
 We will explore increasingly complex synchronization
challenges, starting with solving a race condition before
moving to more advanced topics
▪ Lectures offer a comprehensive and in-depth discussion on the topic

Race condition
 Two or more processes attempt to access a shared resource
(e.g., memory, file, or device), with at least one performing a
write
 A race condition arises when the system’s correctness depends
on the unpredictable order of execution of these processes
 Uncontrolled race conditions can lead to data corruption,
inconsistencies, or unexpected system behavior

Synchronization mechanisms
 To ensure data consistency, it is essential to provide
mechanisms that enforce a specific order between cooperating
processes
 Semaphores are a classic synchronization mechanism that can
be used to:
1. Limit the simultaneous access of n processes to a shared resource
2. Signal the occurrence of events

Semaphore
 A semaphore is an OS-provided object that holds an integer
value, typically greater than or equal to zero
▪ (in most implementations)
 Attempting to decrement the semaphore below zero blocks the
process until the operation becomes possible
▪ i.e., the semaphore remains non-negative
▪ In this case, the process is placed in the semaphore’s waiting queue and
releases the CPU

Semaphore
 A semaphore can only be manipulated through a specific
interface
▪ Traditionally, those operations are called down() and up()
▪ In the POSIX interface, these operations are called sem_wait() and
sem_post(), respectively
 The OS must guarantee that these operations are atomic
▪ i.e., cannot be interrupted

Initial value
 The initial value of a semaphore plays a crucial role in its
behavior
 1 → Ensures mutual exclusion when accessing shared resources
 0 → Synchronizes processes with events
 ≥ 0 → Controls access to resources with limited capacity

Example – Mutual exclusion
 Based in previous example (slide 3)
▪ Initial value of semaphore is 1
▪ Assume parent executes down() first


Advantages
 Efficiency
▪ Can be efficiently implemented across different platforms
▪ Reduces active waiting
 Flexibility
▪ Limits resource access to n processes
▪ Signals events, allowing solutions to a wide range of synchronization
problems

Challenges
 Prone to synchronization errors
▪ Failing to properly protect shared resources can cause inconsistencies or
even deadlocks (where processes remain blocked indefinitely)
 Unpredictable execution order
▪ When multiple processes wait for a resource, the order in which they are
served may be unpredictable

POSIX semaphores in Linux
 sem_open()
▪ Create/open a semaphore
 sem_post()
▪ Increment (up) the semaphore’s value
 sem_wait(), sem_trywait(), sem_timedwait()
▪ Try to decrement (down) the semaphore’s value
 sem_getvalue()
▪ Get the current semaphore’s value

POSIX semaphores in Linux
 In Linux, these functions are part of the POSIX Thread Library
(libpthread) so, it is necessary to link against the pthread
library

$ gcc -Wall -Wextra -g -o my_program my_program.c -pthread

The sem_open function
#include <semaphore.h>
sem_t *sem_open(const char *name, int oflag, mode_t mode,
 unsigned int value);
sem_t *sem_open(const char *name, int oflag);

 Creates a new or opens an existing semaphore
▪ The variant with two parameters is used to open an existent semaphore
 Returns
▪ the semaphore address, if successful
▪ -1 (SEM_FAILED), in case of error

 The semaphore is identified by name
 The oflag argument specifies flags that control the operation
of the call
▪ O_CREAT: creates a new semaphore, if it not exists
▪ O_EXCL: (with O_CREAT) creates a new semaphore if not exists, error if it
already exists

 The mode parameter specifies the permissions to be placed on
the new semaphore
▪ Both read and write permission should be granted to each process that will
access the semaphore
 The value parameter specifies the initial value for the new
semaphore

The sem_post function

#include <semaphore.h>
int sem_post(sem_t *sem);

 Increases the semaphore value by one unit
 sem is the semaphore’s address returned by sem_open()
 Returns:
▪ 0, on success
▪ -1, on failure

The sem_wait function

#include <semaphore.h>
int sem_wait(sem_t *sem);

 Attempts to decrement the semaphore value by one unit
▪ If the semaphore value is greater than zero, decrements immediately
▪ Otherwise, blocks until the decrement becomes possible
 sem is the semaphore’s address returned by sem_open()
 Returns:
▪ 0, on success
▪ -1, on failure

sem_trywait and sem_timedwait

int sem_trywait(sem_t *sem);
int sem_timedwait(sem_t *sem, const struct timespec *timeout);

 The sem_trywait() function is similar to sem_wait() but
does not block
▪ If the semaphore value is greater than zero, decrements immediately.
▪ Otherwise, returns an error (-1)
 The sem_timedwait() function is similar to sem_wait()
but blocks only until a specified timeout
▪ If the decrement succeeds before the timeout, it proceeds normally
▪ If the timeout expires before the semaphore becomes available, it returns
an error (-1)

The sem_getvalue function

#include <semaphore.h>
int sem_getvalue(sem_t * sem, int * sval);

 Gets the current semaphore value
▪ Note: The value of the semaphore may already have changed by the time
sem_getvalue() returns
 sem is the semaphore’s address returned by sem_open()
 sval is the address where the value should be returned
 Returns:
▪ 0, on success
▪ -1, on failure

The sem_unlink function

#include <semaphore.h>
int sem_unlink(const char* name);

 Marks the semaphore for removal
▪ The semaphore is physically deleted once all processes that have it open
(i.e., are actively using it) close their connections
 name is the semaphore’s name (not the address)
 Returns:
▪ 0, on success
▪ -1, on failure


## Example – mutual exclusion

int main(){
 int *shm_counter = NULL; /* shared mem */
 sem_t *sem;
 /* create, open and map shared memory */
 ...
 /* create semaphore with initial value of 1 */
 if((sem = sem_open(“/sem_excl”, O_CREAT|O_EXCL, 0644, 1)) ==
SEM_FAILED){
 perror(“sem_open”);
 exit(1);
 }
 *shm_counter = 0; /* initial counter’s value */
 pid = fork();
 if (pid == 0){
 sem_wait(sem);
 for (i=0; i<500000; i++) (*shm_counter)++;
 sem_post(sem);
 exit(0);
 }else{
 sem_wait(sem);
 for (i=0; i<500000; i++) (*shm_counter)++;
 sem_post(sem);
 }
 wait(NULL);
 sem_unlink(“/sem_excl”);
 printf(“Counter: %d\n", *shm_counter);
 return 0;
}

## TP10 ##

Recall from last class
 sem_open()
▪ Create/open a semaphore
 sem_post()
▪ Increment (up) the semaphore’s value
 sem_wait(), sem_trywait(), sem_timedwait()
▪ Try to decrement (down) the semaphore’s value
 sem_getvalue()
▪ Get the current semaphore’s value

Beyond simple mutual exclusion
 Event signaling
▪ Using semaphores to signal the occurrence of events
 Check-Then-Act problem
▪ Synchronizing actions based on conditions
 Synchronization barrier
▪ Coordinating multiple processes to reach a specific point before continuing

Barriers
 All processes must complete the code prior to the barrier
before any can proceed


Example – barrier
 Use multiple child processes to calculate partial sums of
random numbers stored in separate arrays and update a
shared total sum
 Each child:
▪ Fills one unique array with 1000 random numbers
▪ Child 1 fills v1[]; Child 2 fills v2[]; Child 3 fills v3[]
▪ Calculates the partial sum (∑ v1[j]+v2[j]+v3[j]) of 1/3 of the arrays
▪ Sums the computed partial to a total sum in shared memory
 Note that all children need to fill in the respective array before
computing their partial sum

Implementing a barrier

/* semaphore to guarantee mutual exclusion to a counter in shared memory of processes
waiting at the barrier */
sem_nproc = sem_open(”/nproc", O_CREAT|O_EXCL, 0644, 1);
/* semaphore to have processes blocked at the barrier */
sem_barrier = sem_open(”/barrier", O_CREAT|O_EXCL, 0644, 0);
/* shared data */
typedef struct{
 int v1[N], v2[N], v3[N];
 int total_sum;
 int nproc_at_barrier;
}shared_data_t;

/* increases counter of processes waiting at the barrier */
sem_wait(sem_nproc);
shm->nproc_at_barrier++;
/* checks if all processes have reached the barrier */
if(shm->nproc_at_barrier == NPROC)
 sem_post(sem_barrier);
sem_post(sem_nproc);
/* waits in barrier */
sem_wait(sem_barrier);
/* allows next process to also continue */
sem_post(sem_barrier);
/* calculate partial sum */
…
