#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
    pid_t pid;
    int i;

    for(int i = 0; i < 4; i++)
    {
        pid = fork();
    }

    printf("Concurrent Programming.\n");

    return 0;

}


// a) Sao criados 16 processos, na primeira iteracao do loop ( i = 0 ), é criado um filho, logo ha dois processos (um pai e um filho). Na segunda iteração, ambos os processos anteriores
// vao tambem ficar no ciclo, criando cada um deles um filho, logo passam a existir 4 processos e assim sucessivamente por iteracao, totalizando 16 processos.

// b) como ha 16 processos no fim do ciclo, ha 16 printfs.