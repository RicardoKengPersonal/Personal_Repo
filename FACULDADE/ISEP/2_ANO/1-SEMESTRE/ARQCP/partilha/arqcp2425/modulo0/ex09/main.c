#include <stdio.h>
#include "average.h"

int main()
{

	int v[4] = {1,2,3,4};
	int r = 0;
	
	r = average_array(v,4);
	printf("average = %d\n",r);
	
	return 0;
}
