#include <stdio.h>
#include <stdlib.h>
#include "approved_semester.h"

int main(void){
    group g;
    g.n_students = 3;
    unsigned short *grades = (unsigned short *)malloc(g.n_students * sizeof(unsigned short));
    g.individual_grades = grades;

    free(grades);
    return 0;
}