#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>

#define MAX_DRONES 5
#define MAX_TICKS 10

typedef struct {
    int x, y, z;
} Position;

Position timeline[MAX_TICKS][MAX_DRONES];
int pipe_fd[MAX_DRONES][2];
pid_t drone_pids[MAX_DRONES];

// Utility to compare positions
int positions_equal(Position a, Position b) {
    return a.x == b.x && a.y == b.y && a.z == b.z;
}

void simulate_drone(int id) {
    close(pipe_fd[id][0]); // Close read end

    Position pos = {id * 2, 0, 0}; // Unique start position

    for (int t = 0; t < MAX_TICKS; t++) {
        pos.x += 1;
        pos.y += 1;
        pos.z += 1;

        write(pipe_fd[id][1], &pos, sizeof(Position));

        printf("[Drone %d] Tick %d → Sent position (%d, %d, %d)\n", id, t, pos.x, pos.y, pos.z);
        fflush(stdout);

        sleep(1);
    }

    close(pipe_fd[id][1]);
    exit(0);
}

int main() {
    // Create pipes and fork drones
    for (int i = 0; i < MAX_DRONES; i++) {
        if (pipe(pipe_fd[i]) == -1) {
            perror("pipe failed");
            exit(1);
        }

        pid_t pid = fork();
        if (pid == 0) {
            simulate_drone(i); // Child process
        } else {
            drone_pids[i] = pid;
            close(pipe_fd[i][1]); // Parent doesn't write
        }
    }

    // Main process reads and tracks positions
    for (int t = 0; t < MAX_TICKS; t++) {
        for (int i = 0; i < MAX_DRONES; i++) {
            Position pos;
            ssize_t n = read(pipe_fd[i][0], &pos, sizeof(Position));
            if (n <= 0) {
                fprintf(stderr, "[Main] Failed to read from Drone %d at tick %d\n", i, t);
                continue;
            }

            timeline[t][i] = pos;

            printf("[Main] Tick %d ← Received from Drone %d: (%d, %d, %d)\n", t, i, pos.x, pos.y, pos.z);

            // Collision detection
            for (int j = 0; j < i; j++) {
                if (positions_equal(timeline[t][j], pos)) {
                    printf("COLLISION at tick %d between Drone %d and Drone %d at (%d,%d,%d)\n",
                           t, i, j, pos.x, pos.y, pos.z);
                }
            }
        }
    }

    // Close remaining pipes and wait for drones
    for (int i = 0; i < MAX_DRONES; i++) {
        close(pipe_fd[i][0]);
        waitpid(drone_pids[i], NULL, 0);
    }

    printf(" Simulation complete. No further collisions detected.\n");
    return 0;
}
