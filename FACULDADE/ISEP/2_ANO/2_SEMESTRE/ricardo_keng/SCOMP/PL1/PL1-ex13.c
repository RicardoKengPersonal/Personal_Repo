/*
.....
for(i = 0; i < 3; i++)
{
    pid = fork();

    if (pid == 0)
    {
        pid = fork();
    }

    if(pid > 0)
    {
        execlp("scomp","scomp",NULL);
    }
}

*/
//Programa para testar (substituiu-se scomp por echo Hello para testar)
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    int i;
    pid_t pid;

    // Loop to run fork and execlp multiple times
    for (i = 0; i < 2; i++) {
        pid = fork(); // First fork
        
        if (pid == 0) {
            // In the child process
            pid = fork(); // Second fork inside child process
        }

        if (pid > 0) {
            // In the parent process
            printf("Parent process (pid: %d) executing echo Hello...\n", getpid());
            execlp("echo", "echo", "Hello", NULL); // Replace with echo Hello
            // If execlp fails (it shouldn't in the right case), this will print
            perror("execlp failed");
        }
    }

    return 0;
}


/*
a) Corre 6 vezes. 

1a iteracao -> faz 2 prints Hello, porque é o pai do primeiro fork
e o pai do segundo fork (esta dentro do filho)

2a iteracao -> faz mais 2 prints Hello, porque é o pai do primeiro fork
e o pai do segundo fork (esta dentro do filho)

3a iteracao -> faz mais 2 prints Hello, porque é o pai do primeiro fork
e o pai do segundo fork (esta dentro do filho)

Total: 6 vezes que o programa corre scomp.


NOTA: EXEC's terminam o processo no final da sua execucao
*/