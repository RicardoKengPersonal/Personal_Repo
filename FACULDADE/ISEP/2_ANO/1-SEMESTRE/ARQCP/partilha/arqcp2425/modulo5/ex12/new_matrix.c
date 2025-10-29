#include <stdlib.h>
int **new_matrix(int lines, int columns){

    //Allocate memory for the matrix lines
    int **matrix = (int **)malloc(lines * sizeof(int *));

    //Allocate memory for the matrix columns
    for(int i = 0; i < lines; i++){
        *(matrix+i) = (int *)malloc(columns * sizeof(int));
    }
    
    return matrix;
}