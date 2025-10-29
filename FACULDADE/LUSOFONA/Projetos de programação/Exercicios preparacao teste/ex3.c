#include <stdio.h>

void lerInteiro(int *num)
{

    printf("Numero a calcular: ");
    scanf("%d",num);
}


int NumPerfeito(int num)
{

    int soma = 0;


    if (num == 1)
    {
        printf("Numero perfeito.");
        return 0;
    }

    for (int i = 1 ; i < num ; i++)
    {
        if( num % i == 0)
        {
            soma = soma + i;
        }
    }

    if( soma == num )
    {
        printf("Numero perfeito.");

    }else
    {
        printf("Numero nao perfeito.");
    }
}

int main ()
{
    int num;

    lerInteiro(&num);

    NumPerfeito(num);

    return 0;

}
