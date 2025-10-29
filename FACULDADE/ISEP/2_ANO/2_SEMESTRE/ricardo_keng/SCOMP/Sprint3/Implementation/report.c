#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "asm.h"

void* report_thread_func(void* arg) {
    FILE *relatorio = fopen("relatorio_simulacao.txt", "w");
    if (!relatorio) {
        perror("Erro ao abrir ficheiro de relatório");
        pthread_exit(NULL);
    }

    fprintf(relatorio, "=== Relatório de Simulação ===\n");
    fprintf(relatorio, "Total de Drones: %d\n\n", num_drones);

    int eventos_processados = 0;

    while (1) {
        pthread_mutex_lock(&mutex_colisoes);
        while (eventos_processados == num_eventos_colisao && !fim_simulacao) {
            // Espera por nova colisão ou fim
            pthread_cond_wait(&cond_colisao, &mutex_colisoes);
        }

        // Processa novos eventos de colisão
        while (eventos_processados < num_eventos_colisao) {
            CollisionEvent e = eventos_colisao[eventos_processados++];
            fprintf(relatorio,
                    "[COLISÃO] Tick %d: Drone %d e Drone %d colidiram na posição (%d, %d, %d)\n",
                    e.tick, e.drone1_id, e.drone2_id, e.x, e.y, e.z);
        }
        pthread_mutex_unlock(&mutex_colisoes);
        // Verifica se a simulação acabou
        if (shm->tick >= num_ticks) break;
    }

    // Registar estado final dos drones
    fprintf(relatorio, "\n--- Estado Final dos Drones ---\n");
    int ativos = 0;
    for (int i = 1; i <= num_drones; i++) {
		Drone d = shm->drones[i];

		if (d.active) {
			fprintf(relatorio, "Drone %d: Ativo na posição (%d, %d, %d)\n", d.id, d.x, d.y, d.z);
			ativos++;
		} else {
			fprintf(relatorio, "Drone %d: Inativo na posição (%d, %d, %d)\n", d.id, d.x, d.y, d.z);
		}
	}

	// Validação final
	if (ativos == num_drones) {
		fprintf(relatorio, "\nResultado da Validação: PASS\n");
	} else {
		fprintf(relatorio, "\nResultado da Validação: FAIL\n");
	}
		
    fclose(relatorio);
    pthread_exit(NULL);
}


