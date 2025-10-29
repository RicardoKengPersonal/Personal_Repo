#include <stdio.h>
#include "asm.h"

int main()
{
	int ptr[]={1,2,2,2,3};
	int num = 5;
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,ptr[i]);
	}
	
	int res = count_max(ptr,num);
	
	printf("Number of elements that satisfy the condition: %d\n",res);
	
	return 0;
}
