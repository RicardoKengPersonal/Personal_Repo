#include <stdio.h>
#include "asm.h"

int main()
{
    char ptr1[] = {1, 2, 3, 4};
    char ptr2[] = {5, 6, 7, 8};
    int num = 4;
    
    printf("Before function:\n");
    
    for(int i = 0; i < num; i++)
    {
        printf("ptr1[%d] = %d\n", i, ptr1[i]);
    }
	
	printf("\n\n");
	
    for(int i = 0; i < num; i++)
    {
        printf("ptr2[%d] = %d\n", i, ptr2[i]);
    }
    
    printf("\n\nAfter the function:\n");
    
    swap(ptr1,ptr2,num);
    
	for(int i = 0; i < num; i++)
    {
        printf("ptr1[%d] = %d\n", i, ptr1[i]);
    }
    
    printf("\n\n");

    for(int i = 0; i < num; i++)
    {
        printf("ptr2[%d] = %d\n", i, ptr2[i]);
    }

    return 0;
}

