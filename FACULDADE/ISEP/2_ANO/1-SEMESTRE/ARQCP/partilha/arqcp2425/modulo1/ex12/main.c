#include <stdio.h>
#include "array_sort.h"

int main()
{
	int size = 4;
	
	int vec[]={3,1,5,2};
	
	for(int i = 0; i < size; i++)
	{
		printf("vec[%d] = %d",i,vec[i]);
		printf("\n");
	}
	
	printf("\nSorted array:\n");
	
	array_sort(vec,4);
	
	for(int i = 0; i < size; i++)
	{
		printf("vec[%d] = %d",i,vec[i]);
		printf("\n");
	}

	return 0;
}
