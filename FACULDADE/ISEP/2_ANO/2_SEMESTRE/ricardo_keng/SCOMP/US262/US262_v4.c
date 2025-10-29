#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <time.h>
#include <math.h>

#define MAX_DRONES 5
#define MAX_TICKS 10
#define MAX_POSITION 10


/*To run the file do:
1. gcc -o US262_v4 US262_v4.c
2. ./US262_v4
*/

typedef struct {
    float x, y, z; // Position with float for precision
    float speed;    // Speed for each drone
} Position;

Position timeline[MAX_TICKS][MAX_DRONES];
int pipe_fd[MAX_DRONES][2]; // For communication between drones and the main process
pid_t drone_pids[MAX_DRONES]; // Store the process IDs of drones

// For collision detection
int positions_equal(Position a, Position b) {
    return (fabs(a.x - b.x) < 0.1) && (fabs(a.y - b.y) < 0.1) && (fabs(a.z - b.z) < 0.1);
}

// Function to send drone positions to the main process
void send_position(Position pos, int id) {
    write(pipe_fd[id][1], &pos, sizeof(Position)); // Send to main process
}

// Read drone script (positions and speeds from a file)
int read_drone_script(const char *filename, Position *positions) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror("Error opening drone script file");
        return -1;
    }

    int i = 0;
    while (i < MAX_TICKS && fscanf(file, "%f %f %f %f", 
                                    &positions[i].x, &positions[i].y, 
                                    &positions[i].z, &positions[i].speed) == 4) {
        i++;
    }

    fclose(file);
    return i; // Number of positions read
}

// Simulate drone movement based on a script
void simulate_drone(int id) {
    char filename[32];
    snprintf(filename, sizeof(filename), "drone%d.txt", id + 1);

    Position positions[MAX_TICKS];
    int num_positions = read_drone_script(filename, positions);

    if (num_positions < 0) {
        exit(1); // Exit if there was an error reading the script
    }

    // Send the positions for each tick
    for (int t = 0; t < num_positions; t++) {
        send_position(positions[t], id); // Send position to the main process
        sleep(1); // Wait for the next tick (simulate time delay)
    }

    exit(0); // Exit the drone process after completing its movement script
}

// Main simulation logic
int main() {
    printf("🚀 Starting Drone Simulation with %d drones for %d ticks...\n", MAX_DRONES, MAX_TICKS);

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
            close(pipe_fd[i][1]); // Parent doesn't write to the pipe
        }
    }

    // Main process reads and tracks positions
    for (int t = 0; t < MAX_TICKS; t++) {
        printf("\n[Main] Tick %d\n", t);
        for (int i = 0; i < MAX_DRONES; i++) {
            Position pos;
            ssize_t n = read(pipe_fd[i][0], &pos, sizeof(Position));
            if (n <= 0) {
                fprintf(stderr, "[Main] Failed to read from Drone %d at tick %d\n", i, t);
                continue;
            }

            timeline[t][i] = pos;

            // Check for collisions with previous drones
            for (int j = 0; j < i; j++) {
                if (positions_equal(timeline[t][i], timeline[t][j])) {
                    printf("COLLISION at tick %d between Drone %d and Drone %d at (%.2f, %.2f, %.2f)\n",
                           t, i, j, pos.x, pos.y, pos.z);
                }
            }

            // Display received positions
            printf("[Main] Tick %d ← Received from Drone %d: (%.2f, %.2f, %.2f)\n", t, i, pos.x, pos.y, pos.z);
        }
    }

    // Wait for drones to finish
    for (int i = 0; i < MAX_DRONES; i++) {
        waitpid(drone_pids[i], NULL, 0);
    }

    printf("\nSimulation complete.\n");
    return 0;
}
