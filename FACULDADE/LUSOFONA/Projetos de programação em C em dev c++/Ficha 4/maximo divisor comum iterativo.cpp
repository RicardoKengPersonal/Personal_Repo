#include <stdio.h>
#include <stdlib.h>

int main()
{
	int num1,num2,i,z;
	
	printf("Inserir primeiro numero:");
	scanf("%d", &num1);
	printf("Inserir segundo numero:");
	scanf("%d", &num2);
	
	
	for ( i = 1; i <= num1 && i<=num2; i++ )
	{
		if( num1 % i == 0 && num2 % i == 0)
		{
			// da todos os divisores comuns
			printf ("%d\n", i);
			// vai apontar qual o maior em comum
			z = i;
			
		}
	}
	printf("Maximo divisor comum: %d",z);
}
