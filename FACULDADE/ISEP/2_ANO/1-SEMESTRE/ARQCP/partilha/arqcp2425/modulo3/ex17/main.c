#include <stdio.h>
#include "asm.h"

int main()
{
	int ptr[]={1,5,1,3,7,9};
	int num = 6;
	
	for (int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	printf("\n\n\nSorted Array:\n\n\n");
	
	array_sort(ptr,num);
	
	for (int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	
	return 0;
}
