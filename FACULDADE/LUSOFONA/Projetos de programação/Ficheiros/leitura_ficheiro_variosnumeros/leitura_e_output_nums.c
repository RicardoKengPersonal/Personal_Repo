#include <stdio.h>

int main()
{
    FILE *ficheiro;
    int num;

    ficheiro = fopen("Numeros.txt", "r");

    if (ficheiro != NULL)
    {
        while (fscanf(ficheiro, "%d", &num) == 1)
        {
            printf("%d\n", num);
        }

        fclose(ficheiro);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
    }

    return 0;
}

