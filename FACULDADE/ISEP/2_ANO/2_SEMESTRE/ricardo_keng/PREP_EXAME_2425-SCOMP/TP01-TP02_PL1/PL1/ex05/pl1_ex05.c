#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{
    fork();
    printf("1.\n");
    fork();
    printf("2.\n");
    fork();
    printf("3.\n");
    return 0;
}

// a) 14 processos, 1º fork -> 2 processos, 2º fork() -> 4 processos, 3º fork() -> 8 processos, 2+4+8 = 14 