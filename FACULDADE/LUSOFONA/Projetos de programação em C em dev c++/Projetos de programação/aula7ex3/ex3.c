#include <stdio.h>

int lerInteiro(void)
{

    int num;

    do
    {
    printf("Insira um numero: ");
    scanf("%d",&num);
    }while (num <= 0);


}

int numeroPerfeito(int num)
{

    int i,divisor,conta_perf;

    conta_perf = 0;

    divisor = 0;

    for (i = 1; i < num; i++)
    {
        if(num % i == 0)
        {
            divisor = i;
            conta_perf = conta_perf + divisor;
        }
    }
    if ( num == conta_perf)
    {
        printf("\nO numero %d e perfeito.\n",num);

        return 0;
    }

    printf("\nO numero %d nao e perfeito.\n",num);

    return 0;

}

int main ()
{
    int i,divisor,conta_perf,num;

    num = lerInteiro();

    numeroPerfeito(num);

}
