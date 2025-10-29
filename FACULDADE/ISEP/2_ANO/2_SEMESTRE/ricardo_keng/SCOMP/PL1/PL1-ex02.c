#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>

int main()
{
    pid_t child1, child2; //declaracao das variaveis
    int status;

    child1 = fork();    //Primeiro filho

    if (child1 > 0)
    {
        printf("I am the father. PID : %d\n",getpid());

        child2 = fork();    //Segundo filho

        if(child2 > 0)
        {
            pid_t child2_exit = wait(&status);  //Variavel temporaria para nao reescrever o pid da child 2      // Funcao wait devolve o pid do processo que o chama

            if(WIFEXITED(status))
            {
                printf("Parent: child %d returned %d\n",child2_exit,WEXITSTATUS(status));
            }
            printf("Waiting for the second child to terminate...\n");

        } else
        {
            printf("I am the second child. PID: %d\n",getpid());
            exit(2);
        }

        /*Waiting for the first child to finish*/

        pid_t child1_exit = wait(&status);  //Variavel temporaria para nao reescrever o pid da child 1

        if(WIFEXITED(status))
        {
            printf("Parent: child %d returned %d\n",child1_exit,WEXITSTATUS(status));
        }
        printf("Waiting for the first child to terminate...\n");

    } else {
        printf("I am the first child. PID : %d\n",getpid());
        sleep(5);
        exit(1);
    }
    return 0;
}