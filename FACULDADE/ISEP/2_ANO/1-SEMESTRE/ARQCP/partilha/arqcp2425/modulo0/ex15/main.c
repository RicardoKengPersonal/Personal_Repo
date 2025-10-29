#include <stdio.h>
#include "count_words.h"

int main()
{
	char str[]="Uma palavra";
	int result;
	
	result = count_words(str);
	
	printf("Number of words in (%s): %d\n",str,result);
	
	return 0;
}
