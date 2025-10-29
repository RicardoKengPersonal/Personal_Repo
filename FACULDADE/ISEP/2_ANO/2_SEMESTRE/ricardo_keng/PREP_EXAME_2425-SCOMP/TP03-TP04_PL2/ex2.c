/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

#define _POSIX_C_SOURCE 200809L
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdio.h>
#include<stdlib.h>
#include<signal.h>
#include<string.h>

volatile sig_atomic_t time_to_go;

/* If multiple standard signals are pending for a process, the order
   in which the signals are delivered is unspecified. */
void handle_signal(int sig){

    if(sig == SIGUSR1)
        write(STDOUT_FILENO,"Child: received SIGUSR1\n",24);
        
    if(sig == SIGUSR2)
        write(STDOUT_FILENO,"Child: received SIGUSR2\n",24);

    if(sig == SIGINT){
        write(STDOUT_FILENO,"Child: received SIGINT\n",23);
        time_to_go = 1; 
    }
}

void handle_alrm(int sig){
    write(STDOUT_FILENO,"I should never execute because SIGALRM is blocked\n",51);
}

int main(){
    pid_t pid;
    struct sigaction act;
    int i;
    sigset_t pending;

    /* Zeroes the sigaction structure */
    memset(&act, 0, sizeof(struct sigaction));
  
    /* block all signals during signal handling */
    if(sigfillset(&act.sa_mask)!=0)
        perror("sigfillset");

    act.sa_handler = handle_signal;
    sigaction(SIGUSR1, &act, NULL);
    sigaction(SIGUSR2, &act, NULL);
    sigaction(SIGINT, &act, NULL);

    /* block SIGALRM */
    act.sa_handler = handle_alrm;
    sigemptyset(&act.sa_mask);
    sigaddset(&act.sa_mask,SIGALRM);
    sigprocmask(SIG_BLOCK, &act.sa_mask, 0);
    sigaction(SIGALRM, &act, NULL);


    time_to_go = 0;

    pid = fork();

    if(pid == 0){
        while(!time_to_go){
            printf("Child: waiting for signals...\n");
            sleep(1);
            sigpending(&pending);
            if(sigismember(&pending, SIGALRM))
                printf("Child: SIGALRM pending. Need to unblock to handle!\n");
        }

        printf("Child: ending...\n");       
        exit(0);
    }

    sleep(5);

    /* parent */
    printf("Parent: sending SIGALRM\n");
    kill(pid,SIGALRM);
 
    printf("Parent: sending 3 x SIGUSR1\n");
    for(i=0;i<3;i++)
        kill(pid,SIGUSR1);
 
    printf("Parent: sending SIGUSR2\n");    
    kill(pid,SIGUSR2);
 
    printf("Parent: sending SIGINT\n");
    kill(pid,SIGINT);
 
    wait(NULL);
    return 0;
}