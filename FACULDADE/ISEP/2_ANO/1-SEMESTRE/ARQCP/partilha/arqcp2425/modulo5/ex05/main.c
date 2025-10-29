#include <stdio.h>
#include "newGrades.h"
#include "string.h"


int main(){

    Student s;
    s.number = 1;
    s.age = 18;
    strcpy(s.name, "Joao");
    strcpy(s.address, "123 St");

    int new_grades[] = {10, 12, 14, 16, 18};


    update_grades(&s, new_grades);


       for (int i = 0; i < 5; i++) {
              printf("Grade %d: %d\n", i, s.grades[i]);
          }


        return 0;

}