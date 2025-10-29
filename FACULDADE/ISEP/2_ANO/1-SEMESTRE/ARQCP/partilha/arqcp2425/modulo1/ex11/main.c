#include <stdio.h>
#include "sum_odd.h"

int main()
{
	int vec[]={4,1,2,3,4};
	
	int result = sum_odd(vec);
	
	printf("Sum of odd elements: %d\n",result);
	
	return 0;
}
