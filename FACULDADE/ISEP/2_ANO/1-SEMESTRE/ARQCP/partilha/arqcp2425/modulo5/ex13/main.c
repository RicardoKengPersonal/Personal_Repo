#include <stdio.h>
#include <stdlib.h>
#include "count_odd_matrix.h"
#include "create_matrix.h"
int main() {

	short** matrix;
    int lines = 5;
    int columns = 3;

    matrix = new_matrix(lines, columns);

    for(int i = 0; i < lines; i++) {
        for(int j = 0; j < columns; j++) {
            *(*(matrix + i) + j) = random() % 100;
        }

    }

    printf("result: %d\n", count_odd_matrix(matrix, lines, columns));

    for (int j = 0; j < lines; j++) {
        free(*(matrix + j));
    }
    free(matrix);
    return 0;
}