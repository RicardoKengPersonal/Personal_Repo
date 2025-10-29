#include <stdio.h>

int main ()
{
    FILE *ficheiro;

    int n;

    printf("Passagem de array para um ficheiro.");
    printf("\nNumero de elementos: ");
    scanf("%d",&n);

    int vec[n];

    for (int i = 0; i < n ; i++)
    {
        printf("\n%do elemento: ",i+1);
        scanf("%d",&vec[i]);
    }


    ficheiro = fopen("Impressao_vec.txt","w");

    if (ficheiro != NULL)
    {
        printf("\nficheiro criado com sucesso.");
        for (int i = 0; i < n; i++)
        {
            fprintf(ficheiro,"\nvec[%d] = %d",i,vec[i]);
        }
    }else
    {
        printf("Erro a criar ficheiro.");
    }

    fclose(ficheiro);


}
