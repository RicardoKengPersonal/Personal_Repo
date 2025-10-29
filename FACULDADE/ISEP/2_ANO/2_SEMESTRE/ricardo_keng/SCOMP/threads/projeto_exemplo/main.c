#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <sys/wait.h>
#include <time.h>
#include <math.h>

typedef struct {
    int drone_id, x, y, z;
} DronePosition;

typedef struct {
    double min_dist;
    int max_coord;
    int max_drones;
    int max_steps;
    int max_positions;
    int max_line;
    int max_collisions;
} Config;

typedef struct {
    int drone1, drone2;
    double distance;
    int step;
    DronePosition pos1, pos2;
} Collision;

// Variáveis globais 
pid_t *drone_pids;
int **pipe_data;
int **pipe_ctrl;
DronePosition **drone_positions;
int *position_counts;
int *drone_finished;
int drones_active;
int collision_count = 0;
Collision *collisions;
int collision_capacity = 100;

void handle_sigint(int sig) {
    exit(0);
}

Config read_config(const char *config_file) 
{
    Config cfg;
    FILE *f = fopen(config_file, "r");
    if (!f) {
        perror("Erro ao abrir ficheiro de configuração");
        exit(1);
    }
    char line[128];
    int count = 0;
    while (fgets(line, sizeof(line), f)) {
        char *trim = line;
        while (*trim == ' ' || *trim == '\t') trim++;
        if (*trim == '#' || (trim[0] == '/' && trim[1] == '/')) continue;
        char *nl = strchr(trim, '\n');
        if (nl) *nl = 0;
        switch (count) {
            case 0: cfg.min_dist = atof(trim); break;
            case 1: cfg.max_coord = atoi(trim); break;
            case 2: cfg.max_drones = atoi(trim); break;
            case 3: cfg.max_steps = atoi(trim); break;
            case 4: cfg.max_positions = atoi(trim); break;
            case 5: cfg.max_line = atoi(trim); break;
            case 6: cfg.max_collisions = atoi(trim); break;
        }
        count++;
        if (count >= 7) break;
    }
    fclose(f);
    if (count < 7) {
        fprintf(stderr, "Ficheiro de configuração incompleto!\n");
        exit(1);
    }
    return cfg;
}

int get_max_drone_id(const char *file, int max_line) {
    FILE *f = fopen(file, "r");
    if (!f) {
        perror("Erro ao abrir arquivo");
        exit(1);
    }
    int max_id = -1, id, x, y, z;
    char *line = malloc(max_line);
    while (fgets(line, max_line, f)) {
        if (sscanf(line, "%d %d %d %d", &id, &x, &y, &z) == 4 && id > max_id) {
            max_id = id;
        }
    }
    free(line);
    fclose(f);
    return max_id + 1;
}

int read_positions(const char *file, int drone_id, int max_line, int max_positions) {
    FILE *f = fopen(file, "r");
    if (!f) {
        perror("Erro ao abrir arquivo");
        exit(1);
    }
    char *line = malloc(max_line);
    int count = 0;
    while (fgets(line, max_line, f) && count < max_positions) {
        int id, x, y, z;
        if (sscanf(line, "%d %d %d %d", &id, &x, &y, &z) == 4 && id == drone_id) {
            drone_positions[drone_id][count++] = (DronePosition){id, x, y, z};
        }
    }
    free(line);
    fclose(f);
    return count;
}

void drone_process(int id, int fd_data_w, int fd_ctrl_r, const char *file, int max_line, int max_positions) 
{
    void handle_termination(int sig)
    {
        close(fd_data_w);
        close(fd_ctrl_r);
        exit(0);
    }

    signal(SIGTERM, handle_termination);

    int n = read_positions(file, id, max_line, max_positions);

    for (int i = 0; i < n; i++) {
        char ctrl;
        if (read(fd_ctrl_r, &ctrl, 1) <= 0) break;
        write(fd_data_w, &drone_positions[id][i], sizeof(DronePosition));
    }
    DronePosition end = {.drone_id = -1};
    write(fd_data_w, &end, sizeof(end));
    close(fd_data_w);
    close(fd_ctrl_r);
    exit(0);
}

void check_collisions(int num_drones, double min_dist, int step) {
    for (int i = 0; i < num_drones; i++) {
        for (int j = i + 1; j < num_drones; j++) {
            if (position_counts[i] == 0 || position_counts[j] == 0) continue;
            DronePosition a = drone_positions[i][position_counts[i] - 1];
            DronePosition b = drone_positions[j][position_counts[j] - 1];
            double d = sqrt(
                (a.x - b.x) * (a.x - b.x) +
                (a.y - b.y) * (a.y - b.y) +
                (a.z - b.z) * (a.z - b.z)
            );
            if (d <= min_dist) {
                printf("[COLISÃO] Drones %d e %d colidiram (distância %.2f <= %.2f)\n", i, j, d, min_dist);
                if (collision_count >= collision_capacity) {
                    collision_capacity *= 2;
                    collisions = realloc(collisions, collision_capacity * sizeof(Collision));
                }
                collisions[collision_count++] = (Collision){i, j, d, step, a, b};
            }
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc < 3) {
        printf("Uso: %s <arquivo_movimentos> <ficheiro_config>\n", argv[0]);
        return 1;
    }

    const char *file = argv[1];
    const char *config_file = argv[2];
    Config cfg = read_config(config_file);

    drone_pids = malloc(cfg.max_drones * sizeof(pid_t));
    pipe_data = malloc(cfg.max_drones * sizeof(int*));
    pipe_ctrl = malloc(cfg.max_drones * sizeof(int*));
    drone_positions = malloc(cfg.max_drones * sizeof(DronePosition*));
    position_counts = calloc(cfg.max_drones, sizeof(int));
    drone_finished = calloc(cfg.max_drones, sizeof(int));
    collisions = malloc(collision_capacity * sizeof(Collision));

    for (int i = 0; i < cfg.max_drones; i++) {
        pipe_data[i] = malloc(2 * sizeof(int));
        pipe_ctrl[i] = malloc(2 * sizeof(int));
        drone_positions[i] = malloc(cfg.max_positions * sizeof(DronePosition));
    }

    int num_drones = get_max_drone_id(file, cfg.max_line);
    if (num_drones <= 0 || num_drones > cfg.max_drones) {
        printf("Número de drones inválido.\n");
        return 1;
    }

    signal(SIGINT, handle_sigint);
    signal(SIGTERM, handle_sigint);
    drones_active = num_drones;

    for (int i = 0; i < num_drones; i++) {
        if (pipe(pipe_data[i]) == -1 || pipe(pipe_ctrl[i]) == -1) {
            perror("Erro ao criar pipes");
            exit(1);
        }
        pid_t pid = fork();
        if (pid == -1) {
            perror("Erro no fork");
            exit(1);
        }
        if (pid == 0) {
            close(pipe_data[i][0]);
            close(pipe_ctrl[i][1]);
            drone_process(i, pipe_data[i][1], pipe_ctrl[i][0], file, cfg.max_line, cfg.max_positions);
        } else {
            drone_pids[i] = pid;
            close(pipe_data[i][1]);
            close(pipe_ctrl[i][0]);
        }
    }

    DronePosition *current = malloc(cfg.max_drones * sizeof(DronePosition));
    int step = 0;

    while (drones_active > 0) {
        int stepFlag = 1;

        for (int i = 0; i < num_drones; i++) {
            if (kill(drone_pids[i], 0) == 0) {
                char c = 'C';
                if (drone_finished[i]) continue;
                write(pipe_ctrl[i][1], &c, 1);
            }

            int bytes = read(pipe_data[i][0], &current[i], sizeof(DronePosition));
            if (bytes > 0) {
                if (current[i].drone_id < 0) {
                    for (int j = 0; j < num_drones; j++) {
                        if (!drone_finished[j]) {
                            kill(drone_pids[j], SIGTERM);
                            drone_finished[j] = 1;
                        }
                    }
                    drones_active = 0;
                    break;
                } else {
                    if (stepFlag) {
                        printf("\n[Main] Passo %d:\n", ++step);
                        stepFlag = 0;
                    }
                    printf("Drone %d => (%d,%d,%d)\n", i,
                        current[i].x, current[i].y, current[i].z);
                    if (position_counts[i] < cfg.max_positions) {
                        drone_positions[i][position_counts[i]++] = current[i];
                    }
                }
            }
        }

        if (drones_active > 0) {
            check_collisions(num_drones, cfg.min_dist, step);

            if (collision_count >= cfg.max_collisions) {
                printf("[Main] Número máximo de colisões atingido (%d). Encerrando simulação.\n", cfg.max_collisions);
                for (int i = 0; i < num_drones; i++) {
                    if (!drone_finished[i]) {
                        kill(drone_pids[i], SIGTERM);
                        drone_finished[i] = 1;
                        drones_active--;
                    }
                }
                break;
            }
        }
        sleep(1);
    }

    FILE *report = fopen("simulation_report.txt", "w");
    if (!report) {
        perror("Erro ao criar ficheiro de relatório");
        return 1;
    }

    fprintf(report, "===== RELATÓRIO DE SIMULAÇÃO =====\n");
    fprintf(report, "Número total de drones: %d\n", num_drones);
    fprintf(report, "Estados de execução:\n");

    for (int i = 0; i < num_drones; i++) {
        fprintf(report, "  Drone %d: %s\n", i, drone_finished[i] ? "Terminado" : "Abortado");
    }

    fprintf(report, "\nTotal de colisões: %d\n", collision_count);
    if (collision_count > 0) {
        fprintf(report, "Detalhes das colisões:\n");
        for (int i = 0; i < collision_count; i++) {
            Collision c = collisions[i];
            fprintf(report, "  Passo %d - Drone %d (%d,%d,%d) e Drone %d (%d,%d,%d) - Distância: %.2f\n",
                    c.step, c.drone1, c.pos1.x, c.pos1.y, c.pos1.z,
                    c.drone2, c.pos2.x, c.pos2.y, c.pos2.z, c.distance);
        }
    }

    fprintf(report, "\nResultado da validação: %s\n",
            (collision_count < cfg.max_collisions) ? "APROVADO" : "REPROVADO");

    fclose(report);
    printf("Relatório de simulação gerado em 'simulation_report.txt'.\n");

    for (int i = 0; i < num_drones; i++) {
        kill(drone_pids[i], SIGTERM);
        waitpid(drone_pids[i], NULL, 0);
        close(pipe_data[i][0]);
        close(pipe_ctrl[i][1]);
        free(pipe_data[i]);
        free(pipe_ctrl[i]);
        free(drone_positions[i]);
    }

    free(drone_pids);
    free(pipe_data);
    free(pipe_ctrl);
    free(drone_positions);
    free(position_counts);
    free(drone_finished);
    free(current);
    free(collisions);

    printf("Simulação concluída.\n");
    return 0;
}