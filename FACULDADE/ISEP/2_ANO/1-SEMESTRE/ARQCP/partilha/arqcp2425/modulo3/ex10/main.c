#include <stdio.h>
#include "asm.h"

int main()
{
	char ptr1[]="ab   cde";
	char ptr2[]="fg    hijk";
	char ptr3[40];
	
	printf("String 1: %s\n",ptr1);
	printf("String 2: %s\n",ptr2);
	
	str_cat(ptr1,ptr2,ptr3);
	
	printf("String 3: %s\n",ptr3);
	
	return 0;
}
