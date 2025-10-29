#include <stdio.h>
#include "asm.h"


int main()
{
	int result = 0;
	
	int avg = 0;
	
	int ptr[]={1,2,2,3};
	
	short num = 4;
	
	for(int i = 0; i < num; i++)
	{
		printf("ptr[%d] = %d\n",i,ptr[i]);
	}
	
	result = vec_sum(ptr,num);
	
	printf("Sum: %d\n",result);
	
	avg = vec_avg(ptr,num);
	
	printf("Average: %d\n",avg);
	
	return 0;
}
