#include <stdio.h>
#include "asm.h"

int main()
{
	int result = 0;
	int num = 4;
	int vec[]={1,2,3,4};
	
	result = test_even(2);
	
	printf("1 means even, 0 means odd.\n");
	printf("Result: %d\n\n",result);
	
	
	for(int i = 0; i < num; i++)
	{
		printf("vec[%d]= %d\n",i,vec[i]);
	}
	
	int sum = vec_sum_even(vec,num);
	
	printf("Sum of even numbers: %d\n",sum);
	
	return 0;
}
