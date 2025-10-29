#include <stdio.h>

void LerArray (int *n)
{
    printf("Tamanho do array: ");
    scanf("%d",n);
}

int main ()
{
    int n;

    FILE *ficheiro;

    ficheiro = fopen ("Vetor.txt","w");

    LerArray(&n);

    int vec[n];

    for (int i = 0; i < n; i++)
    {
        printf("\n%d valor : ",i+1);
        scanf("%d",&vec[i]);
    }

    if (ficheiro != NULL)
    {
        for (int i = 0; i < n ; i++)
        {
            fprintf(ficheiro,"\nvec[%d] = %d",i,vec[i]);
        }

        printf("Ficheiro criado com sucesso.");

    }else
    {
        printf("Erro ao criar ficheiro.");
    }
    fclose(ficheiro);
}
