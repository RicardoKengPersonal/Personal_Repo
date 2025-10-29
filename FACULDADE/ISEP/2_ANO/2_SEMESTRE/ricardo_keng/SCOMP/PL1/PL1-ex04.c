#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

 #include<unistd.h>
 #include<sys/types.h>
 #include<sys/wait.h>
 #include<stdio.h>
 #include<stdlib.h>
 
 #define N 2
 
 int main(){
     pid_t pids[N],pid_end;
     int i,status;
 
     /* create N child processes */
     for(i=0;i<N;i++){
         pids[i] = fork();
 
         if(pids[i] == -1){
             perror("fork:");
             exit(1);
         }
 
         /* child */
         if(pids[i] == 0){
             if(i == 0){
                 sleep(5);
                 printf("Child %d has PID %d...\n",i+1,getpid());
             }
             else{
                 printf("Child %d has PID %d and PPID %d...\n",i+1,getpid(),getppid());
             }
             exit(i+1);
         }
     }
 
     /* parent */
     printf("Parent with PID %d: %d children created...\n",getpid(),N);
 
     for(i=0; i<N; i++){
         pid_end = wait(&status);
         if(WIFEXITED(status)){
             printf("Child with PID %d ended with value %d\n",pid_end,WEXITSTATUS(status));
         }  
     }
 
     return 0;
 }
// a) vao ser criados 8 processos (f < 3)

/*
b) Let's Track the Prints:
Iteration 1:

1 parent (P0) prints.

Total prints: 1

Iteration 2:

2 parents (P0, P1) print.

Total prints: 2

Cumulative prints: 1 + 2 = 3

Iteration 3:

4 parents (P0, P1, P2, P3) print.

Total prints: 4

Cumulative prints: 3 + 4 = 7

Iteration 4 (Implicit through overlapping processes):

5 parents (P0, P1, P2, P3, P4) print.

Total prints: 5

Cumulative prints: 7 + 5 = 12
*/


// d) trocar f < 3 para f < 2

