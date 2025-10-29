#include <stdio.h>

int main ()
{

    FILE *ficheiro;

     int num;
    char nome[20];

    printf("Nome: ");
    scanf("%s",nome);

    printf("Idade: ");
    scanf("%d",&num);

    ficheiro = fopen("prep_teste.txt","w");

    if (ficheiro != NULL)
    {
        fprintf(ficheiro,"%s,%d",nome,num);
        printf("Ficheiro criado com sucesso.");
    }else
    {
        printf("Erro a criar ficheiro.");

    }

    fclose(ficheiro);
}
