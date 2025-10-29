#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

#define NUM_STUDENTS 5

typedef struct {
    int num;
    char name[50];
    int grade;
} student_t;

student_t students[NUM_STUDENTS];

// Thread function to display a single student's data
void* display_student(void* arg) {
    student_t* s = (student_t*)arg;
    printf("Student Number: %d\n", s->num);
    printf("Name: %s\n", s->name);
    printf("Grade: %d\n\n", s->grade);
    pthread_exit(NULL);
}

// Function to display all students using threads
void display_all_students() 
{
    pthread_t threads[NUM_STUDENTS];

    for (int i = 0; i < NUM_STUDENTS; i++) 
    {
        if (pthread_create(&threads[i], NULL, display_student, &students[i]) != 0) 
        {
            perror("Failed to create thread");
        }
    }

    for (int i = 0; i < NUM_STUDENTS; i++) 
    {
        pthread_join(threads[i], NULL); //Waits for each thread to complete before 
                                        //  moving on. This ensures the main program doesn't exit prematurely.
    }
}

int main() 
{
    int choice;

    // Initialize student data (can be replaced with input if needed)
    for (int i = 0; i < NUM_STUDENTS; i++) 
    {
        students[i].num = i + 1;
        sprintf(students[i].name, "Student%d", i + 1);
        students[i].grade = (rand() % 20); // random grade between 0-20
    }

    while (1) {
        printf("\n---- Menu ----\n");
        printf("1. Display all students\n");
        printf("0. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                display_all_students();
                break;
            case 0:
                printf("Exiting program.\n");
                exit(0);
            default:
                printf("Invalid option. Try again.\n");
        }
    }

    return 0;
}
