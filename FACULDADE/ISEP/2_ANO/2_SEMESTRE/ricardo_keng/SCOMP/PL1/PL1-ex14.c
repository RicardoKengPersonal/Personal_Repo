/* 
.....
pid = fork();
pid = fork();

for(i = 0; i < 5; i++)
{
    pid = fork();
}

execlp("exam","exam",NULL);

if(pid == 0)
{
    break;
}

execlp("students","students","scomp",NULL);
......
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    int i;
    pid_t pid;

    pid = fork();
    pid = fork();

    // Loop to run fork and execlp multiple times
    for (i = 0; i < 5; i++) 
    {
        pid = fork();

        execlp("exam","exam",NULL);

        if (pid == 0) 
        {
            // In the child process
            break;
        }
    }

    execlp("students","students","scomp",NULL);

    return 0;
}

/*
a) 2 forks iniciais criam 4 processos. Dentro do for cada processo faz fork
logo:

1a iteracao: 8 processos, 8 vezes executado "exam" + 4 vezes executado "students"
uma por cada pai , os filhos fazem break e voltam ao inicio do loop.

Assim sucessivamente.
*/