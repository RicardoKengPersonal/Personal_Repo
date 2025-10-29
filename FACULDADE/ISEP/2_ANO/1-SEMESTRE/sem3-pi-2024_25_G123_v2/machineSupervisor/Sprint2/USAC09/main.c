#include <stdio.h>
#include "asm.h"

int main()
{
	int vec[]={1,4,6,2,5};
	int length = 5;
	char order = 1;
	
	for(int i = 0; i < length; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("\n\nSorted Array:\n\n");
	
	int res = sort_array(vec, length, order);
	
	for(int i = 0; i < length; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("(1 - sucessful; 0 - unsucessful)\nResult: %d\n",res);
	
	return 0;
}
