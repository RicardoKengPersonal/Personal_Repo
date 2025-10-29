/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>
#include<semaphore.h>
#include<sys/stat.h>
#include<fcntl.h>


int main(){
    sem_t *semA,*semC;
    pid_t pids[4];
    int i;

    /* creates semA */
    if((semA = sem_open("/semA",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open semA");
        exit(1);
    }

    /* creates semC */
    if((semC = sem_open("/semC",O_CREAT,0644,0))==SEM_FAILED){
        perror("sem_open semB");
        exit(2);
    }

    for(i=0;i<4;i++){
        pids[i] = fork();

        if(pids[i] == 0){
            if(i==0){
                /* generates A */
                execlp("make","make","A",(char*)NULL);
            }

            if(i==1){
                /* generates C */
                execlp("make","make","C",(char*)NULL);
            }

            if(i==2){
                /* wait for parent sync */
                sem_wait(semA);
                /* generates B */
                execlp("make","make","B",(char*)NULL);
            }

            if(i==3){
                /* wait for parent sync */
                sem_wait(semC);
                /* generates D */
                execlp("make","make","D",(char*)NULL);
            }

            exit(i+1);
        }
    }

    /* parent*/

    /* wait for child 1 that generates A */
    waitpid(pids[0],NULL,0);
    /* signals end of A*/
    sem_post(semA);

    /* wait for child 2 that generates C */
    waitpid(pids[1],NULL,0);
    /* signals end of C*/
    sem_post(semC);

    /* waits for B and D*/
    for(i=0;i<2;i++){
        wait(NULL);
    }

    printf("Parent: all children ended. Generating prog...\n");

    /* removes sems */
    sem_unlink("semA");
    sem_unlink("semC");

    /* generates prog */
    execlp("make","make","prog",(char*)NULL);

    exit(3);
}