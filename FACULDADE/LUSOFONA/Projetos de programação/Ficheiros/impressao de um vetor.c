#include <stdio.h>

int main ()
{
    int vec[10]={0,1,2,3,4,5,6,7,8,9};

    FILE *ficheiro;

    ficheiro = fopen ("Vetor.txt","w");

    if (ficheiro != NULL)
    {
        for (int i = 0; i < 10 ; i++)
        {
            fprintf(ficheiro,"vec[%d] = %d",i,vec[i]);
        }

        printf("Ficheiro criado com sucesso.");

    }else
    {
        printf("Erro ao criar ficheiro.");
    }
    fclose(ficheiro);
}
