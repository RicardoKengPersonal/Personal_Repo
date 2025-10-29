#include <stdio.h>

int main ()
{

    FILE *ficheiro;

    ficheiro = fopen("Teste_abertura_leitura.txt","w");

    if (ficheiro != NULL)
    {
        fprintf(ficheiro,"12345");
        printf("Ficheiro criado com sucesso.");

    }else
    {
        printf("Erro a criar ficheiro.");
    }

    fclose(ficheiro);


    FILE *ficheiro_input;

    ficheiro_input = fopen("Teste_abertura_leitura.txt","r");

    int num;

    if (ficheiro_input != NULL)
    {
        fscanf(ficheiro_input,"%d",&num);
        printf("\n%d",num);
        printf("\n\nFicheiro lido com sucesso.");

    }else
    {
        printf("Erro a ler ficheiro.");
    }

    fclose(ficheiro_input);

}
