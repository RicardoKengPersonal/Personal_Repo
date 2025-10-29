#include <stdio.h>
#include "fake_hash.h"

int main()
{
	char str[]="aaa";
	int result = 0;
	
	result = fake_hash(str);
	
	printf("Sum of ASCII code of (%s): %d\n",str,result);
	
	return 0;
}
