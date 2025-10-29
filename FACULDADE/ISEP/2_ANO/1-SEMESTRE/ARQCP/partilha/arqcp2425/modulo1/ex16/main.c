#include <stdio.h>
#include "format_word.h"

int main()
{
	char str[]="     test  StrIng   ";
	
	printf("Original String: %s\n",str);
	
	format_word(str);
	
	printf("Formatted string: %s\n",str);
	
	return 0;
}
