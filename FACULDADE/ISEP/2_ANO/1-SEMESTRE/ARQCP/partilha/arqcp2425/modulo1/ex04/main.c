#include <stdio.h>
#include "capitalize.h"


int main()
{
    char test_string[] = "Hello World!";
    
    printf("Original string: %s\n", test_string);
    
    capitalize(test_string);

    printf("Capitalized string: %s\n", test_string);
    
    return 0;
}
