#include <stdio.h>
#include "asm.h"

int main()
{
	char ptr[]="5500555";
	
	int res = 0;
	
	res = five_count(ptr);
	
	printf("res = %d\n",res);
	
	return 0;
}
