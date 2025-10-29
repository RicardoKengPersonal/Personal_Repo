#include <stdio.h>
#include "asm.h"

short op1 = 0x1234, op2 = 0x5678;

int main()
{

    short result = exchangeBytes();

    printf("Result: 0x%04X\n", result);

	return 0;
}
