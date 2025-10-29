#include <stdio.h>

int maior (int *vec, int size)
{
    int maior = -999;

    for (int i = 0; i < size; i++)
    {
        if(vec[i] > maior )
        {
            maior = vec [i];
        }
    }

    return maior;
}


int menor (int *vec, int size)
{
    int menor = 999;

    for (int i = 0; i < size; i++)
    {
        if( vec[i] < menor)
        {
            menor = vec [i];
        }
    }

    return menor;
}

int main ()
{

    int n;

    do
    {

        printf("Tamanho do array: ");
        scanf("%d",&n);

    }while (n <= 1 || n > 20);

    int vec[n];

    for (int i = 0; i < n ; i++)
    {
        printf("%d elemento do array: ",i+1);
        scanf("%d",&vec[i]);
    }

    printf("Array:\n");

    for (int i = 0; i < n; i++)
    {
        printf("\nvec[%d]: %d",i,vec[i]);
    }

    int maiorElemento = maior(vec,n);

    int menorElemento = menor (vec,n);

    printf("\nmaior elemento do array: %d",maiorElemento);

    printf("\nMenor elemento do array: %d",menorElemento);


}
