#ifndef STRUCT_H
#define STRUCT_H 

typedef union {
        int a;
        short b;
        char c;
        long int d;
}unionB; 


typedef struct {
   short int a[3];
   short b;
   long long int c;
   short d;
   char e;
   unionB ub;
}structA;



#endif 
