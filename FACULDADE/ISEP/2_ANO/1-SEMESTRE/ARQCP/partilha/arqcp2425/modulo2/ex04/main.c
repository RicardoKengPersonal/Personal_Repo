#include <stdio.h>
#include "asm.h"

int op1=1, op2=2;

int main() {
    long result;
    result = yet_another_sum();

    printf("Result: %ld\n", result);

    return 0;
}
