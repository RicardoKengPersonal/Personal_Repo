#include <stdio.h>

int main()
{
    FILE *ficheiro_strings_input;
    FILE *ficheiro_strings_output;

    char palavra[20] = "Teste";
    char palavra1[20];

    ficheiro_strings_output = fopen("Strings_leitura.txt", "r");

    if (ficheiro_strings_output != NULL)
    {
        fscanf(ficheiro_strings_output, "%s", palavra1);
        printf("%s\n", palavra1);
        printf("Ficheiro lido com sucesso.\n");
        fclose(ficheiro_strings_output);
    }
    else
    {
        printf("Erro ao ler ficheiro.\n");
    }

    return 0;
}

