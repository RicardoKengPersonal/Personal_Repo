#include <stdio.h>
#include "asm.h"
#define SIZE 23

short array1[] = {8,7,6,5,4,4,4,4,4,3,6,7,4,21,45,7,4,3,5,123,1,2,3};
int num = SIZE;
short array2[SIZE];

int main()
{
	
	short ptrsrc[] = {8,7,6,5,4,4,4,4,4,3,6,7,4,21,45,7,4,3,5,123,1,2,3};
	int num = SIZE;
	short ptrdest[SIZE];
	
    int n_moves = sort_without_reps(ptrsrc, ptrdest, num);

    printf("\nNumber of Items Placed: %d\n", n_moves);
    
    printf("New Array: ");
    
    for(int i = 0; i < n_moves; i++)
    {
        printf("%d ", array2[i]);
    }
    printf("\n\n");

    return 0;
}
