#ifndef ASM_H 
#define ASM_H 
typedef struct {
       short number;
       int grades[5];
       char age;
       char name[60];
       char address[100];
} Student;

int locate_greater(Student *s, int minimum , int * greater_grades);
#endif 

