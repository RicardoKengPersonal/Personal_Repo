#include <stdio.h>
#include <stdlib.h>
#include "inc_nsets.h"
#include "populate.h"
#include "check.h"
#include <time.h>

#define ARRAY_SIZE 50

unsigned char arr[ARRAY_SIZE];

int main() 
{
    srand(time(NULL));

    populate(arr, ARRAY_SIZE, 20);

    printf("Generated Array: ");
    for (int i = 0; i < ARRAY_SIZE; i++) 
    {
        printf("%d ", arr[i]);
    }
    printf("\n");

    for (int i = 0; i < ARRAY_SIZE - 2; i++) 
    {
        if (check(arr[i], arr[i + 1], arr[i + 2])) 
        {
            inc_nsets();
        }
    }

    printf("Number of sets of three consecutive elements satisfying the condition: %d\n", num_sets);

    return 0;
}
