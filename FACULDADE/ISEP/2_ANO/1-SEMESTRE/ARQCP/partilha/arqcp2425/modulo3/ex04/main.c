#include <stdio.h>
#include "asm.h"



int main()
{
	short ptr[]={1,2,0,3,4};
	int num = 4;
	
	for (int i = 0; i < num; i++)
	{
		printf("ptr[%d]=%d\n",i,ptr[i]);
	}
	
	vec_add_three(ptr,num);
	
	printf("\nChanged array:\n");
	
	for(int i = 0; i < num; i++)
	{
		printf("ptr[%d]=%d\n",i,ptr[i]);
	}
	
	return 0;
}
