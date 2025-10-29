#include <stdio.h>
#include "asm.h"

int main()
{	
	int ptr[]={1,2,3,-1,-2,-3,4};
	int num = 7;
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	keep_positives(ptr,num);
	
	printf("\n\n\n");
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	return 0;
}
