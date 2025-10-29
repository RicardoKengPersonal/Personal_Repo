#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>


int main()
{
    pid_t p,a;

    p = fork();

    if (p < 0)
    {
        perror("Error forking.\n");
        exit(1);
    }

    a = fork();

    if (a < 0)
    {
        perror("Error forking.\n");
        exit(1);
    }

    printf("Concurrent programming.\n");

    return 0;
}

// a) são criados 4 processos, 2^n processos por fork, no caso há dois forks , logo 2^2 processos são criados.
// b) "Concurrent programming" vai ser printado 4 vezes , uma por cada processo
// c) if (p < 0) e if (a < 0) são usados para verificar se houve erro ao criar os processos filhos. Se houver erro, o programa imprime uma mensagem de erro e termina com exit(1).