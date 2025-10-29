/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   January 2024
 **/

#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>

#define N 10
#define SIZE 1000
#define STR_SIZE 80

/* global variables shared by threads */
int vec[SIZE];
int max[N];

/* function executed by each thread */
void* find_local_max(void *arg){
    int my_id = *(int*)arg;
    int start = my_id*SIZE/N;
    int end = (my_id+1)*SIZE/N;
    int i, local_max = vec[start];
    char str[STR_SIZE];

    for(i=start+1; i<end; i++){
        if(vec[i] > local_max)
            local_max = vec[i];
    }

    /* Avoiding the use of printf in Linux POSIX threads is recommended because printf is not inherently thread-safe */    
    snprintf(str,STR_SIZE,"Thread %d found local_max = %d\n",my_id+1,local_max);
    write(STDOUT_FILENO,str,strlen(str));

    max[my_id] = local_max;
    pthread_exit(NULL);
}


int main(){
    pthread_t ids[N];
    int i,args[N],gmax;

    /* fills vec[] */
    srand(time(NULL));
    for(i=0;i<SIZE;i++)
        vec[i] = rand() % 1000;

    /* creates N threads */
    for(i=0;i<N;i++){
        /* each thread receives a different index */
        args[i] = i;
        pthread_create(&ids[i],NULL,find_local_max,(void*)&args[i]);
    }

    /* wait for all threads to finish */
    for(i=0;i<N;i++)
        pthread_join(ids[i],NULL);

    /* computes global max */  
    gmax = max[0];
    for(i=1;i<N;i++){
        if(max[i] > gmax)
            gmax = max[i];
    }    

    printf("Thread main: global max is %d\n",gmax);

    exit(0);
}