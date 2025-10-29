#include <stdio.h>
#include "asm.h"

short op1 = 1, op2 = 2;

int main()
{
	printf("Result: %d\n",verify_flags());
	
	return 0;
}
