#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#define NSTUDENTS 300

typedef struct {
    int number;
    float sprint_grades[3];
    float exam;
    float final_grade;
} student_grade;

student_grade grades[NSTUDENTS];
int pos = 0, neg = 0;
int current_student = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond_grades_ready = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_final_ready = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_pos = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_neg = PTHREAD_COND_INITIALIZER;

// Control variables
int grades_ready = 0;
int final_ready = 0;
int final_computed = 0; // 0: not computed, 1: computed
float last_final_grade = 0.0;

void* generate_random_grades(void* arg)
{
    for (int i = 0; i < NSTUDENTS; i++)
    {
        pthread_mutex_lock(&mutex);

        grades[i].number = i + 1;
        for (int j = 0; j < 3; j++)
            grades[i].sprint_grades[j] = rand() % 21;
        grades[i].exam = rand() % 21;

        grades_ready = 1;
        final_computed = 0;
        pthread_cond_broadcast(&cond_grades_ready);

        // Wait until final grade is computed before moving to next student
        while (!final_computed)
            pthread_cond_wait(&cond_final_ready, &mutex);

        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(NULL);
}

void* compute_final_grade(void* arg)
{
    for (int i = 0; i < NSTUDENTS; i++)
    {
        pthread_mutex_lock(&mutex);

        // Wait for grades to be ready and not yet computed
        while (!grades_ready || final_computed)
            pthread_cond_wait(&cond_grades_ready, &mutex);

        // Only one of T2/T3 should compute the final grade
        if (!final_computed)
        {
            grades[i].final_grade = 0.4 * (grades[i].sprint_grades[0] + grades[i].sprint_grades[1] + grades[i].sprint_grades[2]) / 3.0
                                  + 0.6 * grades[i].exam;
            last_final_grade = grades[i].final_grade;
            current_student = i;
            grades_ready = 0;
            final_computed = 1;
            pthread_cond_signal(&cond_final_ready);

            // Signal T4 or T5
            if (grades[i].final_grade >= 10.0)
                pthread_cond_signal(&cond_pos);
            else
                pthread_cond_signal(&cond_neg);
        }

        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(NULL);
}

void* count_positive(void* arg)
{
    for (int i = 0; i < NSTUDENTS; i++)
    {
        pthread_mutex_lock(&mutex);
        // Wait for a positive grade for the correct student
        while (current_student != i || last_final_grade < 10.0)
            pthread_cond_wait(&cond_pos, &mutex);
        pos++;
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(NULL);
}

void* count_negative(void* arg)
{
    for (int i = 0; i < NSTUDENTS; i++)
    {
        pthread_mutex_lock(&mutex);
        // Wait for a negative grade for the correct student
        while (current_student != i || last_final_grade >= 10.0)
            pthread_cond_wait(&cond_neg, &mutex);
        neg++;
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(NULL);
}

int main()
{
    srand(time(NULL));
    pthread_t t1, t2, t4, t5;

    pthread_create(&t1, NULL, generate_random_grades, NULL);
    pthread_create(&t2, NULL, compute_final_grade, NULL);
    pthread_create(&t4, NULL, count_positive, NULL);
    pthread_create(&t5, NULL, count_negative, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t4, NULL);
    pthread_join(t5, NULL);

    printf("Number of positive grades: %d\n", pos);
    printf("Number of negative grades: %d\n", neg);

    return 0;
}