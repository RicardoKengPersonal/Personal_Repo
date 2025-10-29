#include <stdlib.h>
int **new_matrix(int lines, int columns){
	int x;
	int **newmatrix = malloc(lines * sizeof(long));
	for(x = 0 ; x < lines ; x++) {
		*(newmatrix+x) = malloc(columns * sizeof(int));
	}
	
	return newmatrix;
}
