#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include "asm.h"

void* collision_thread_func(void* arg) {
	char str[STR_SIZE];
	barreira();

	
    for (int tick = 1; tick <= num_ticks; tick++) {
		
        //  Espera que os drones terminarem o tick atual
        barreira();  
        
        snprintf(str, STR_SIZE, "Verificaçao colisao Tick:%d\n", tick);
        write(STDOUT_FILENO, str, strlen(str));

        // Verifica colisões após os drones atualizarem posição
        for (int i = 1; i <= num_drones; i++) {
			
            if (!shm->drones[i].active) continue;

            for (int j = i + 1; j <= num_drones; j++) {
                if (!shm->drones[j].active) continue;

                if (shm->drones[i].x == shm->drones[j].x &&
                    shm->drones[i].y == shm->drones[j].y &&
                    shm->drones[i].z == shm->drones[j].z) {

                    // Colisão encontrada
                    shm->drones[i].active = 0;
                    shm->drones[j].active = 0;

                    snprintf(str, STR_SIZE,
                        "[COLISÃO] Tick %d: Drone %d e Drone %d colidiram na posição (%d, %d, %d)\n",
                        tick, i, j, shm->drones[i].x, shm->drones[i].y, shm->drones[i].z);
                    write(STDOUT_FILENO, str, strlen(str));
                    pthread_mutex_lock(&mutex_colisoes);
					
					//Guarda a colisão em eventos_colisão
					if (num_eventos_colisao < MAX_COLISOES) {
						eventos_colisao[num_eventos_colisao].tick = tick;
						eventos_colisao[num_eventos_colisao].drone1_id = i;
						eventos_colisao[num_eventos_colisao].drone2_id = j;
						eventos_colisao[num_eventos_colisao].x = shm->drones[i].x;
						eventos_colisao[num_eventos_colisao].y = shm->drones[i].y;
						eventos_colisao[num_eventos_colisao].z = shm->drones[i].z;
						num_eventos_colisao++;

						pthread_cond_signal(&cond_colisao);  //Notifica a thread de relatório
					}

					pthread_mutex_unlock(&mutex_colisoes);

                }
            }
        }
        if(!(tick==num_ticks)){
			snprintf(str, STR_SIZE, "\nTICK %d ───────────────────────────────────────\n\n", tick + 1);
            write(STDOUT_FILENO, str, strlen(str));
		}
        barreira();

    }
    pthread_mutex_lock(&mutex_colisoes);
	fim_simulacao = 1;
	pthread_cond_signal(&cond_colisao); // Acorda a thread de relatório
	pthread_mutex_unlock(&mutex_colisoes);


    pthread_exit(NULL);
}


