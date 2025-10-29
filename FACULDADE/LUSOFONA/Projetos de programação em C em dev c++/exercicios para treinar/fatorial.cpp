#include <stdio.h>

int main ()

{
	int  i,num,fact=1;
	
	printf("Insira o numero: ");
	scanf("%d",&num);
	
	for (i=1;i<=num;i++)
	{
		fact = fact * i;
		
	}
	printf("Fatorial: %d",fact);
	return 0;
	
}
