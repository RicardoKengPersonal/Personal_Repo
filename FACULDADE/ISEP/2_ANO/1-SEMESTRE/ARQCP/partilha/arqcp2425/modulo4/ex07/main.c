#include <stdio.h>
#include "asm.h"

int main(){
	short vec[] = {1,2,3};
	int n = 3;
	
	int result = count_even(vec, n);
	printf("Result: %d\n", result);
	return 0;
}
