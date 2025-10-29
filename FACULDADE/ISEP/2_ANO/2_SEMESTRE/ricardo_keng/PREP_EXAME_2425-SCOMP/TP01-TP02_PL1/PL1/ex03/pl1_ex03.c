#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
    pid_t pid;
    int i;

    for(int i = 0; i < 4; i++)
    {
        pid = fork();
    }

    printf("Concurrent programming.\n");

    return 0;
}

// a) 16 processos são criados, 2^4 processos
// b) É printado 16 vezes

