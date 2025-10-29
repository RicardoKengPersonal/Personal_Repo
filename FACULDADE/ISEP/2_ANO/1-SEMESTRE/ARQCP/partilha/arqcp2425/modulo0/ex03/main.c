#include <stdio.h>

int mul(int a, int b)
{
    int result = 0;

    for (int i = 0; i < b; i++)
    {
        result += a;
    }

    return result;
}

int main()
{
    int num1, num2;
    int multiplicacao_somas;

    printf("Integer 1: ");
    scanf("%d", &num1);

    printf("Integer 2: ");
    scanf("%d", &num2);

    multiplicacao_somas = mul(num1, num2);

    printf("Resultado da multiplicacao: %d\n", multiplicacao_somas);

    return 0;
}
