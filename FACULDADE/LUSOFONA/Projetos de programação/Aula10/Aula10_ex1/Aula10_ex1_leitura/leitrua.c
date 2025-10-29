#include <stdio.h>
#include <stdlib.h>

int main()
{
    // Ler ficheiro de texto
    char tabela2[10][100];
    int aux = 0;

    printf("Tabela 2:\n");

    FILE *ficheiro2;

    ficheiro2 = fopen("teste1.txt", "r");

    if (ficheiro2 != NULL)
    {
        while (fgets(tabela2[aux], sizeof(tabela2[aux]), ficheiro2) != NULL)
        {
            printf("%d: %s", aux + 1, tabela2[aux]);
            aux++;
        }
        fclose(ficheiro2);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    return 0;
}

