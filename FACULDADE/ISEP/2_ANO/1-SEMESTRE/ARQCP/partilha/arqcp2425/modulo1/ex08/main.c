#include <stdio.h>
#include "sum_integer_bytes.h"

int main()
{
	unsigned int d = 0xAABBCCDD;
	
    int r = sum_integer_bytes(&d); //Call the function
    
    printf("Sum of bytes: %d\n", r); //Print the result
    
    return 0;
}
