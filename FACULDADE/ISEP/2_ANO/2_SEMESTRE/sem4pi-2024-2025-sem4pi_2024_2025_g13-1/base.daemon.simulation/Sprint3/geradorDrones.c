#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "asm.h"

void gerar_ficheiros_drones() {

    do {
        printf("Quantos drones deseja simular? ");
        scanf("%d", &num_drones);
    } while (num_drones < 1);

    do {
        printf("Quantos momentos de simulação? ");
        scanf("%d", &num_ticks);
    } while (num_ticks < 1);

    srand(time(NULL));

    for (int i = 0; i < num_drones; i++) {
        char nome_ficheiro[128];
        snprintf(nome_ficheiro, sizeof(nome_ficheiro), "%sdrone_%d.txt", PASTA_BASE, i + 1);

        FILE *f = fopen(nome_ficheiro, "w");
        if (!f) {
            perror("Erro ao criar ficheiro");
            continue;
        }

        int x = rand() % 2;
        int y = rand() % 2;
        int z = rand() % 2;
        fprintf(f, "%d %d %d\n", x, y, z);

        for (int j = 0; j < num_ticks; j++) {
            int dx = (rand() % 3) - 1;
            int dy = (rand() % 3) - 1;
            int dz = (rand() % 3) - 1;
            fprintf(f, "%d %d %d\n", dx, dy, dz);
        }

        fclose(f);
        printf("Ficheiro '%s' criado com sucesso.\n", nome_ficheiro);
    }

    // Criação do ficheiro de vento
    char nome_vento[128];
    snprintf(nome_vento, sizeof(nome_vento), "%svento.txt", PASTA_BASE);
    FILE *vento = fopen(nome_vento, "w");
    if (!vento) {
        perror("Erro ao criar ficheiro de vento");
        return;
    }

    for (int t = 0; t < num_ticks; t++) {
        int wx = (rand() % 3) - 1;
        int wy = (rand() % 3) - 1;
        int wz = (rand() % 3) - 1;
        fprintf(vento, "%d %d %d\n", wx, wy, wz);
    }

    fclose(vento);
    printf("Ficheiro '%s' criado com sucesso.\n", nome_vento);
}
