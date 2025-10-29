/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>

#define N_T 200
#define MAX 20000
#define STR_SIZE 80

/* global variables shared by threads */
int global_counter;
pthread_mutex_t mutex;
pthread_cond_t cond;
char signaled;

/* function executed by the printer thread */
void *printer(void *arg){
    char str[STR_SIZE];

    pthread_mutex_lock(&mutex);
    while(global_counter < MAX)
        pthread_cond_wait(&cond,&mutex);

    /* Avoiding the use of printf in Linux POSIX threads is recommended because printf is not inherently thread-safe */    
    snprintf(str,STR_SIZE,"Printer thread: global counter is %d\n",global_counter);
    write(STDOUT_FILENO,str,strlen(str));
    pthread_mutex_unlock(&mutex);
}

/* function executed by each worker threads */
void* worker(void *arg){
    int my_id = *(int*)arg;
    int i;
    char str[STR_SIZE];
    char finish = 0;

    while(!finish){
        /* critial secion */
        pthread_mutex_lock(&mutex);

        /* several threads (one at a time) can be here */
        /* and global max is now >= MAX */ 
        /* because they were held in mutex */ 
        if(global_counter < MAX){
            global_counter += rand() % 3 + 1;

            /* signal condition */
            if(global_counter >= MAX){
                /* Avoiding the use of printf in Linux POSIX threads is recommended because printf is not inherently thread-safe */    
                snprintf(str,STR_SIZE,"Thread %d: signal condition\n",my_id + 1);
                write(STDOUT_FILENO,str,strlen(str));
                pthread_cond_signal(&cond);
                signaled = 1;
            }
        }  

        if(signaled)
            finish = 1;

        pthread_mutex_unlock(&mutex);
    }

    pthread_exit(NULL);
}


int main(){
    pthread_t ids[N_T], printer_id;
    int i,args[N_T];

    global_counter = 0;
    signaled = 0;
    srand(time(NULL));

    /* init mutex */
    pthread_mutex_init(&mutex,NULL);

    /* init cond variable */
    pthread_cond_init(&cond,NULL);

    /* create the printer thread */
    pthread_create(&printer_id, NULL, printer, NULL);

    /* create N threads */
    for(i=0;i<N_T;i++){
        /* each thread receives a different index */
        args[i] = i;
        pthread_create(&ids[i],NULL,worker,(void*)&args[i]);
    }

    /* wait for all threads to finish */
    for(i=0;i<N_T;i++)
        pthread_join(ids[i],NULL);
        
    pthread_join(printer_id,NULL);

    /* removes the mutex and cond variable */
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond);


    exit(0);
}