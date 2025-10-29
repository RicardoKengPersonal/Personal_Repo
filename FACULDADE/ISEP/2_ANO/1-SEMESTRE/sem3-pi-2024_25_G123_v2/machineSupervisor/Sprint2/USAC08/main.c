#include <stdio.h>
#include "asm.h"

int main() {

    int length = 5;

    int buffer[5];

    buffer[0] = 6;
    buffer[1] = 2;
    buffer[2] = 3;
    buffer[3] = 4;
    buffer[4] = 20;

    int head = 0;
    int *ptr_head;
    ptr_head = &head;

    int tail = 4;
    int *ptr_tail;
    ptr_tail = &tail;

    int array[5];

    int n = 2;

    int result = move_n_to_array(buffer, length, ptr_tail, ptr_head, n, array);

    if (result == 1) {

        printf("Dequeue Success\n");
        
        for(int i = 0; i < 5; i++) {
            printf("Array[%d]: %d \n", i, array[i]);
        }

    } else {

        printf("Fail");
    }

    return 0;
}
