#include <stdlib.h>

int **add_matrixes(int **a, int **b, int y, int k){
	int **sum = malloc(y * sizeof(long));
	int x,z;
	
	for(x = 0 ; x < y ; x++){
		*(sum+x) = malloc(k*sizeof(int));
	}
	
	for(x = 0 ; x < y ; x++){
		for(z = 0 ; z < k ; z++){
			sum[x][z] = a[x][z]+b[x][z];
		}
	}
	return sum;
}
