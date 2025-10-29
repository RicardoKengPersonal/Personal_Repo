#include <stdio.h>


int main()
{
	int num,i;
	
	printf("Numero:");
	scanf("%d",&num);
	
	if ( num<2)
	printf("Numero nao primo.");
	else if (num ==2)
	printf ("Numero primo. ");
	else 
	{
		i=2;
		while ((num % i !=0) && (i <= num/2))
		i++;
		if (num%i!=0)
		printf ("\nnumero  e primo.");
		else 
		printf ("\nNumero nao e primo");
		
	}
	return 0;
	
}
