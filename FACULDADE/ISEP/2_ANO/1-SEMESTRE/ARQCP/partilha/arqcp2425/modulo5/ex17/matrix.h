#include <stdlib.h>

typedef union {
    int a;
    short b;
    char c;
    long int d;
} unionB;

typedef struct {
    short int a[3];
    short b;
    long long int c;
    short d;
    char e;
    unionB ub;
} structA;

char return_unionB_b(structA **matrix, int i, int j);

void fill_unionA(structA *sA, short int a[3], short b, long long int c, short d, char e, unionB ub);

structA ** matrixCreate(int i, int j);

void freeMatrix(structA **matrix, int i);