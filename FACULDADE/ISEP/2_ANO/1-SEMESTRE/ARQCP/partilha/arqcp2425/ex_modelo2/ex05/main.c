#include <stdio.h>
#include "asm.h"

int main()
{
	long x = 0x1122334455667788;
	int result = fn(&x);
	printf("%d\n",result);
	
	return 0;
}
