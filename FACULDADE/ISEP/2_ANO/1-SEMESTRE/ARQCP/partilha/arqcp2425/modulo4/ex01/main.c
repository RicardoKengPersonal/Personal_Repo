#include <stdio.h>
#include "asm.h"

int main(void){
	int x = 5;
	
	long result = d_square(x);
	printf("long = %ld\n", result);
	return 0;
}
