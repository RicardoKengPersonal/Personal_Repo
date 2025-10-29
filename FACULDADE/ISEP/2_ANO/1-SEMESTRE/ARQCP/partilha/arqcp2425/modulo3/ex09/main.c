#include <stdio.h>
#include "asm.h"

int main()
{
	int vec[]={1,2,3,4};
	int num = 4;
	int search = 3;
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("First occurence: %p\n",&vec[2]);
	
	int* res = vec_search(vec,num,search);
	
	printf("Address of first occurence: %p\n",res);
	return 0;
}
