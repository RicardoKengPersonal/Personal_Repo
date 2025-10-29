#define _POSIX_C_SOURCE 200809L
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

#define NUM_ALUNOS 5  // Quantos alunos você quer processar

typedef struct {
    int number;
    float sprint_grades[3];
    float exam;
    float final_grade;
} student_grade;

// Arrays para controle de sincronização por aluno
pthread_mutex_t mutex[NUM_ALUNOS];
pthread_cond_t cond_filled[NUM_ALUNOS];
pthread_cond_t cond_final_pos[NUM_ALUNOS];
pthread_cond_t cond_final_neg[NUM_ALUNOS];

int data_ready[NUM_ALUNOS];      // flags para dados prontos
int final_grade_ready[NUM_ALUNOS];
int grade_result[NUM_ALUNOS];    // 1 se >=10, 0 se <10

int positive = 0;
int negative = 0;

student_grade grades[NUM_ALUNOS];

// Mutex para proteger done_flags e contagem
pthread_mutex_t done_flags_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t count_mutex = PTHREAD_MUTEX_INITIALIZER;

int done_flags[NUM_ALUNOS] = {0};

// Thread T1: gera notas aleatórias para um aluno
void* t1_func(void* arg) {
    int idx = *(int*)arg;
    free(arg);

    pthread_mutex_lock(&mutex[idx]);

    for (int i = 0; i < 3; i++) {
        grades[idx].sprint_grades[i] = rand() % 21;  // 0 a 20
    }
    grades[idx].exam = rand() % 21;

    data_ready[idx] = 1;

    pthread_cond_broadcast(&cond_filled[idx]); // avisa T2 e T3

    pthread_mutex_unlock(&mutex[idx]);

    pthread_exit(NULL);
}

// Threads T2 e T3: calculam a nota final de um aluno (somente uma delas fará o cálculo)
void* t2_t3_func(void* arg) {
    int idx = *(int*)arg;
    free(arg);

    pthread_mutex_lock(&mutex[idx]);

    while (!data_ready[idx])
        pthread_cond_wait(&cond_filled[idx], &mutex[idx]);

    pthread_mutex_lock(&done_flags_mutex);
    int done = done_flags[idx];
    if (!done) {
        done_flags[idx] = 1;
    }
    pthread_mutex_unlock(&done_flags_mutex);

    if (!done) {
        grades[idx].final_grade =
            0.40f * ((grades[idx].sprint_grades[0] + grades[idx].sprint_grades[1] + grades[idx].sprint_grades[2]) / 3.0f)
            + 0.60f * grades[idx].exam;

        if (grades[idx].final_grade >= 10.0f) {
            grade_result[idx] = 1;
            pthread_cond_signal(&cond_final_pos[idx]);  // sinaliza T4
        } else {
            grade_result[idx] = 0;
            pthread_cond_signal(&cond_final_neg[idx]);  // sinaliza T5
        }
        final_grade_ready[idx] = 1;
    }

    pthread_mutex_unlock(&mutex[idx]);
    pthread_exit(NULL);
}

// Thread T4: conta alunos com nota final positiva
void* t4_func(void* arg) {
    int idx = *(int*)arg;
    free(arg);

    pthread_mutex_lock(&mutex[idx]);

    while (!final_grade_ready[idx] || grade_result[idx] != 1)
        pthread_cond_wait(&cond_final_pos[idx], &mutex[idx]);

    pthread_mutex_unlock(&mutex[idx]);

    pthread_mutex_lock(&count_mutex);
    positive++;
    pthread_mutex_unlock(&count_mutex);

    pthread_exit(NULL);
}

// Thread T5: conta alunos com nota final negativa
void* t5_func(void* arg) {
    int idx = *(int*)arg;
    free(arg);

    pthread_mutex_lock(&mutex[idx]);

    while (!final_grade_ready[idx] || grade_result[idx] != 0)
        pthread_cond_wait(&cond_final_neg[idx], &mutex[idx]);

    pthread_mutex_unlock(&mutex[idx]);

    pthread_mutex_lock(&count_mutex);
    negative++;
    pthread_mutex_unlock(&count_mutex);

    pthread_exit(NULL);
}

int main() {
    srand(time(NULL));

    // Inicializa dados e sincronizadores
    for (int i = 0; i < NUM_ALUNOS; i++) {
        grades[i].number = i + 1;
        data_ready[i] = 0;
        final_grade_ready[i] = 0;
        grade_result[i] = -1;

        pthread_mutex_init(&mutex[i], NULL);
        pthread_cond_init(&cond_filled[i], NULL);
        pthread_cond_init(&cond_final_pos[i], NULL);
        pthread_cond_init(&cond_final_neg[i], NULL);
    }

    // Criar threads para cada aluno
    pthread_t t1[NUM_ALUNOS], t2[NUM_ALUNOS], t3[NUM_ALUNOS], t4[NUM_ALUNOS], t5[NUM_ALUNOS];

    for (int i = 0; i < NUM_ALUNOS; i++) {
        int *idx;

        idx = malloc(sizeof(int));
        *idx = i;
        pthread_create(&t1[i], NULL, t1_func, idx);

        idx = malloc(sizeof(int));
        *idx = i;
        pthread_create(&t2[i], NULL, t2_t3_func, idx);

        idx = malloc(sizeof(int));
        *idx = i;
        pthread_create(&t3[i], NULL, t2_t3_func, idx);

        idx = malloc(sizeof(int));
        *idx = i;
        pthread_create(&t4[i], NULL, t4_func, idx);

        idx = malloc(sizeof(int));
        *idx = i;
        pthread_create(&t5[i], NULL, t5_func, idx);
    }

    // Espera todas as threads terminarem
    for (int i = 0; i < NUM_ALUNOS; i++) {
        pthread_join(t1[i], NULL);
        pthread_join(t2[i], NULL);
        pthread_join(t3[i], NULL);
        pthread_join(t4[i], NULL);
        pthread_join(t5[i], NULL);
    }

    // Exibe resultados
    for (int i = 0; i < NUM_ALUNOS; i++) {
        printf("Aluno %d:\n", grades[i].number);
        printf("  Sprint 1: %.2f\n", grades[i].sprint_grades[0]);
        printf("  Sprint 2: %.2f\n", grades[i].sprint_grades[1]);
        printf("  Sprint 3: %.2f\n", grades[i].sprint_grades[2]);
        printf("  Exame: %.2f\n", grades[i].exam);
        printf("  Nota Final: %.2f\n\n", grades[i].final_grade);
    }

    printf("Total positivos: %d\n", positive);
    printf("Total negativos: %d\n", negative);

    // Destruir mutexes e condicionais
    for (int i = 0; i < NUM_ALUNOS; i++) {
        pthread_mutex_destroy(&mutex[i]);
        pthread_cond_destroy(&cond_filled[i]);
        pthread_cond_destroy(&cond_final_pos[i]);
        pthread_cond_destroy(&cond_final_neg[i]);
    }
    pthread_mutex_destroy(&done_flags_mutex);
    pthread_mutex_destroy(&count_mutex);

    return 0;
}
