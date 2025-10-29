#include <stdio.h>
#include <unistd.h>
#include <errno.h>

int main(int argc, char *argv[])
{
    printf("Try to execute lls\n");
    execl("/bin/lls","lls",NULL);
    printf("execl returned! errno is [%d]\n",errno);
    perror("The error message is :");
    return 0;
}

/* 
Isto acontece porque o execl falhou, ou seja, se o execl nao tivesse falhado
o programa não executaria as linhas apos isso.

O execl falha porque ta "/bin/lls" e "lls", se tivesse apenas 
"/bin/ls" e "ls" o programa corria normalmente e nao correria as ultimas 
linhas, mas sim os comandos especificados no execl,
no caso listaria os programas guardados na pasta.
*/