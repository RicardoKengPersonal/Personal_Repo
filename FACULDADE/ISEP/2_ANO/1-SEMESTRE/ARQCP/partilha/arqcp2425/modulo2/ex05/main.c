#include "asm.h"
#include <stdio.h>

short  s1 = 0x1234;

int main(){
    printf("\nValor não alterado: %X\n", s1);
   s1 = swapBytes();
    printf("\nValor alterado: %X\n", s1);
    
    return 0;
}
