#include <stdio.h>
#include "string_to_int.h"

int main()
{
	char str[] = "12300";
	
	int i = string_to_int(str);
	
	printf("Integer:%d\n", i);
	
	return 0;
	
}
