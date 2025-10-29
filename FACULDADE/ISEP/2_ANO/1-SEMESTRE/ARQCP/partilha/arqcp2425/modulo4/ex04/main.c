// Implemente na função int sum_sub(int num1, int num2, int *ptrsub) que retorna a soma dos dois números, num1 e num2, e coloque a subtração dos dois (n1 − n2) na área de memória apontada por ptrsub

# include <stdio.h>
# include "asm.h"

int main()
{
	int num1, num2, subtracao;

	printf("Inserir N1: ");
		 scanf("%d", &num1);
	printf("Inserir N2: ");
		 scanf("%d", &num2);
	
	printf("Soma = %d\n", sum_sub(num1, num2, &subtracao));
	printf("Subtração = %d\n", subtracao);
	
	return 0;
}
