#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <semaphore.h>
#include <fcntl.h> 
#include <unistd.h>
#include "asm.h"

void barreira() {
    // Entrada da barreira de drones + thread de colisão + thread environment
    sem_wait(sem_excl);
    shm->contador++;
    if (shm->contador == shm->n_elementos) {
		if(shm->tick==0){
			char str[STR_SIZE];
			snprintf(str, STR_SIZE, "\nTICK 1 ───────────────────────────────────────\n\n");
			write(STDOUT_FILENO, str, strlen(str));
		}
		
        // Último drone e thread chegou: liberta todos da entrada
        for (int i = 0; i < shm->n_elementos; i++) {
            sem_post(sem_barrier_in);
        }
    }
    sem_post(sem_excl);

	sem_wait(sem_barrier_in); 

    // Saída da barreira
    sem_wait(sem_excl);
    shm->contador--;
    
    if (shm->contador == 0) {
		
        // Último a sair: liberta todos da saída
        for (int i = 0; i < shm->n_elementos; i++) {
            sem_post(sem_barrier_out);
            
        }
    }
    sem_post(sem_excl);

    sem_wait(sem_barrier_out); 
}



void simulação_drone(int id) {
    
	// Lê ficheiro com a posição inicial
	char nome[128];
	snprintf(nome, sizeof(nome), "%sdrone_%d.txt", caminho_base, id);

	FILE *f = fopen(nome, "r");
	if (!f) {
		perror("Erro ao abrir ficheiro do drone");
		exit(1);
	}

	int x, y, z;
	fscanf(f, "%d %d %d", &x, &y, &z);
	

	// Guarda posição na memória partilhada
	shm->drones[id].id = id;
	shm->drones[id].x = x;
	shm->drones[id].y = y;
	shm->drones[id].z = z;
	shm->drones[id].active = 1;
	
	char str[520];
	snprintf(str, sizeof(str),
		"──────────────────────────────────────────────\n"
		"  [Drone %d] Início da simulação\n"
		"  Posição inicial: (%d, %d, %d)\n"
		"──────────────────────────────────────────────\n",
		id, x, y, z);
	write(STDOUT_FILENO, str, strlen(str));

	
	barreira();
	
	// Simulação por ticks
    for (int tick = 1; tick <= num_ticks; tick++) {
		if ( id==1) {
            shm->tick++;
        }
		
		if (shm->drones[id].active == 0) {
			barreira();
			barreira();
			continue;
		}
        int dx, dy, dz;
        if (fscanf(f, "%d %d %d", &dx, &dy, &dz) == 3) {
            x += dx + shm->vento_x;
			y += dy + shm->vento_y;
			z += dz + shm->vento_z;

			shm->drones[id].x = x;
			shm->drones[id].y = y;
			shm->drones[id].z = z;

			printf("[Drone %d] Tick %d: moveu (%+d, %+d, %+d) + vento (%+d, %+d, %+d) → nova posição (%d, %d, %d)\n",
				   id, tick, dx, dy, dz,
				   shm->vento_x, shm->vento_y, shm->vento_z,
				   x, y, z);

        } else {
            printf("[Drone %d] Tick %d: fim do ficheiro de movimentos.\n", id, tick);
        }
        // Barreira para sincronizar todos os drones antes do próximo tick
        barreira();
        //Thread deteta se existe colisões
        barreira(); 
    }
    fclose(f);
	
}
