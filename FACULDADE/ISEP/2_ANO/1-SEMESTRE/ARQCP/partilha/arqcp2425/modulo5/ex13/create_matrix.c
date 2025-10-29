#include <stdlib.h>
short **new_matrix(int lines, int columns){
    // Allocate memory for the array of pointers (rows)
    short ** matrix= malloc(lines * sizeof(short*));
    // Check if memory allocation for rows was successful
    if(matrix==NULL){
        return NULL;
    }
    // Allocate memory for each row
    for(int i=0;i<lines;i++){
        matrix[i] = (short *)malloc(columns * sizeof(short));
    // Check if memory allocation for columns in the current row was successful
    if(matrix==NULL){
        return NULL;
    }
    }
        return matrix;

}