/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

 #include<unistd.h>
 #include<sys/types.h>
 #include<sys/wait.h>
 #include<stdio.h>
 #include<stdlib.h>
 
 #define N 10
 
 int main(){
     pid_t pids[N],p;
     int i,nsec,status;
 
     /* creates N child processes */
     for(i=0; i<N; i++){
         pids[i] = fork();
 
         /* child */
         if(pids[i] == 0){
             nsec = (i+1)*2;
             sleep(nsec);
             printf("Child %d with PID %d ending...\n",i+1,getpid());
             exit(nsec);
         }
     }
 
     printf("Parent waiting for childs in reverse creation order...\n");
     
     /* waiting for child termination */
     for(i=N-1; i>=0; i--){
         p = waitpid(pids[i],&status,0);
         if(WIFEXITED(status))
             printf("Child %d with PID %d ended with value %d\n",i+1,p,WEXITSTATUS(status));
     }
 }