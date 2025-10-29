#include <stdio.h>
#include "asm.h"

int main()
{
	int result = 0;
	
	char ptr[]="abc   dea";
	
	printf("Original String: %s\n",ptr);
	
	result = encrypt(ptr);
	
	printf("\n\nEncrypted String: %s\nNumber of changed characters: %d\n",ptr,result);
	
	
	result = decrypt(ptr);
	
	printf("\n\nDecrypted string: %s\nNumber of changed characters: %d\n",ptr,result);
	
	return 0;
}
