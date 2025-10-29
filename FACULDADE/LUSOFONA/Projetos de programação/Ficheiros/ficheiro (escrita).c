#include <stdio.h>

int main()
{
    FILE *ficheiro;
    char nome[20];
    int num;



    ficheiro = fopen("string.txt", "r");

    if (ficheiro != NULL)
    {
        fscanf(ficheiro, "%s , %d", nome, &num);
        printf("%s, %d\n", nome, num);

    }
    else
    {
        printf("Erro a abrir ficheiro.\n");
    }
    fclose(ficheiro);

    return 0;
}
