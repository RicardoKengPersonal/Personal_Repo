#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

typedef struct {
    double min_dist;
    int max_coord;
    int max_drones;
    int max_steps;
    int max_positions;
    int max_line;
    int max_collisions;
} Config;

Config read_config(const char *config_file) {
    Config cfg;
    FILE *f = fopen(config_file, "r");
    if (!f) {
        perror("Erro ao abrir ficheiro de configuração");
        exit(1);
    }
    char line[128];
    int count = 0;

    while (fgets(line, sizeof(line), f)) {
        // Ignora linhas que começam por '#' ou '//'
        char *trim = line;
        while (*trim == ' ' || *trim == '\t') trim++; // ignora espaços iniciais
        if (*trim == '#' || (trim[0] == '/' && trim[1] == '/')) continue;
        // Remove newline
        char *nl = strchr(trim, '\n');
        if (nl) *nl = 0;
        // Lê os valores na ordem correta
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

void gerar_ficheiro_movimentos(const char *nome, int num_drones, int num_steps, int max_coord) {
    FILE *f = fopen(nome, "w");
    if (!f) { perror("fopen"); exit(1); }
    srand(time(NULL));

    // Gera intercalado por passo
    for (int step = 0; step < num_steps; ++step) {
        for (int id = 0; id < num_drones; ++id) {
            int x = rand() % max_coord + 1;
            int y = rand() % max_coord + 1;
            int z = rand() % max_coord + 1;
            fprintf(f, "%d %d %d %d\n", id, x, y, z);  // Escreve no ficheiro: drone_id x y z 
        }
    }
    fclose(f);
    printf("Gerado %d passos para %d drones em '%s'.\n", num_steps, num_drones, nome);
}

int main(int argc, char **argv) {
    if (argc != 5) {
        fprintf(stderr, "Uso: %s <ficheiro_movimentos> <num_drones> <num_passos> <ficheiro_config>\n", argv[0]);
        return 1;
    }
    const char *ficheiro_mov = argv[1];
    int N = atoi(argv[2]);
    int P = atoi(argv[3]);
    const char *ficheiro_cfg = argv[4];

    Config cfg = read_config(ficheiro_cfg);

    if (N < 1 || N > cfg.max_drones || P < 1 || P > cfg.max_steps || cfg.max_coord < 1) {
        fprintf(stderr, "Parâmetros inválidos.\n");
        return 1;
    }
    gerar_ficheiro_movimentos(ficheiro_mov, N, P, cfg.max_coord);
    return 0;
}