#include <stdio.h>
#include "asm.h"

int main()
{
	char str[]="         Count           the Spaces!           .";
	int r = remove_spaces(str);
	printf("%d: %s\n",r,str);
	return 0;
}
