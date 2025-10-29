#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <signal.h>
#include <time.h>
#include <math.h>

#define MAX_DRONES 10
#define MAX_TICKS 10
#define MAX_POSITION 10
#define PREDICTION_TICKS 3
#define COLLISION_LIMIT 3

/*To run the file do:
1. gcc -o US262_v4 US262_v4.c
2. ./US262_v4
*/

typedef struct {
    float x, y, z;  // Position with float for precision
    float speed;    // Speed for each drone
} Position; // Position: 3D point + speed of a drone at a tick.


typedef struct {
    float x, y, z;
} Vector3; // Vector3: 3D velocity vector.


typedef struct {
    int drone_id;
    int tick;
    Position pos;
} CollisionInfo; // keeps track of which drones collided at what tick and position.

CollisionInfo collision_list[MAX_TICKS * MAX_DRONES];
int collision_index = 0;

char drone_status[MAX_DRONES][64];

Position timeline[MAX_TICKS][MAX_DRONES]; // stores position data over time.
int pipe_fd[MAX_DRONES][2];         // for drones to send positions to main process.
pid_t drone_pids[MAX_DRONES];       // drone_pids: stores PIDs of drone child processes.
int collision_count = 0;            // Count of collisions
sig_atomic_t stop_simulation = 0;

int control_fd[MAX_DRONES][2]; //for main process to signal drones when to send their position.

volatile sig_atomic_t terminate_flag = 0;

void handle_sigterm(int sig) {
    terminate_flag = 1; 
}                           // Drones use this flag to know they’ve been asked to terminate (instead of abruptly calling exit() inside the signal handler).
                            //Why SIGTERM?
                            //SIGTERM is a graceful termination signal.
                            //Unlike SIGKILL, it can be caught and handled.
                            //It's standard practice to allow clean shutdowns.                              

Vector3 wind = {0, 0, 0};

int positions_equal(Position a, Position b) {
    return (fabs(a.x - b.x) < 0.1) && (fabs(a.y - b.y) < 0.1) && (fabs(a.z - b.z) < 0.1);
}

// Function to send drone positions to the main process
void send_position(Position pos, int id) {
    ssize_t w = write(pipe_fd[id][1], &pos, sizeof(Position));
    if (w == -1) {
        perror("[Drone] write failed");
    }
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
    return i;
}

// Simulate drone movement based on a script
void simulate_drone(int id) {
    signal(SIGTERM, handle_sigterm); // Register SIGTERM handler

    close(pipe_fd[id][0]); // Close read end of the pipe

    char filename[32];
    snprintf(filename, sizeof(filename), "drone%d.txt", id + 1);

    Position positions[MAX_TICKS];
    int num_positions = read_drone_script(filename, positions);

    if (num_positions < 0) {
        exit(1);
    }

    // Send the positions for each tick step-by-step
    for (int t = 0; t < num_positions; t++) {
       char buf;
       read(control_fd[id][0], &buf, 1); // Wait for control signal

        send_position(positions[t], id); // Send position to the main process
    }

    close(pipe_fd[id][1]);
    exit(0);
}

Vector3 compute_velocity(Position a, Position b, float delta_time) {
    Vector3 v = {
        .x = (b.x - a.x) / delta_time,
        .y = (b.y - a.y) / delta_time,
        .z = (b.z - a.z) / delta_time
    };
    return v;
}

Position predict_position(Position current, Vector3 velocity, int ticks_ahead) {
    Position predicted = {
        .x = current.x + velocity.x * ticks_ahead,
        .y = current.y + velocity.y * ticks_ahead,
        .z = current.z + velocity.z * ticks_ahead,
        .speed = current.speed
    };
    return predicted;
}

void check_predicted_collisions(int current_tick) {
    for (int i = 0; i < MAX_DRONES; i++) {
        for (int j = i + 1; j < MAX_DRONES; j++) {
            if (current_tick < 1) continue;

            if (drone_pids[i] == -1 || drone_pids[j] == -1) continue;

            Position curr_i = timeline[current_tick][i];
            Position prev_i = timeline[current_tick - 1][i];
            Vector3 vel_i = compute_velocity(prev_i, curr_i, 1.0);

            Position curr_j = timeline[current_tick][j];
            Position prev_j = timeline[current_tick - 1][j];
            Vector3 vel_j = compute_velocity(prev_j, curr_j, 1.0);

            for (int k = 1; k <= PREDICTION_TICKS; k++) {
                Position future_i = predict_position(curr_i, vel_i, k);
                Position future_j = predict_position(curr_j, vel_j, k);
                if (positions_equal(future_i, future_j)) {
                    printf("⚠PREDICTED COLLISION in %d ticks (tick %d) between Drone %d and Drone %d at (%.2f, %.2f, %.2f)\n", k, current_tick + k, i, j, future_i.x, future_i.y, future_i.z);
                }
            }
        }
    }
}

void handle_termination(int sig) {
    // mensagem de erro guardada em stderr, onde indica qual o PID do drone atual
    fprintf(stderr, "Drone %d terminated after collision.\n", getpid());
    // encerra o processo
    exit(0);
}

void handle_collision_signal(int sig, siginfo_t *info, void *context) {
    pid_t sender = info->si_pid;                // obtém o PID do drone que enviou o sinal (o que sofreu uma colisão)

    for (int i = 0; i < MAX_DRONES; i++) {      // Procura pelo drone que sofreu a colisão
        if (drone_pids[i] == sender) {
            kill(sender, SIGTERM);              // envio do sinal SIGTERM ao drone
            drone_pids[i] = -1;                 // marca o drone como inativo para isso o seu PID é subtituido por -1
            break;
        }
    }
}

// Main simulation logic
int main() {
    for (int i = 0; i < MAX_DRONES; i++) {
        strcpy(drone_status[i], "Completed");
    }

    // Lê vetor do vento do ficheiro wind.txt
    FILE *wind_file = fopen("wind.txt", "r");
    if (!wind_file) {
        perror("Error opening wind.txt");
        // Continua com vento (0,0,0)
    } else {
        if (fscanf(wind_file, "%f %f %f", &wind.x, &wind.y, &wind.z) == 3) {
            printf("[Main] Wind vector loaded: (%.2f, %.2f, %.2f)\n", wind.x, wind.y, wind.z);
        } else {
            fprintf(stderr, "Failed to read wind vector properly.\n");
        }
        fclose(wind_file);
    }

    struct sigaction sa;
    sa.sa_sigaction = handle_collision_signal;
    sa.sa_flags = SA_SIGINFO;                           // handle_collision_signal irá receber outras informações através do SA_SIGINFO
    sigemptyset(&sa.sa_mask);                           // iniciar a máscara como vazia
    sigaction(SIGUSR1, &sa, NULL);                      // handle_collision_signal irá ser executado quando o processo receber o sinal SIGUSR1
    signal(SIGTERM, SIG_IGN);

    printf("Starting Drone Simulation with %d drones for %d ticks...\n", MAX_DRONES, MAX_TICKS);
    fflush(stdout);

    for (int i = 0; i < MAX_DRONES; i++) {
        if (pipe(pipe_fd[i]) == -1) {
            perror("pipe failed");
            exit(1);
        }
        if (pipe(control_fd[i]) == -1) {
            perror("pipe failed (control)");
            exit(1);
        }

        pid_t pid = fork();
        if (pid == 0) {
            close(pipe_fd[i][0]);
            close(control_fd[i][1]); // Close write end of control pipe
            simulate_drone(i);
            close(pipe_fd[i][1]);
            close(control_fd[i][0]); // Close read end of control pipe
            exit(0);
        } else if (pid > 0) {
            drone_pids[i] = pid;
            close(pipe_fd[i][1]); // Parent doesn't write to the pipe
            close(control_fd[i][0]); // Close read end of control pipe
        } else {
            perror("fork failed");
            exit(1);
        }
    }

    int active_drones = MAX_DRONES;

   // Main process reads and tracks positions
   for (int t = 0; t < MAX_TICKS && !stop_simulation && active_drones > 0; t++) {
       // Signal all active drones to proceed with this tick
       for (int i = 0; i < MAX_DRONES; i++) {
           if (drone_pids[i] == -1) continue; // Drone já terminou
           ssize_t w = write(control_fd[i][1], "x", 1);
           if (w == -1) {
               perror("[Main] Failed to write control signal");
           }
       }

       printf("\n[Main] Tick %d\n", t);
       fflush(stdout);

       for (int i = 0; i < MAX_DRONES; i++) {
           if (drone_pids[i] == -1) continue;

           Position pos;
           ssize_t n = read(pipe_fd[i][0], &pos, sizeof(Position));
           if (n <= 0) {
               fprintf(stderr, "[Main] Failed to read from Drone %d at tick %d\n", i, t);
               continue;
           }

           pos.x += wind.x;
           pos.y += wind.y;
           pos.z += wind.z;

           timeline[t][i] = pos;

           for (int j = 0; j < i; j++) {
               if (drone_pids[j] == -1) continue;

               if (positions_equal(timeline[t][i], timeline[t][j])) {
                   printf("COLLISION at tick %d between Drone %d and Drone %d at (%.2f, %.2f, %.2f)\n",
                       t, i, j, pos.x, pos.y, pos.z);

                   // Atualizar status
                   strcpy(drone_status[i], "Terminated after collision");
                   strcpy(drone_status[j], "Terminated after collision");

                   // Armazenar colisão
                   collision_list[collision_index++] = (CollisionInfo){i, t, pos};
                   collision_list[collision_index++] = (CollisionInfo){j, t, pos};

                   // Enviar sinal SIGUSR1
                   kill(drone_pids[i], SIGUSR1);
                   kill(drone_pids[j], SIGUSR1);

                   // Fechar descritores de controlo
                   close(control_fd[i][1]);
                   close(control_fd[j][1]);

                   // Marcar como terminados
                   drone_pids[i] = -1;
                   drone_pids[j] = -1;

                   collision_count++;
                   if (collision_count >= COLLISION_LIMIT) {
                       printf("Maximum collisions reached. Will stop after this tick.\n");
                       stop_simulation = 1;
                   }
               }
           }

           printf("[Main] Tick %d ← Drone %d: (%.2f, %.2f, %.2f)\n", t, i, pos.x, pos.y, pos.z);
           fflush(stdout);
       }

       if (t > 0) {
           check_predicted_collisions(t);
       }

       if (stop_simulation) break;

      // sleep(1);
   }


    for (int i = 0; i < MAX_DRONES; i++) {
        if (drone_pids[i] > 0) {
            kill(drone_pids[i], SIGTERM);
        }
    }

    // Wait for drones to finish
    for (int i = 0; i < MAX_DRONES; i++) {
        if (drone_pids[i] > 0) {
            waitpid(drone_pids[i], NULL, 0);
        }
    }

    if (!stop_simulation)
        printf("\nSimulation complete :) .\n");

    FILE *report = fopen("simulation_report.txt", "w");
    if (!report) {
        perror("Failed to create simulation report");
        exit(1);
    }

    fprintf(report, "=== Simulation Report ===\n");
    fprintf(report, "Total Drones: %d\n\n", MAX_DRONES);

    fprintf(report, "Drone Statuses:\n");
    for (int i = 0; i < MAX_DRONES; i++) {
        fprintf(report, "  - Drone %d: %s\n", i, drone_status[i]);
    }

    fprintf(report, "\nCollisions:\n");
    if (collision_index == 0) {
        fprintf(report, "  None\n");
    } else {
        for (int i = 0; i < collision_index; i++) {
            CollisionInfo c = collision_list[i];
            fprintf(report, "  - Tick %d: Drone %d at (%.2f, %.2f, %.2f)\n",
                    c.tick, c.drone_id, c.pos.x, c.pos.y, c.pos.z);
        }
    }

    fprintf(report, "\nValidation: %s\n",
            collision_index == 0 ? "PASSED" : "FAILED");

    fclose(report);

    return 0;
}
