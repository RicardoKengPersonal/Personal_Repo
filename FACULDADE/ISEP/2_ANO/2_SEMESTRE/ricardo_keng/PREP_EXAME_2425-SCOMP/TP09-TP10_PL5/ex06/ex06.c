#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>

int main()
{
    // Ensure no leftover semaphores from previous runs
    sem_unlink("/sem_ex06_parent");
    sem_unlink("/sem_ex06_child");

    pid_t pid;
    sem_t *sem_parent, *sem_child;

    /*
     * Create two named semaphores:
     * - sem_parent: Initialized to 1 so the parent can run first.
     * - sem_child: Initialized to 0 so the child waits for the parent to signal it.
     *
     * This creates turn-based execution: parent prints, then child, then parent, etc.
     */

    sem_parent = sem_open("/sem_ex06_parent", O_CREAT | O_EXCL, 0644, 1);
    if (sem_parent == SEM_FAILED) {
        perror("sem_open parent");
        exit(EXIT_FAILURE);
    }

    sem_child = sem_open("/sem_ex06_child", O_CREAT | O_EXCL, 0644, 0);
    if (sem_child == SEM_FAILED) {
        perror("sem_open child");
        sem_unlink("/sem_ex06_parent");
        sem_close(sem_parent);
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == -1) {
        perror("Fork Failed");
        sem_unlink("/sem_ex06_parent");
        sem_unlink("/sem_ex06_child");
        sem_close(sem_parent);
        sem_close(sem_child);
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        // === CHILD PROCESS ===
        for (int i = 0; i < 10; i++) {
            // Wait until the parent gives permission (sem_child becomes > 0)
            sem_wait(sem_child);

            // Now it's the child's turn to print
            printf("I am the child (%d).\n", i);
            fflush(stdout);  // Ensure output is not buffered

            // Signal the parent it's their turn by incrementing sem_parent
            sem_post(sem_parent);
        }

        // Clean up
        sem_close(sem_parent);
        sem_close(sem_child);
        exit(0);
    } else {
        // === PARENT PROCESS ===
        for (int i = 0; i < 10; i++) {
            // Wait until it's the parent's turn (sem_parent becomes > 0)
            sem_wait(sem_parent);

            // Now it's the parent's turn to print
            printf("I am the father (%d)\n", i);
            fflush(stdout);

            // Signal the child it's their turn next
            sem_post(sem_child);
        }

        // Wait for child to finish
        wait(NULL);

        // Clean up
        sem_close(sem_parent);
        sem_close(sem_child);
        sem_unlink("/sem_ex06_parent");
        sem_unlink("/sem_ex06_child");
        exit(0);
    }
}

/*
🔄 Why sem_post() doesn't keep incrementing forever
Even though sem_post() increases the semaphore's value, it only happens after a sem_wait(), which first decrements it.

So over time, the semaphore value oscillates between 0 and 1 — like a baton being passed between the parent and child.

🧠 Think of the semaphore like a flag or gate
🧱 sem_wait(sem)
→ Blocks if the semaphore value is 0
→ If value > 0, it decrements it and proceeds

🚪 sem_post(sem)
→ Increments the semaphore value
→ If a process is waiting on it, it's woken up

🔁 In your case
Initially:

sem_parent = 1 → Parent starts

sem_child = 0 → Child blocked

Each cycle:

Parent:
sem_wait(sem_parent) → goes from 1 to 0 (runs)
sem_post(sem_child) → child gets 1 → child unblocked

Child:
sem_wait(sem_child) → goes from 1 to 0 (runs)
sem_post(sem_parent) → parent gets 1 → parent unblocked

🔁 Repeat for 10 iterations.

So:

They’re not just increasing the value every time

Each post is matched by a wait in the other process

📊 Visually:
Iteration	sem_parent	sem_child	Who runs?
0	1	0	Parent
after parent runs → post(child)	0	1	Child
after child runs → post(parent)	1	0	Parent
...	...	...	...

So the semaphores never keep increasing — they just toggle, acting as control signals between processes.

Let me know if you'd like a graphical simulation or animation to make it even clearer!
*/