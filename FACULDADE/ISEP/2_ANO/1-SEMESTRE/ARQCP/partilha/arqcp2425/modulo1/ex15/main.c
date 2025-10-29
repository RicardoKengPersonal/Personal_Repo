#include <stdio.h>
#include "trim_string.h"

int main()
{
	char str[]="  the      nUmbEr    muSt Be SaVed    ";
	
	printf("Original string: %s\n",str);
	
	trim_string(str);
	
	printf("Trimmed string: %s\n",str);
	
	return 0;
} 
