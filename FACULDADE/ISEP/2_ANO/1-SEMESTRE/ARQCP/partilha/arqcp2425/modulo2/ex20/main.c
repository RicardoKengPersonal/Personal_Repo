#include <stdio.h>
#include "check_num.h"

int num = 15;

int main() {
    char result;
    result = check_num();
    printf("Result: %d\n", result);
    return 0;
}
