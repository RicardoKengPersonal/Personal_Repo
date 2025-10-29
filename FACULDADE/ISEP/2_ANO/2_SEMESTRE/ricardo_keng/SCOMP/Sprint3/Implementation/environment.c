#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "asm.h"

void* environment_thread_func(void* arg){
	
    FILE *f_vento = fopen("vento.txt", "r");
	if (!f_vento) {
		perror("Erro ao abrir ficheiro de vento");
		pthread_exit(NULL);
	}
	
	
	// Alteração da memória partilhada com o vento por tick
	int wx, wy, wz;
	for (int tick = 1; tick <= num_ticks; tick++) {
		if (fscanf(f_vento, "%d %d %d", &wx, &wy, &wz) != 3) {
			fprintf(stderr, "Erro a ler vento no tick %d\n", tick);
			break;
		}

		shm->vento_x = wx;
		shm->vento_y = wy;
		shm->vento_z = wz;

		printf("Tick %d: vento.x = %d, vento.y = %d, vento.z = %d\n", tick, wx, wy, wz);

		// Sincronização com barreiras
		barreira(); 
		barreira(); 
	}

	fclose(f_vento);
	barreira();
	pthread_exit(NULL);
}
