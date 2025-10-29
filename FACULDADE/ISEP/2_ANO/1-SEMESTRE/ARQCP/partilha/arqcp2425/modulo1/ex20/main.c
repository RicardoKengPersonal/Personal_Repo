#include <stdio.h>
#include "compress.h"

int main()
{
	int size = 4;
	int vec_ints[]={1,2,3,4};
	long vec_longs[size/2];
	
	for(int i = 0; i < size; i++)
	{
		printf("vec_ints[%d]=%d\n",i,vec_ints[i]);
	}
	
	compress(vec_ints,size,vec_longs);
	
	printf("\n");
	
	for(int i = 0; i < size/2; i++)
	{
		printf("vec_longs[%d]=%ld\n",i,vec_longs[i]);
	}
	
	return 0;
}
