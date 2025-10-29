typedef struct {
    short number;
    char age;
    int grades[5];
    char name[60];
    char address[100];
} Student;

// Function that fills a student's data in a given structure
void fill_student(Student* s, char age, short number, char* name, char* address) {
    s->age = age;
    s->number = number;

    // Copy the name and address strings using a loop
    for (int i = 0; i < 60; i++) {
        s->name[i] = name[i];
        if (name[i] == '\0') break;
    }
    for (int i = 0; i < 100; i++) {
        s->address[i] = address[i];
        if (address[i] == '\0') break;
    }
}