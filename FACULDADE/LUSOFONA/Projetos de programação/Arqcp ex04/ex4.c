#include <stdio.h>

int soma(int a, int b)
{
    return a + b;
}

int soma_digitos(int n)
{
    int soma_digito = 0;

    while (n > 0)
    {
        int digito = n % 10;

        soma_digito = soma(soma_digito, digito);

        n /= 10;
    }

    return soma_digito;
}

int main()
{

    int num;

    do
    {
        printf("Integer: ");
        scanf("%d",&num);

    }while(num < 0 || num > 99);

    int result = soma_digitos(num);

    printf("A soma dos digitos de %d e: %d\n", num, result);

    return 0;
}
