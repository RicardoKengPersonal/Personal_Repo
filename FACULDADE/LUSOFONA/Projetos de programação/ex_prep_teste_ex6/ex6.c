#include <stdio.h>

int maior(int *vec, int size)
{
    int maior = -999;

    for (int i = 0; i < size; i++)
    {
        if (vec[i] > maior)
        {
            maior = vec[i];
        }
    }

    return maior;
}

int main()
{
    int n;

    printf("Numero de elementos de um array: ");
    scanf("%d", &n);

    int vec[n];

    for (int i = 0; i < n; i++)
    {
        printf("%d elemento do array: ", i + 1);
        scanf("%d", &vec[i]);
    }

    printf("Array:\n");

    for (int i = 0; i < n; i++)
    {
        printf("vec[%d]: %d\n", i + 1, vec[i]);
    }

    int maiorElemento = maior(vec, n);

    printf("O maior elemento do array: %d\n", maiorElemento);

    return 0;
}
