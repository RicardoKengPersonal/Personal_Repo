#include <stdio.h>


int main ()
{
    int sum,num,max;

    sum = 0;
    max = 0;

    printf("Soma de numeros ate 1000.");

    do
    {
        printf("\n\nInsira um numero para adicionar ao anterior: ");
        scanf("%d",&num);

        if ( num > max )
        {
            max = num;
            printf("\nMaximo atualizado para %d",max);
        }

        sum = sum + num;

        printf("A soma vai em %d",sum);


    } while ( sum <= 1000);

    printf("\nA soma chegou ao maximo.");
    printf("\nO maior numero inserido foi : %d",max);

    return 0;
}
