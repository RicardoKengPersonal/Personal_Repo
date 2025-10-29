#include <stdio.h>
#include "asm.h"

int main()
{
	int vec[]={-1,-3,-4,-2};
	int length = 4;
	int me = 0;
	
	for(int i = 0; i < length; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("\n\n");
	
	int res = median(vec,length,&me);
	
	for(int i = 0; i < length; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("\n\n");
	
	printf("Median: %d\n",me);
	printf("Result: %d\n",res);
	
	return 0;
}
