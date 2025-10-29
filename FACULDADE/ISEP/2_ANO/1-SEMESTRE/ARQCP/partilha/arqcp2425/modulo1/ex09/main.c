#include <stdio.h>
#include "get_array_statistics.h"

int main()
{
	int vec[]={1,2,3,4};
	
	int min,max;
	float avg;
	
	get_array_statistics(vec,4,&min,&max,&avg);
	
	printf("Biggest value: %d\n",max);
	printf("Smallest value: %d\n",min);
	printf("Biggest value: %.2f\n",avg);
	
	return 0;
}
