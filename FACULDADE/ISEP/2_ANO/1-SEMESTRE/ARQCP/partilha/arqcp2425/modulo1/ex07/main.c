#include <stdio.h>
#include "capitalize2.h"

int main()
{
	char vec[]="StrinG Test";
	
	printf("Original String: %s\n",vec);
	
	capitalize2(vec);
	
	printf("Capitalized string: %s\n", vec);
    
    return 0;
}
