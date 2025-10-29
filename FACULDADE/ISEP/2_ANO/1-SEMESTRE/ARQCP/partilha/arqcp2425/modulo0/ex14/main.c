#include <stdio.h>
#include "count_value.h"

int main()
{
	int vec[]={1,2,2,2,3,4,5,5,2};
	int value;
	int result;
	
	printf("Value to search the array:");
	scanf("%d",&value);
	
	result = count_value(vec,9,value);
	
	printf("\n%d appears %d times in the array.\n",value,result);
	
	return 0;
}
