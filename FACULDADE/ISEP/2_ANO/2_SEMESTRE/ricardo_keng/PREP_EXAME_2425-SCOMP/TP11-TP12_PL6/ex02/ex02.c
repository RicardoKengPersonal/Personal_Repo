#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

void* prime_numbers(void* arg)
{
    int number = *((int *)arg);
    free(arg);

    char buffer[100];

    for(int i = 2; i <= number; i++)
    {
        int is_prime = 1;
        for(int j = 2; j * j <= i; j++)
        {
            if(i % j == 0)
            {
                is_prime = 0;
                break;
            }
        }
        if(is_prime)
        {
            int len = snprintf(buffer, sizeof(buffer), "%d is prime\n", i);
            write(STDOUT_FILENO, buffer, len);
        }
    }

    pthread_exit(NULL);
}

int main()
{
    int num;
    pthread_t thread;
    int *arg;

    printf("Enter a positive number:");
    scanf("%d",&num);

    arg = malloc(sizeof(int)); // espaco de memoria reservado para arg tem que ser maior ou igual a variavel que ele vai "adquirir"

    *arg = num; // conteudo de arg vai ser igual ao de num

    pthread_create(&thread,NULL,prime_numbers,(void*)arg); // cria a thread

    pthread_join(thread,NULL); // espera que a thread termine

    exit(0);
}