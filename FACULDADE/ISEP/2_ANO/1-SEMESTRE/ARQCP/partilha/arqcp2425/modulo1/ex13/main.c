#include <stdio.h>
#include "sort_without_reps.h"

int main()
{
	int size = 9;
	short src[]={2,1,1,1,1,1,1,1,1};
	short dest[size];
	int result = 0;
	
	for(int i = 0; i < size; i++)
	{
		printf("src[%d] = %d\n",i,src[i]);
	}
	
	result = sort_without_reps(src,size,dest);
	
	printf("\n");
	
	for(int i = 0; i < size; i++)
	{
		printf("dest[%d] = %d\n",i,dest[i]);
	}
	
	printf("Numbers placed on dest: %d\n",result);
	
	return 0;
}
