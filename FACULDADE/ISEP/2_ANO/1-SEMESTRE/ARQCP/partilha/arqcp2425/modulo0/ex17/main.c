#include <stdio.h>
#include "check_string.h"

int main()
{
	char str[]="aaa";
	int h = 0;
	int resultado = 0;
	
	printf("Hash to check:");
	scanf("%d",&h);
	
	resultado = check_string(str,h);
	
	printf("Result: %d\n",resultado);
	
	return 0;
	
}
