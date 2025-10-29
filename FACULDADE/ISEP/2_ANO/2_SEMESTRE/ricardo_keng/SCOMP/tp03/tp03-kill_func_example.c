#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t child;

    child = fork();

    if (child > 0)
    {
        printf("Hello child!\n");
        sleep(5);
        printf("Goodbye child!\n");
        kill(child, SIGUSR1);
        wait(NULL);
    } else
    {
        printf("Child is running in an infinite loop..\n");
        for(;;);
    }

    return 0;
}

// int kill(pid_t pid, int sig);
// Envia o sinal especificado por sig
// se pid = 0, sig é enviado para o grupo de processos do processo que chama
// se pid = -1 , sig é enviado aos processos aos quais o processo invocador tem permissao
// para enviar sinais
