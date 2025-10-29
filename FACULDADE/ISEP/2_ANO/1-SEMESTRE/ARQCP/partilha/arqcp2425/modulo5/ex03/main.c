#include <stdio.h>
#include "fill_student.h"


int main(){
	 // Statically allocate an array of 5 Students
        Student studentArray[5];


    fill_student(&studentArray[0], 21, 1, "Eduardo", "123 Main St");
    fill_student(&studentArray[1], 22, 2, "Joao", "123 St");
    fill_student(&studentArray[2], 21, 3, "Pedro", "124 Main St");
    fill_student(&studentArray[3], 18, 4, "Joana", "125 Main St");
    fill_student(&studentArray[4], 19, 5, "Maria", "126 Main St");


       for (int i = 0; i < 5; ++i) {
            printf("Student %d\n", i + 1);
            printf("Number: %d\n", studentArray[i].number);
            printf("Age: %d\n", studentArray[i].age);
            printf("Name: %s\n", studentArray[i].name);
            printf("Address: %s\n", studentArray[i].address);
            printf("\n");
        }

        return 0;

}



