#include <stdio.h>
#include "frequencies.h"

int main()
{
	float grades[] = {8.23, 12.25, 16.45, 12.45, 10.05, 6.45, 14.45, 0.0, 12.67, 16.23, 18.7};
	int size = 11;
	int freq[21]={0};
	
	for(int i = 0; i < size; i++)
	{
		printf("Student %d grades:  %.2f\n", i , grades[i]);
	}
	
	
	frequencies(grades,11,freq);
	
	for (int i = 0; i < 21; i++)
	{
		printf("Frequencies of %d: %d\n",i, freq[i]);
	}
	printf("\n");
	return 0;
}
