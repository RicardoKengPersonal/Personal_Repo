// Implemente a função long sum2_n(int n) que retorna o quadrado da soma dos inteiros 1 a n ('n' é passado como parâmetro).

# include <stdio.h>
# include "asm.h"

int main() {
    int num;

    printf("Inserir n: ");
    scanf("%d", &num);

    long int resultado = sum2_n(num);

    printf("O quadrado da soma dos inteiros de 1 a %d é: %ld\n", num, resultado);

    return 0;
}
	
