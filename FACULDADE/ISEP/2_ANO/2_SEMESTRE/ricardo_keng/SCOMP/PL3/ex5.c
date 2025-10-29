/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

 #include<unistd.h>
 #include<sys/types.h>
 #include<sys/wait.h>
 #include<stdio.h>
 #include<stdlib.h>
 #include<time.h>
 
 #define N 5
 #define SIZE 1000
 
 int main()
 {
     pid_t pids[N];
     int i, j, partial, sum, status;
     int vec1[SIZE], vec2[SIZE];
     int fd[2];
 
     srand(time(NULL));
 
     for(i=0;i<SIZE;i++)
     {
         vec1[i] = rand() % 50;
         vec2[i] = rand() % 100;
     }
 
     if(pipe(fd) == -1)
     {
         perror("pipe failed:");
         exit(1);
     }
 
     for(i=0;i<N;i++) //Create N processes, in this case 5
     {
         pids[i] = fork();
 
         if(pids[i] == 0)
         {
             /*close unused read descriptor*/
             close(fd[0]);
 
             partial = 0;
             for(j=(i*SIZE/N);j<(i+1)*SIZE/N;j++)
             {
                 partial += (vec1[j] + vec2[j]);
             }
 
             /* write partial sum in pipe */
             write(fd[1],&partial,sizeof(int));
 
             printf("Child %d: wrote partial sum %d in pipe\n",i+1,partial);
 
             /* close write descriptor */
             close(fd[1]);
             exit(0);
         }
     }
 
     /* close write descriptor */
     close(fd[1]);
 
     while(read(fd[0],&partial,sizeof(int))!=0)
     {
         printf("Parent: got %d from pipe\n",partial);
         sum += partial;
     }
 
     /* close read deescriptor */
     close(fd[0]);
     
     printf("Parent: global sum = %d\n",sum);
 
     for(i=0;i<N;i++)
     {
         wait(NULL);
     }
 
     exit(0);
 }