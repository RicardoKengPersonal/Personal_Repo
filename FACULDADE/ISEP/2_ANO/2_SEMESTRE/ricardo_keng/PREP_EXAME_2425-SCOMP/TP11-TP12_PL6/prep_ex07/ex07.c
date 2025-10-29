#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

pthread_cond_t bought_beer_cond;
pthread_cond_t bought_chips_cond;
pthread_cond_t ready_to_eat_cond;
pthread_mutex_t mutex;

int beer = 0;
int chips = 0;
int ready_to_eat = 0;

void eat_and_drink()
{
    printf("Eating and drinking.\n");
}

void* buy_chips(void* arg)
{
    (void)arg;

    pthread_mutex_lock(&mutex);

    // Wait until beer is bought
    while (!beer)
    {
        pthread_cond_wait(&bought_beer_cond, &mutex);
    }

    printf("Buying chips..\n");
    sleep(7);
    chips = 1;

    // Signal chips bought
    pthread_cond_signal(&bought_chips_cond);

    // Increment ready_to_eat count
    ready_to_eat++;
    // If both are ready, signal the other thread
    if (ready_to_eat == 2)
        pthread_cond_broadcast(&ready_to_eat_cond);

    // Wait until both beer and chips are bought
    while (ready_to_eat < 2)
    {
        pthread_cond_wait(&ready_to_eat_cond, &mutex);
    }

    pthread_mutex_unlock(&mutex);

    eat_and_drink();

    pthread_exit(NULL);
}

void* buy_drinks(void* arg)
{
    (void)arg;

    pthread_mutex_lock(&mutex);

    printf("Buying beer..\n");
    sleep(5);
    beer = 1;

    // Signal beer bought
    pthread_cond_signal(&bought_beer_cond);

    // Wait until chips are bought
    while (!chips)
    {
        pthread_cond_wait(&bought_chips_cond, &mutex);
    }

    // Increment ready_to_eat count
    ready_to_eat++;
    // If both are ready, signal the other thread
    if (ready_to_eat == 2)
        pthread_cond_broadcast(&ready_to_eat_cond);

    // Wait until both beer and chips are bought
    while (ready_to_eat < 2)
    {
        pthread_cond_wait(&ready_to_eat_cond, &mutex);
    }

    pthread_mutex_unlock(&mutex);

    eat_and_drink();

    pthread_exit(NULL);
}

int main()
{
    pthread_t t1, t2;

    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&bought_beer_cond, NULL);
    pthread_cond_init(&bought_chips_cond, NULL);
    pthread_cond_init(&ready_to_eat_cond, NULL);

    pthread_create(&t1, NULL, buy_chips, NULL);
    pthread_create(&t2, NULL, buy_drinks, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&bought_beer_cond);
    pthread_cond_destroy(&bought_chips_cond);
    pthread_cond_destroy(&ready_to_eat_cond);

    return 0;
}
