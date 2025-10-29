#include <stdio.h>
#include "fn2.h"
int main(void)
{
	int a = 0x01020304;
	printf("0x%08x\n",a);
	fn2(&a);
	printf("0x%08x\n",a);
	return 0;	
}
