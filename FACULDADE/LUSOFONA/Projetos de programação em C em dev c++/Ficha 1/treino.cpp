#include <stdio.h>


int main ()
{
	int num,i,fact=1;
	
	printf("numero para calcular o fatorial: ");
	scanf("%d",&num);
	
	for (i=1;i<=num;i++){
		fact=fact*i;
		
	}	
	printf("O fatorial de %d e : %d",num,fact);
	return 0;

}

