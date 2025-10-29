#include <stdio.h>
#include "asm.h"

int main() {
    int buffer[5] = {0}; // Buffer circular com capacidade de 5 elementos
    int length = 5;      // Comprimento do buffer
    int tail = 0;
    int head = 0;

    // head >= tail  = head - tail
    // head < tail = (length - tail) + head
    tail = 3;
    head = 2;

    int num_elements = get_n_element(buffer, length, &tail, &head);
    printf("Número de elementos no buffer: %d\n", num_elements);


    return 0;
}
