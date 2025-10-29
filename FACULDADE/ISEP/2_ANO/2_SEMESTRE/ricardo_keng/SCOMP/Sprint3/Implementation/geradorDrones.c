#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "asm.h"

void gerar_ficheiros_drones() {

    do {
        printf("Quantos drones deseja simular (Mínimo 5)? ");
        scanf("%d", &num_drones);
    } while (num_drones < 5 );

    do {
        printf("Quantos momentos de simulação ? ");
        scanf("%d", &num_ticks);
    } while (num_ticks < 1 );

    srand(time(NULL));

	//Criação dos ficheiros dos drones
    for (int i = 0; i < num_drones; i++) {
        char nome_ficheiro[64];
        snprintf(nome_ficheiro, sizeof(nome_ficheiro), "drone_%d.txt", (i+1));

        FILE *f = fopen(nome_ficheiro, "w");
        if (!f) {
            perror("Erro ao criar ficheiro");
            continue;
        }

        // Posição inicial entre 0 e 2
        int x = rand() % 2;
        int y = rand() % 2;
        int z = rand() % 2;

        fprintf(f, "%d %d %d\n", x, y, z);

        // Gerar movimentos aleatórios entre -1 e 1
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
    FILE *vento = fopen("vento.txt", "w");
    if (!vento) {
        perror("Erro ao criar ficheiro de vento");
        return;
    }

    for (int t = 0; t < num_ticks; t++) {
        int wx = (rand() % 3) - 1; // vento em X (-1, 0, 1)
        int wy = (rand() % 3) - 1; // vento em Y
        int wz = (rand() % 3) - 1; // vento em Z

        fprintf(vento, "%d %d %d\n", wx, wy, wz);
    }

    fclose(vento);
    printf("Ficheiro 'vento.txt' criado com sucesso.\n");
    printf("\nTodos os ficheiros foram gerados com sucesso.\n");
}
