#include <stdio.h>
#include "asm.h"


int main() 
{
	long ptr[] = {0x0000000000010000, 0x0000000000010000, 0x0000000000010000, 0x0000000000010000, 0x0000000000010000};

    int num = 5;

    for (int i = 0; i < 5; i++) 
    {
        printf("ptr[%d] = %ld\n",i, ptr[i]);
    }
    
    int result = sum_third_byte(ptr,num);
    
    printf("Result : %d\n",result);

    return 0;
}
