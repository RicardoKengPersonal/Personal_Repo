#include <stdio.h>

int main ()
{
    FILE *ficheiro;

    int n;

    printf("Numero de numeros a inserir: ");
    scanf("%d",&n);

    int vec[n];

    for (int i = 0; i < n; i++)
    {
        printf("%do do vetor: ",i+1);
        scanf("%d",&vec[i]);
    }

    ficheiro = fopen("escrever.txt","w");

    if (ficheiro != NULL)
    {
        for (int i = 0; i < n; i++)
        {
            fprintf(ficheiro,"\nvec[%d] = %d",i+1,vec[i]);
        }
       printf("\nFicheiro escrito com sucesso.");


    }else
    {
        printf("\nErro a criar ficheiro.");
    }
    fclose(ficheiro);

    int vec_leitura[n];

    int aux = 0;

    printf("\nNumeros do vetor do ficheiro: ");

    ficheiro = fopen("escrever.txt","r");

    while(fscanf(ficheiro, "%d",&vec_leitura[aux])!= EOF)
    {
        printf("\n%do elemento = %d",aux+1,vec_leitura[aux]);
        aux++;
    }

    fclose(ficheiro);

}
