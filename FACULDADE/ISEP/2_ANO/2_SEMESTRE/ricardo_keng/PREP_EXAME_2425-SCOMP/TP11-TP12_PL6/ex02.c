/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>

#define N_T 10
#define N_VEC 1152
#define N_ELEMS_THREAD N_VEC/N_T
#define STR_SIZE 80


/* global variables shared by threads */
int vec[N_VEC];
int global_sum;
pthread_mutex_t mutex;

/* function executed by each thread */
void* sum(void *arg){
    int my_id = *(int*)arg;
    int start = my_id * N_VEC / N_T;
    int end = (my_id + 1) * N_VEC / N_T;
    int i, local_sum = 0;
    char str[STR_SIZE];

    for(i=start;i<end;i++){
        local_sum += vec[i];
    }

    /* Avoiding the use of printf in Linux POSIX threads is recommended because printf is not inherently thread-safe */    
    snprintf(str,STR_SIZE,"Thread %d computed local_sum = %d\n",my_id+1,local_sum);
    write(STDOUT_FILENO,str,strlen(str));

    /* critical section */
    pthread_mutex_lock(&mutex);
    global_sum += local_sum;
    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}


int main(){
    pthread_t ids[N_T];
    int i,args[N_T];
    int n_elems_thread = N_VEC / N_T;

    /* fill vec[] */
    srand(time(NULL));
    for(i=0;i<N_VEC;i++)
        vec[i] = rand() % 100;

    global_sum = 0;

    /* init mutex */
    pthread_mutex_init(&mutex,NULL);

    /* create N threads */
    for(i=0;i<N_T;i++){
        /* each thread receives a different index */
        args[i] = i;
        pthread_create(&ids[i],NULL,sum,(void*)&args[i]);
    }

    /* wait for all threads to finish */
    for(i=0;i<N_T;i++)
        pthread_join(ids[i],NULL);
        
    /* Add leftover elements */
    for(i=N_T*n_elems_thread;i<N_VEC;i++)
        global_sum += vec[i];

    printf("Thread main: global sum is %d\n",global_sum);

    /* removes the mutex */
    pthread_mutex_destroy(&mutex);

    exit(0);
}