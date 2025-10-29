#include <stdio.h>
#include "count_char.h"

int main()
{
	int c;
	
	printf("Character to search (ASCII CODE):");
	scanf("%d",&c);
	
	char str[]="aaabaad";
	
	int result = count_char(str,c);
	
	printf("The character '%c' appears %d times in %s\n",c,result,str);
	
	return 0;
}
