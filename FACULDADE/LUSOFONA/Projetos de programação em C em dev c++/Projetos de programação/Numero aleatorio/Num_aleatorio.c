#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int num_aleatorio;
    srand(time(NULL)); // inicializa a semente do gerador de números aleatórios

    num_aleatorio = rand() % 100; // gera um número aleatório entre 0 e 99
    printf("O numero aleatorio gerado foi: %d\n", num_aleatorio);

    return 0;
}

