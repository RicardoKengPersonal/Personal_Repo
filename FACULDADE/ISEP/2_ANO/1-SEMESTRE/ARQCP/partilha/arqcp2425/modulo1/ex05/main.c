#include <stdio.h>
#include "copy_vec.h"

int main()
{
	int vec1[]={1,2,3,4};
	int vec2[4];
	
	
	for(int i = 0; i < 4; i++)
	{
		printf("Vec1: %d",vec1[i]);
		printf("\n");
	}
	
	copy_vec(vec1,4,vec2);
	
	printf("Copied array: \n");
	for(int j = 0; j < 4; j++)
	{
		printf("Vec2: %d",vec2[j]);
		printf("\n");
	}
	
	return 0;
}
