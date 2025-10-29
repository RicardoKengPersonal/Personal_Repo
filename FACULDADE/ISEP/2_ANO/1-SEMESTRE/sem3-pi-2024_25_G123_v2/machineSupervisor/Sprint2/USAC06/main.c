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
    int* ptr_head;
    ptr_head = &head;

    int tail = 4;
    int* ptr_tail;
    ptr_tail = &tail;

    int value = 0;
    int* ptr_value;
    ptr_value = &value;

    int result = dequeue_value(buffer, length, ptr_tail, ptr_head, ptr_value);

    if (result == 1) {

        printf("Dequeue Success\n");
        printf("Removed Element: %d \n", value);

    } else {

        printf("Fail");
    }

    return 0;
}
