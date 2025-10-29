#include <stdio.h>
#include <math.h>

float maiorDif(float *vec, int size)
{
    float dif = 0;

    for (int i = 0; i < size - 1; i++)
    {
        float diffAtual = fabs(vec[i + 1] - vec[i]);

        if (diffAtual > dif)
        {
            dif = diffAtual;
        }
    }

    return dif;
}

int main()
{
    int n;

    do
    {
        printf("Tamanho do vetor: ");
        scanf("%d", &n);

    } while (n < 1 || n > 20);

    float vec[n];

    for (int i = 0; i < n; i++)
    {
        printf("%d elemento do vetor: ", i + 1);
        scanf("%f", &vec[i]);
    }

    printf("Array: \n");

    for (int i = 0; i < n; i++)
    {
        printf("vec[%d] = %.2f \n", i, vec[i]);
    }

    float maiorDiferenca = maiorDif(vec, n);

    printf("A maior diferenca entre valores consecutivos e: %.2f\n", maiorDiferenca);

    return 0;
}

