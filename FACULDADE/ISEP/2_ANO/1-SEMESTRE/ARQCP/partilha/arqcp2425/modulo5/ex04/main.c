#include <stdio.h>
#include "student.h"
#include "update_address.h"


int main(void) {

	// Create a student structure array
	Student students[5];

	// Fill the data for each student using the fill_student function and addresses should be like Rua 1, Rua 2, etc.
	fill_student(&students[0], 20, 1, "Joao", "Rua 1");
	fill_student(&students[1], 21, 2, "Maria", "Rua 2");
	fill_student(&students[2], 22, 3, "Jose", "Rua 3");
	fill_student(&students[3], 23, 4, "Ana", "Rua 4");
	fill_student(&students[4], 24, 5, "Paulo", "Rua 5");

	// Print the data for each student
	for (int i = 0; i < 5; i++) {
		printf("Student %d:\n", i + 1);
		printf("  Number: %hd\n", students[i].number);
		printf("  Age: %d\n", students[i].age);
		printf("  Name: %s\n", students[i].name);
		printf("  Address: %s\n", students[i].address);
	}

	// Update the address for each student
	update_address(&students[0], "Rua 6");
	update_address(&students[1], "Rua 7");
	update_address(&students[2], "Rua 8");
	update_address(&students[3], "Rua 9");
	update_address(&students[4], "Rua 10");
	
	
	printf("\n\n UPDATING ADDRESSES \n\n");

	// Print the data for each student after the update of the grades
	for (int i = 0; i < 5; i++) {
		printf("Student %d:\n", i + 1);
		printf("  Number: %hd\n", students[i].number);
		printf("  Age: %d\n", students[i].age);
		printf("  Name: %s\n", students[i].name);
		printf("  Address: %s\n", students[i].address);

	}

	return 0;
}