#include <stdio.h>
#include "asm.h"

int main()
{
	int vec[]={1,50,52,53,2,4};
	int num = 6;
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	unsigned char changed_count = vec_zero(vec,num);
	printf("\n\n");
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("Number of changed elements: %d\n",changed_count);
	
	return 0;
}
