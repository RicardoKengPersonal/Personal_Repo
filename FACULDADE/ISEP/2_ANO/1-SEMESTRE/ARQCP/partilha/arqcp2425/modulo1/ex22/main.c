#include <stdio.h>
#include "swap_pointers.h"

int main(void)
{
	int data1[5]={1,2,3,4,5};
	int data2[5]={10,20,30,40,50};
	
	int *bigger = data1;
	int *smaller = data2;
	
	swap_pointers(&bigger,&smaller,5);
	return 0;
}
