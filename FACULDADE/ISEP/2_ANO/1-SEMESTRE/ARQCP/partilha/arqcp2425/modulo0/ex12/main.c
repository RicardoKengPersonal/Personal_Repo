#include <stdio.h>
#include "integer_part.h"

int main()
{
	char x[]="123.456";
	
	int x_int = integer_part(x);
	int x_frac = fractional_part(x);
	
	printf("%s : \nInteger part: %d\n Fractional part: %d\n",x,x_int,x_frac);
	
	return 0;
}
