#include <stdio.h>
#include <stdlib.h>


int main()
{
	int num1,num2,perimetro,area;
	
		perimetro = 0;
		area = 0,
	
	
	printf("Insira o valor da base: ");
	scanf("%d", &num1);
	printf("Insira o valor da altura ");
	scanf("%d", &num2);

	
	perimetro = 2*num2 + 2*num1;
	area = num1*num2 ;



	printf("Perimetro: %d ", perimetro);
	printf("\nArea: %d ",area  );
	
	return 0;
	
}
