#include <stdio.h>
#include <stdlib.h>
#include "find_matrix.h"
#include "new_matrix.h"

int main(void){

    int num = 12;
    int y = 4;
    int k = 3;
    int **m = new_matrix(y, k);

    printf("\nMatrix:\n");
    for(int i = 0; i < y; i++){
        for(int j = 0; j < k; j++){
            *(*(m+i)+j) = random() % 50; //Fill all positions with a random number between 0 and 49
            printf("%d ", *(*(m+i)+j));
        }
        printf("\n");
    }
    
    if(find_matrix(m, y, k, num) == 1){
        printf("\nNum %d Found!\n", num);
    }    
    else{
        printf("\nNum %d not found!\n", num);
    }

    for(int j= 0; j < y; j++){
        free(*(m+j));
    }
    free(m);
    return 0;
}