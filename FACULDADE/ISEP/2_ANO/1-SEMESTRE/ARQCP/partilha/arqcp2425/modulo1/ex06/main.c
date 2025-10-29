#include <stdio.h>
#include "sum_even.h"

int main()
{
	int vec[5]={1,2,3,4,5};
	int result = 0;
	
	for(int i = 0; i < 5; i++)
	{
		printf("Vec: %d\n",vec[i]);
	}
	
	result = sum_even(vec,5); // Call function
	
	printf("Sum of array elements: %d\n",result);
	
	return 0;
}
