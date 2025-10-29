/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

 #include<unistd.h>
 #include<sys/types.h>
 #include<sys/wait.h>
 #include<stdio.h>
 #include<stdlib.h>
 
 int main(){
     pid_t pid;
     int a,sum,status;
     int fd[2];
 
     if(pipe(fd) == -1){
         perror("pipe failed:");
         exit(1);
     }
 
     pid = fork();
 
     if(pid > 0){
         /*close unused read descriptor*/
         close(fd[0]);
 
         a = -1;
         printf("Child: enter a set of values. End with 0\n");
 
         while(a!=0){
             printf("a = ");
             scanf("%d",&a); 
             /* write value to pipe */
             write(fd[1],&a,sizeof(int));
         }
 
         /* close write descriptor */
         /* read in child returns 0 */
         close(fd[1]);
 
         wait(&status);
         if(WIFEXITED(status))
             printf("Parent: sum = %d\n",WEXITSTATUS(status));
     }
     else{
         /*close unused write descriptor*/
         close(fd[1]);
 
         sum = 0;
 
         /* read values from pipe */
         while(read(fd[0],&a,sizeof(int))!=0){
             sum += a;
         }
 
         /* close read descriptor */
         close(fd[0]);
         exit(sum);
     }
 
 }