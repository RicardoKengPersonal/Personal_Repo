#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
    int a,b,c,d;

    a = 0;
    b = fork();
    c = getpid();
    d = getppid();
    a = a + 5;

    printf("\na = %d, b = %d ,c = %d, d = %d",a,b,c,d);

    return 0;
}