#include <stdio.h>
#include <stdlib.h>


int main ()
{
	int n,i,soma;
	float media;
	
	printf("Numero de elementos do array: ");
	scanf("%d", &n );
	int vnum[n];
	soma = 0;
	for (i=1;i<=n;i++)
	{
		
		printf("Elemento %d do array: ",i );
		scanf("%d", &vnum[n]); 
	}
	for (i=1; i<=n; i++);
	{

		soma = vnum[n];
		soma = soma + vnum[n];
		media = soma / n;
	}
	
	
	printf("Soma dos elementos do array: %d",soma );
	printf("\nA media do array: %.2f ", media);
}

