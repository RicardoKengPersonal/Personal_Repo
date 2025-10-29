#include <stdio.h>

int main ()
{
	int n,i,val;
	
	printf("Numero de elementos do array: ");
	scanf("%d",&n);
	
	int array[n];
	
	for (i=0; i < n; i++)
	
	{
		printf("elemento %d: ",i+1);
		scanf("%d", &array[i]);
	}
	
	for (i=0; i < n; i++)
	printf("\narray[%d]: %d ",i,array[i]);
	
}
