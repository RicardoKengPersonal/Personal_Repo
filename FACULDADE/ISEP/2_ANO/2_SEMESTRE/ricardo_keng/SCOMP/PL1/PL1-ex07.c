#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

void M(char *string)
{
    printf("%c\n", *string);
}

int main()
{
    pid_t child;
    char string;

    for (int i = 0; i < 3; i++)
    {
        child = fork();

        if (child == -1)
        {
            perror("Fork Failure.\n");
            exit(EXIT_FAILURE);
        }

        if (child > 0)
        {
            printf("The father prints:\n");
            string = 'A';
            M(&string);
            wait(NULL);
        }
        else
        {
            printf("The child prints:\n");
            string = 'B';
            M(&string);
            exit(0);
        }
    }

    return 0;
}
