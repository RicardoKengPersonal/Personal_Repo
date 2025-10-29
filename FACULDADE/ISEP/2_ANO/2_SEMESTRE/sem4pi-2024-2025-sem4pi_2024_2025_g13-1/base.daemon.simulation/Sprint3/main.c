#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <string.h>
#include <pthread.h>
#include "asm.h"

void simulação_drone(int drone_id);
void* collision_thread_func(void* arg);
void* report_thread_func(void* arg);
void* environment_thread_func(void* arg);
void gerar_ficheiros_drones();


int num_drones = 0;
int num_ticks = 0;
shared_memory *shm=NULL;
sem_t *sem_excl= NULL;
sem_t *sem_barrier_in=NULL;
sem_t *sem_barrier_out=NULL;

pthread_mutex_t mutex_colisoes;
pthread_cond_t cond_colisao;

CollisionEvent eventos_colisao[MAX_COLISOES];
int num_eventos_colisao = 0;
int fim_simulacao = 0;
char caminho_base[128]="";


void menu_escolha_entrada() {
    int escolha;

    printf("Deseja utilizar ficheiros existentes ou criar novos?\n");
    printf("1 - Utilizar ficheiros existentes\n");
    printf("2 - Criar novos ficheiros\n");
    printf("Escolha (1 ou 2): ");
    fflush(stdout);
    scanf("%d", &escolha);

    if (escolha == 1) {
        int tipo;
        printf("Escolha o conjunto de ficheiros:\n");
        printf("1 - Ficheiros COM colisões\n");
        printf("2 - Ficheiros SEM colisões\n");
        printf("Escolha (1 ou 2): ");
        fflush(stdout);
        scanf("%d", &tipo);
        num_drones=5;
        num_ticks=5;

        if (tipo == 1) {
            strcpy(caminho_base, "colisoes/");
        } else {
            strcpy(caminho_base, "sem_colisoes/");
        }

    } else {
        gerar_ficheiros_drones();
        strcpy(caminho_base, "ficheiros_gerados/");
    }
}


int main(){
	
	menu_escolha_entrada();
	
	shm_unlink("/mp_projeto");
	//Declaração memória partilhada
	int fd;
	
    if((fd = shm_open("/mp_projeto",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
        perror("shmopen");
        exit(1);
    }

    if(ftruncate(fd,sizeof(shared_memory)) == -1){
        perror("ftruncate");
        exit(2);
    }

    if((shm = (shared_memory*)mmap(0,sizeof(shared_memory),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }
    
	shm->n_elementos=(num_drones+2); //usado na sincronização
    
	sem_unlink("/sem_excl");
    sem_unlink("/sem_barrier_in");
	sem_unlink("/sem_barrier_out");
	
    //Declaraçao dos semaforos que controlam a barreira
    if((sem_excl = sem_open("/sem_excl",O_CREAT,0644,1))==SEM_FAILED){
        perror("sem_open sem_excl");
        exit(4);
    }
    if((sem_barrier_in  = sem_open("/sem_barrier_in", O_CREAT | O_EXCL, 0644, 0))==SEM_FAILED){
        perror("sem_open sem_barrier_in");
        exit(4);
    }
    if((sem_barrier_out = sem_open("/sem_barrier_out", O_CREAT | O_EXCL, 0644, 0))==SEM_FAILED){
        perror("sem_open sem_barrier_out");
        exit(4);
    }

	
	//Criaçao de processos filhos como drones
	for (int i = 0; i < num_drones; i++) {
		pid_t pid = fork();
		if (pid == 0) {
			simulação_drone((i+1));
            exit(0);
		} else if (pid < 0) {
			perror("Erro no fork");
			exit(6);
		}
	} 
	
	
	pthread_mutex_init(&mutex_colisoes,NULL);
	pthread_cond_init(&cond_colisao,NULL);
	
	//Declaração threads
    pthread_t collision_thread, report_thread,environment_thread;

	if (pthread_create(&collision_thread, NULL, collision_thread_func, NULL) != 0) {
		perror("Erro ao criar thread de colisão");
		exit(7);
	}

	if (pthread_create(&report_thread, NULL, report_thread_func, NULL) != 0) {
		perror("Erro ao criar thread de relatório");
		exit(8);
	}
	
	
	if (pthread_create(&environment_thread, NULL, environment_thread_func, NULL) != 0) {
		perror("Erro ao criar thread de environment");
		exit(8);
	}

	
	//espera pelos processos filhos
	for (int i = 0; i < num_drones; i++) {
		wait(NULL);  
	}
	

	//espera pelo fim das threads
	pthread_join(collision_thread, NULL);
	pthread_join(report_thread, NULL);
	pthread_join(environment_thread, NULL);
	pthread_mutex_destroy(&mutex_colisoes);
	pthread_cond_destroy(&cond_colisao);

	
	//Remover memória partilhada
	close(fd);
    munmap(shm,sizeof(shared_memory));
    shm_unlink("/mp_projeto");
    
    //Remover os semáforos
    sem_unlink("/sem_excl");
    sem_unlink("/sem_barrier_in");
	sem_unlink("/sem_barrier_out");
	
	return 0;
}

