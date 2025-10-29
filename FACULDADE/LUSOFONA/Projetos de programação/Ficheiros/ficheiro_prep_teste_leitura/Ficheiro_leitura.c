#include <stdio.h>

int main()
{
    FILE *ficheiro;
    int numero;

    ficheiro = fopen("Impressao_vec.txt", "r");

    if (ficheiro != NULL)
    {
        printf("Números lidos do arquivo:\n");

        while (fscanf(ficheiro, "%d", &numero) == 1)
        {
            printf("%d\n", numero);
        }

        fclose(ficheiro);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
    }

    return 0;
}


