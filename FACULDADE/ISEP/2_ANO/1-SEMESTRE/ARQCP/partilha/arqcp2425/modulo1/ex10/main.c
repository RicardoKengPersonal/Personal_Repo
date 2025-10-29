#include <stdio.h>
#include "where_is.h"

int main()
{
	char str[]="Looking for a Char";
	char target = 'C';
	
    char* result = where_is(str, target);

    if (result != NULL) 
    {
        printf("Character '%c' found at address: %p\n", target, result);
    } 
    else 
    {
        printf("Character '%c' not found in the string.\n", target);
    }

    return 0;
}
