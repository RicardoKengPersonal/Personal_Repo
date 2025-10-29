#include <stdio.h>

int main ()
{
	int num,i, fact = 1;
	
	printf ("Insira o valor cujo queira saber o fatorial: ");
	scanf("%d", &num);


	for (i=1;i<=num;i++)
	{
		fact = fact * i;
	}
	printf("Fatorial do numero: %d ", fact);
	return 0;
}
