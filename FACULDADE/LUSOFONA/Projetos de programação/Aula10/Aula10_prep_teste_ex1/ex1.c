#include <stdio.h>

int main()
{
    int tabela[10] = {15, 2678, 323, 17, 3, 18, 12, 1, 2, 10};

    FILE *ficheiro;

    int maximo = 10;

    ficheiro = fopen("teste.txt", "w");

    if (ficheiro != NULL)
    {
        for (int i = 0; i < maximo; i++)
        {
            fprintf(ficheiro, "Linha %d/%d - Valor: %d\n", i+1, maximo, tabela[i]);
        }
        fclose(ficheiro);

        printf("Arquivo criado com sucesso.\n");
    }
    else
    {
        printf("Erro ao criar o arquivo.\n");
    }

    return 0;
}
