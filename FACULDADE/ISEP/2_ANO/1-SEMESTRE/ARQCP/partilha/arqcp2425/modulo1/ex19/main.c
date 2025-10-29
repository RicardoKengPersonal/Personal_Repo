#include <stdio.h>
#include "swap.h"

int main()
{
	short first_array[]={1,2,3,4,5};
	short second_array[]={6,7,8,9,10};
	int n = 5;
	
	for(int i = 0; i < n; i++)
	{
		printf("First array [%d] = %d\n", i, first_array[i]);
	}
	
	for(int i = 0; i < n; i++)
	{
		printf("Second array [%d] = %d\n",i,second_array[i]);
	}
	
	printf("After swap:\n");
	
	swap(first_array, second_array, n);
	
	for(int i = 0; i < n; i++)
	{
		printf("First array [%d] = %d\n", i, first_array[i]);
	}
	
	for(int i = 0; i < n; i++)
	{
		printf("Second array [%d] = %d\n",i,second_array[i]);
	}
	
	return 0;
}
