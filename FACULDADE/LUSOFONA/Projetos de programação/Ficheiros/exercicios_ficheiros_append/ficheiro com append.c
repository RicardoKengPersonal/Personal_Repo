#include <stdio.h>

int main()
{
    FILE *ficheiro;
    int valor;

    ficheiro = fopen("Impressao_vec.txt", "r");

    if (ficheiro != NULL)
    {
        printf("Valores lidos do arquivo:\n");

        while (fscanf(ficheiro, "vec[%*d] = %d", &valor) == 1)
        {
            printf("%d\n", valor);
        }

        fclose(ficheiro);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
    }

    return 0;
}
