#include <stdio.h>
#include "asm.h"

int main()
{
	int ptr[] = {1,0,1};
	int num = 3;
	int x = 1;

    int x_duplicated = exists(ptr, num, x);

    printf("is X duplicated? (1 = YES ; 0 = NO) = %d \n", x_duplicated);
    
    int res = vec_diff(ptr, num);

    printf("\nNumber of different elements = %d\n", res);

    return 0;
}
