#include <stdio.h>
#include "asm.h"

int main() 
{
    char chars1[] = "um u";
    char chars2[10];

    str_copy_roman(chars1, chars2);

    printf("Original String: %s\n", chars1);
    printf("Altered String: %s\n", chars2);

    return 0;
}
