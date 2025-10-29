/**
 * Author:    Luís Nogueira (lmn@isep.ipp.pt)
 * Created:   February 2025
 **/

 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h>
 #include <unistd.h>
 #include <signal.h>
 #include <sys/types.h>
 #include <sys/wait.h>
 
 
 void sigusr1_handler(int sig) {
     const char msg[] = "Parent received SIGUSR1 from child.\n";
     write(STDOUT_FILENO, msg, sizeof(msg) - 1);
 }
 
 int main() {
     pid_t child_pid;
     struct sigaction act;
 
     /* Zeroes the sigaction structure */
     memset(&act, 0, sizeof(struct sigaction));
 
     /* Set the signal handler function for SIGUSR1 */
     act.sa_handler = sigusr1_handler;
     act.sa_flags = SA_RESTART;
     sigaction(SIGUSR1, &act, NULL);
 
     child_pid = fork();
 
     if(child_pid == -1){
         perror("fork");
         exit(EXIT_FAILURE);
     }
 
     if(child_pid == 0){
         /* Child process */ 
         sleep(2);
         kill(getppid(), SIGUSR1); /* Send SIGUSR1 to parent */  
         while (1); 
     }else{
         /* Parent process */
         pause(); /* Wait for SIGUSR1 */  
         sleep(2); 
         kill(child_pid, SIGINT); /* Terminate child */
         waitpid(child_pid, NULL, 0); /* Wait for child to exit */ 
 
         printf("Parent: Child process terminated.\n");
     }
 
     return 0;
 }