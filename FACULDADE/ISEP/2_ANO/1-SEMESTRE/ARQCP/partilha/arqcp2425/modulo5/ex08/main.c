#include <stdio.h>
#include "fill_s2.h"

int main(void){
    s2 s;
    short vw[3] = {1, 2, 3};
    int vj = 2;
    char vc[3] = {'a', 'b', 'c'};

    fill_s2(&s, vw, vj, vc);
    return 0;
}
