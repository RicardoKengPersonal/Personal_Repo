#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

#define NUM_ATHLETES 300

typedef struct {
    int number;
    int event_scores[3]; // swim, bike, run
    int total_time;
    float final_score;
} athlete_score;

athlete_score scores[NUM_ATHLETES];

pthread_mutex_t mutex;
pthread_cond_t cond_t1;
pthread_cond_t cond_t2;

int current_index = 0;
int ready = 0; // 0: T1's turn, 1: T2's turn

// Random number generator helper
int gera_num(int inicio, int fim) 
{
    return inicio + rand() % (fim - inicio + 1);
}

// T1: Generate scores for each athlete
void* t1_func(void* arg) 
{
    while (1) {
        pthread_mutex_lock(&mutex);

        if (current_index >= NUM_ATHLETES) 
        {
            pthread_cond_signal(&cond_t2); // Wake T2 if waiting
            pthread_mutex_unlock(&mutex);
            break;
        }

        while (ready != 0)
            pthread_cond_wait(&cond_t1, &mutex);

        // Generate data
        scores[current_index].number = current_index + 1;
        scores[current_index].event_scores[0] = gera_num(0, 100); // swim
        scores[current_index].event_scores[1] = gera_num(0, 100); // bike
        scores[current_index].event_scores[2] = gera_num(0, 100); // run
        scores[current_index].total_time = gera_num(50, 100);

        ready = 1;
        pthread_cond_signal(&cond_t2);
        pthread_mutex_unlock(&mutex);
    }

    return NULL;
}

// T2: Compute final_score for each athlete
void* t2_func(void* arg) 
{
    while (1) 
    {
        pthread_mutex_lock(&mutex);

        while (ready != 1) {
            if (current_index >= NUM_ATHLETES) 
            {
                pthread_mutex_unlock(&mutex);
                return NULL;
            }
            pthread_cond_wait(&cond_t2, &mutex);
        }

        // Compute score
        float avg_event = (scores[current_index].event_scores[0] +
                           scores[current_index].event_scores[1] +
                           scores[current_index].event_scores[2]) / 3.0;

        scores[current_index].final_score = 0.3 * avg_event + 0.7 * scores[current_index].total_time;

        current_index++;
        ready = 0;

        pthread_cond_signal(&cond_t1);
        pthread_mutex_unlock(&mutex);
    }

    return NULL;
}

int main() 
{
    srand(time(NULL));

    pthread_t t1, t2;

    pthread_create(&t1, NULL, t1_func, NULL);
    pthread_create(&t2, NULL, t2_func, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    // Print results
    for (int i = 0; i < NUM_ATHLETES; i++) {
        printf("Athlete %d: Swim=%d, Bike=%d, Run=%d, Time=%d, Final Score=%.2f\n",
               scores[i].number,
               scores[i].event_scores[0],
               scores[i].event_scores[1],
               scores[i].event_scores[2],
               scores[i].total_time,
               scores[i].final_score);
    }

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond_t1);
    pthread_cond_destroy(&cond_t2);

    return 0;
}
