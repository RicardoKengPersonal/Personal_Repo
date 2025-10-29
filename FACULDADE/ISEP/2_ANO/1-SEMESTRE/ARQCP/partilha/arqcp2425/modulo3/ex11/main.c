#include <stdio.h>
#include "asm.h"

int main()
{
	int ptr[]={1,2,3,12,12,13,13,14,15};
	int num = 9;
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	int res = vec_greater12(ptr,num);
	
	printf("NUmber of elements greater than 12: %d\n",res);
	
	
	return 0;
}
