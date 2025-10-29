#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

/*Example -- Changes after the fork*/

int main()
{
    int x = 10;

    pid_t p;

    p = fork();

    if (p > 0)
    {
        printf("I am your father\n");
    } else {
        printf("I am the child\n");
        /*Child changes the copy of x*/
        x++;
    }

    printf("x = %d\n",x);

    return 0;
}