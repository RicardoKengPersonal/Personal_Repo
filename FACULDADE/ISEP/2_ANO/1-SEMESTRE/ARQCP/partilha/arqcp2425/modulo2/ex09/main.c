#include <stdio.h>
#include "asm.h"

int A = 1;
char B = 3;
short C = 10, D = 2;

int main()
{
	printf("Result: %ld\n",sum_and_subtract());
	
	return 0;
}
