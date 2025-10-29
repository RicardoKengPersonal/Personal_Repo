#include <stdio.h>

int main ()
{
    FILE *ficheiro;

    int n;

    printf("Numero de numeros a inserir: ");
    scanf("%d",&n);

    int vec[n];

    for (int i = 0; i < n ; i++)
    {
        printf("%do numero: ",i+1);
        scanf("%d",&vec[i]);
    }

    ficheiro = fopen("Numeros.txt","w");

    if(ficheiro != NULL)
    {
        for (int i = 0; i < n; i++)
        {
            fprintf(ficheiro,"%d",vec[i]);
        }
        printf("\nFicheiro criado com sucesso.");

        fclose(ficheiro);

    }else
    {
        printf("\nErro a criar ficheiro.");
    }


}
