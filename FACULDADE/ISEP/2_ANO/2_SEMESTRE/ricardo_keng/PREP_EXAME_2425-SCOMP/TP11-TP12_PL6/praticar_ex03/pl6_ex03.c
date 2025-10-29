#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

typedef struct{
    int number;
    char name[20];
    float grade;
} Student;

void* thread_func(void* arg)
{
    Student* s = (Student*)arg;
    printf("Number: %d, Name: %s, Grade: %.2f\n", s->number, s->name, s->grade);
    free(s); // Free the allocated memory
    pthread_exit(NULL);
}

int main()
{
    pthread_t threads[5];

    Student students[5] = {
        {1,"Aluno1",17.5},
        {2,"Aluno2",16.0},
        {3,"Aluno3",15.5},
        {4,"Aluno4",14.0},
        {5,"Aluno5",10}
    };

    for(int i = 0; i < 5; i++)
    {
        Student *s_copy = malloc(sizeof(Student));
        if (s_copy == NULL) {
            perror("malloc failed");
            exit(1);
        }
        *s_copy = students[i]; // Copy struct contents
        pthread_create(&threads[i], NULL, thread_func, (void*)s_copy);
    }

    for(int i = 0; i < 5; i++)
    {
        pthread_join(threads[i], NULL);
    }
}