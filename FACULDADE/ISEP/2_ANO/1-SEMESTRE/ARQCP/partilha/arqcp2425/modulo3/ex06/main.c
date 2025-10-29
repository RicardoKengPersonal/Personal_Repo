#include <stdio.h>
#include "asm.h"

int main()
{
	int result = 0;
	
	char ptr[]="abc   dea";
	
	printf("Original String: %s\n",ptr);
	
	result = encrypt(ptr);
	
	printf("Encrypted String: %s\n Number of changed characters: %d\n",ptr,result);
	
	return 0;
}
