#include <stdio.h>
#include "asm.h"

int main() 
{
    char chars1[] = "Uum uU";
    char chars2[10];

    str_copy_roman2(chars1, chars2);

    printf("Original String: %s\n", chars1);
    printf("Altered String: %s\n", chars2);

    return 0;
}
