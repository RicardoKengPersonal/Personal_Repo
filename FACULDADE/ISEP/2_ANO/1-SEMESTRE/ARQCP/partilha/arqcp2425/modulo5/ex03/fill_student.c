#include <string.h>
#include "fill_student.h"


void fill_student(Student *s, char age, short number, char *name, char *address) {
    if (s != NULL) {
        s->number = number;
        s->age = age;

        // Copy name, ensuring null-termination
        size_t name_len = strnlen(name, sizeof(s->name) - 1);
        strncpy(s->name, name, name_len);  //copys until name > name_len
        s->name[name_len] = '\0'; //Adds the null terminator 0

        // Copy address, ensuring null-termination
        size_t address_len = strnlen(address, sizeof(s->address) - 1);
        strncpy(s->address, address, address_len); //copys until address > address_len
        s->address[address_len] = '\0';  //Adds the null terminator 0
    }
}

