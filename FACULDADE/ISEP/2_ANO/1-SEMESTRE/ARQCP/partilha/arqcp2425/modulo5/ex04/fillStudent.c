#include <string.h>
#include "student.h"
	
void fill_student(Student *s, char age, short number, char *name, char *address){
	s -> number = number;
	s -> age = age;
	strcpy(s -> name, name);
	strcpy(s -> address, address);
}
