#include <stdio.h>

int main ()
{
	
	int size,i,max,min,array[size]; 
	
	printf("Tamanho do array:");
	scanf("%d",&size);
	
	printf("Elementos: ");
	
	for (i=0;i<size;i++)
	{
		printf("\nElemento %d: ",i);
		scanf("%d",&array[i]);
	}
	
	max = array[0];
	min = array[0];
	
	for (i=0;i<size;i++)
	{
		if( max < array[i])
		max = array[i];
		
		if ( min > array[i] )
		min = array[i];
			
	}
	
	printf("Maximo: %d",max);
	printf("\nminimo: %d",min);
	
}
