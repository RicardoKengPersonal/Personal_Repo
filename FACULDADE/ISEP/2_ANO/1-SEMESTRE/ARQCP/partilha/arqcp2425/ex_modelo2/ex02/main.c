#include <stdio.h>
#include "asm.h"

int main()
{
	int vec[]={1,2,2,2,3};
	int num = 5;
	
	int res = count_max(vec,num);
	
	for(int i = 0; i < num; i++)
	{
		printf("Vec[%d] = %d\n",i,vec[i]);
	}
	
	printf("%d\n",res);
	
	return 0;
}
