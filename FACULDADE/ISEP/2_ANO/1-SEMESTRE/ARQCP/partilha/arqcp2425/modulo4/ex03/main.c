#include <stdio.h>
#include "asm.h"

int main(){
	short a = 1;
	short b = 2;
	short c = 3;
	
	short result = greatest(a,b,c);
	printf("Result: %hd\n", result);
	return 0;
}
