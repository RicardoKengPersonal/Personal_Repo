#include <stdio.h>

int soma (int a, int b)
{
    return a+b;
}

int main ()
{

    int num1,num2;

    int soma_integers = 0;

    printf("Integer a:");
    scanf("%d",&num1);

    printf("Integer b: ");
    scanf("%d",&num2);

    soma_integers = soma(num1,num2);

    printf("Soma: %d\n",soma_integers);

    return 0;


}
