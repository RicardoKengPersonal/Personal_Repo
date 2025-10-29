#include <stdio.h>

int main()
{
    int n, x;

    do
    {
        printf("Tamanho do array 1: ");
        scanf("%d", &n);

    } while (n < 1 || n > 19);

    do
    {
        printf("Tamanho do array 2: ");
        scanf("%d", &x);

    } while (x < 1 || x > 19);

    int vec1[n];
    int vec2[x];

    for (int i = 0; i < n; i++)
    {
        printf("%d valor do array 1: ", i + 1);
        scanf("%d", &vec1[i]);
    }

    for (int j = 0; j < x; j++)
    {
        printf("%d valor do array 2: ", j + 1);
        scanf("%d", &vec2[j]);
    }

    printf("\nArray 1:\n");

    for (int i = 0; i < n; i++)
    {
        printf("vec1[%d] = %d\n", i, vec1[i]);
    }

    printf("\nArray 2:\n");

    for (int j = 0; j < x; j++)
    {
        printf("vec2[%d] = %d\n", j, vec2[j]);
    }

    int tam_vec_concat = n + x;
    int vec_concat[tam_vec_concat];

    for (int k = 0; k < n; k++)
    {
        vec_concat[k] = vec1[k];
    }

    for (int k = 0; k < x; k++)
    {
        vec_concat[n + k] = vec2[k];
    }

    printf("\nArray concatenacao:\n");

    for (int f = 0; f < tam_vec_concat; f++)
    {
        printf("vec_concat[%d] = %d\n", f, vec_concat[f]);
    }

    int tam_vec_soma = (n > x) ? n : x;
    int vec_soma[tam_vec_soma];

    if (n >= x)
    {
        for (int i = 0; i < x; i++)
        {
            vec_soma[i] = vec1[i] + vec2[i];
        }
        for (int i = x; i < n; i++)
        {
            vec_soma[i] = vec1[i];
        }
    }
    else
    {
        for (int i = 0; i < n; i++)
        {
            vec_soma[i] = vec1[i] + vec2[i];
        }
        for (int i = n; i < x; i++)
        {
            vec_soma[i] = vec2[i];
        }
    }

    printf("\nArray soma:\n");

    for (int i = 0; i < tam_vec_soma; i++)
    {
        printf("vec_soma[%d] = %d\n", i, vec_soma[i]);
    }

    return 0;
}


