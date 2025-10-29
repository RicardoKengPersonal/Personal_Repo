#include <stdio.h>

int main()
{
    FILE *ficheiro;
    char linha[100];
    int numero;

    ficheiro = fopen("Impressao_vec.txt", "r");

    if (ficheiro != NULL)
    {
        while (fgets(linha, sizeof(linha), ficheiro) != NULL)
        {
            sscanf(linha, "%d", &numero);
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

