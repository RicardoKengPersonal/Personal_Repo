#include <stdio.h>


void LerInteiro (int *num)
{
    printf("Numero para calcular: ");
    scanf("%d",num);
}

int numPerfeito (int num)
{
    int soma = 0;

    if (num == 1)
    {
        printf("Numero nao perfeito.");
        return ;
    }

    for(int i = 1; i < num; i++ )
    {
        if( num % i == 0)
        {
            soma = soma + i;

        }
    }

    if (num == soma)
    {
        printf("O Numero %d e perfeito.",num);
    }else
    {
        printf("O numero %d nao e perfeito.",num);
    }

}

int main ()
{
    int num;

    LerInteiro(&num);

    numPerfeito(num);

    return 0;

}
