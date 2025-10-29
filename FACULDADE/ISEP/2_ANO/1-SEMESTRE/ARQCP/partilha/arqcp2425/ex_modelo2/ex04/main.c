#include <stdio.h>
#include "asm.h"

int main()
{
	char* str ="abcacbacab";
	char* token ="cab";
	char *s = find_result(str,token);
	printf("%s\n",s);
	return 0;
}
