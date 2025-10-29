#include <stdio.h>
#include "asm.h"

int main(){
	short v1 = 1;
	int v2 = 2;
	
	long result = inc_and_cube(&v1, v2);
	printf("Result: %ld\n", result);
	return 0;
}
