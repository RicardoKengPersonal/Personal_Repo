#include <stdio.h>
#include "asm.h"

int main()
{
	int x = 0x01020304;
	char r = fn(&x);
	printf("%d\n",(int)r);
	return 0;
}
