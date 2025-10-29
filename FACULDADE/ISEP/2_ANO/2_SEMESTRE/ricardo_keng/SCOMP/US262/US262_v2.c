#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>
#include <math.h>
#include <errno.h>

#define MAX_DRONES 5
#define MAX_TICKS 10
#define COLLISION_THRESHOLD 1.0f  // Minimum distance to count as collision
#define MOVEMENT_SCRIPT_FILE "drone_movements.txt" // File where drone movements are stored

typedef struct {
    float x, y, z;
} Position;

typedef struct {
    int timestamp;
    int droneId;
    Position pos;
} DroneUpdate;

Position timeline[MAX_TICKS][MAX_DRONES];
int pipe_fd[MAX_DRONES][2];
pid_t drone_pids[MAX_DRONES];

// Utility to compute Euclidean distance
float distance(Position a, Position b) {
    return sqrtf((a.x - b.x) * (a.x - b.x) +
                 (a.y - b.y) * (a.y - b.y) +
                 (a.z - b.z) * (a.z - b.z));
}

// Simulate drone behavior: generate and send positions
void simulate_drone(int id) {
    close(pipe_fd[id][0]); // Close read end

    Position pos = {id * 5.0f, 0.0f, 0.0f}; // Unique start position

    for (int t = 0; t < MAX_TICKS; t++) {
        // Movement logic (example pattern)
        pos.x += 1.0f;
        pos.y += (id % 2 == 0) ? 0.5f : -0.5f;
        pos.z += 0.2f;

        DroneUpdate update = {t, id, pos};
        if (write(pipe_fd[id][1], &update, sizeof(update)) < 0) {
            perror("write failed");
        }

        printf("[Drone %d] Tick %d → Sent position (%.1f, %.1f, %.1f)\n", id, t, pos.x, pos.y, pos.z);
        fflush(stdout);
        sleep(1);
    }

    close(pipe_fd[id][1]);
    exit(0);
}

// Collision check for the current position
void check_collisions(int t, int currentDroneId, Position currentPos) {
    for (int i = 0; i < currentDroneId; i++) {
        Position other = timeline[t][i];
        float d = distance(currentPos, other);
        if (d < COLLISION_THRESHOLD) {
            printf("⚠️ COLLISION at tick %d between Drone %d and Drone %d at (%.1f, %.1f, %.1f)\n",
                   t, currentDroneId, i, currentPos.x, currentPos.y, currentPos.z);
        }
    }
}

// Handle signals to stop or resume drones
void signal_handler(int sig) {
    if (sig == SIGSTOP) {
        printf("Received SIGSTOP: Pausing simulation\n");
    } else if (sig == SIGCONT) {
        printf("Received SIGCONT: Resuming simulation\n");
    }
}

// Read movement commands from a file (e.g., "drone_movements.txt")
void read_movement_script() {
    FILE *file = fopen(MOVEMENT_SCRIPT_FILE, "r");
    if (!file) {
        perror("Failed to open movement script file");
        exit(1);
    }

    char line[256];
    while (fgets(line, sizeof(line), file)) {
        int droneId, tick;
        float x, y, z;
        if (sscanf(line, "%d %d %f %f %f", &droneId, &tick, &x, &y, &z) == 5) {
            printf("Movement Command: Drone %d, Tick %d, Position (%.1f, %.1f, %.1f)\n", droneId, tick, x, y, z);
            // Update the timeline with the movement from the file
            if (tick < MAX_TICKS && droneId < MAX_DRONES) {
                timeline[tick][droneId] = (Position){x, y, z};
            }
        }
    }

    fclose(file);
}

int main() {
    printf("🚀 Starting Drone Simulation with %d drones for %d ticks...\n\n", MAX_DRONES, MAX_TICKS);

    // Signal handling setup
    signal(SIGSTOP, signal_handler);
    signal(SIGCONT, signal_handler);

    // Create pipes and fork drones
    for (int i = 0; i < MAX_DRONES; i++) {
        if (pipe(pipe_fd[i]) == -1) {
            perror("pipe failed");
            exit(EXIT_FAILURE);
        }

        pid_t pid = fork();
        if (pid < 0) {
            perror("fork failed");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {
            simulate_drone(i);  // Child
        } else {
            drone_pids[i] = pid;
            close(pipe_fd[i][1]); // Parent doesn't write
        }
    }

    // Read movement commands from file
    read_movement_script();

    // Main controller loop
    for (int t = 0; t < MAX_TICKS; t++) {
        for (int i = 0; i < MAX_DRONES; i++) {
            DroneUpdate update;
            ssize_t n = read(pipe_fd[i][0], &update, sizeof(update));

            if (n <= 0) {
                fprintf(stderr, "[Main] Failed to read from Drone %d at tick %d (errno: %d)\n", i, t, errno);
                continue;
            }

            timeline[t][i] = update.pos;

            printf("[Main] Tick %d ← Drone %d at (%.1f, %.1f, %.1f)\n",
                   update.timestamp, update.droneId,
                   update.pos.x, update.pos.y, update.pos.z);

            check_collisions(update.timestamp, update.droneId, update.pos);
        }
    }

    // Cleanup
    for (int i = 0; i < MAX_DRONES; i++) {
        close(pipe_fd[i][0]);
        waitpid(drone_pids[i], NULL, 0);
    }

    printf("\n✅ Simulation complete.\n");
    return 0;
}
