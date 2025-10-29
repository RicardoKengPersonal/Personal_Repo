#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>

#define SIZE 20

int main()
{
    pid_t pid;
    int data[SIZE];
    int result[SIZE];
    int status;

    srand(time(NULL));

    for(int i = 0; i < SIZE ; i++)
    {
        data[i] = (rand() % 10) + 1;
        printf("Filled data[%d] = %d\n",i,data[i]);
    }

    pid = fork();

    if (pid == -1)
    {
        perror("Error forking. Exiting..\n");
        exit(1);
    }

    if (pid > 0 ) // Father
    {
        for (int i = 0; i < SIZE / 2 ; i++)
        {
            result[i] = data[i] * 4 + 20;
        }

        printf("[Father]: I calculated the first 10 positions.\n");   

        pid = wait(&status); // Let the child finish first

        if (WIFEXITED(status))
        {
            printf("[Father]: The child exited with status %d\n",WEXITSTATUS(status));
        }

        for(int i = 0; i < SIZE / 2 ; i++)
        {
            printf("Result[%d] = %d\n",i,result[i]);
        }

    } else {

        for(int i = SIZE / 2; i < SIZE ; i++)
        {
            result[i] = data[i] * 4 + 20;
        }

        printf("[Child]: I calculated the last 10.\n");
        
        for (int i = SIZE / 2; i < SIZE ; i++)
        {
            printf("Result[|%d] = %d\n",i,result[i]);
        }

        exit(2);
    }

    return 0;
}