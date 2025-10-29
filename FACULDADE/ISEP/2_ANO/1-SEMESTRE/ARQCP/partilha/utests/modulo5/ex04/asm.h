#ifndef ASM_H 
#define ASM_H 
typedef struct {
       short number;
       int grades[5];
       char age; 
       char name[60];
       char address[100];
} Student;

void update_address(Student *s,char *address); 

#endif 

