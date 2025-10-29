#include <stdio.h>

void ler_Dim_Matriz(int *col, int *lin)
{
    printf("Numero de colunas: ");
    scanf("%d", col);

    printf("Numero de linhas: ");
    scanf("%d", lin);
}

void LerMatriz(int lin, int col, int mat[lin][col])
{
    for (int i = 0; i < lin; i++)
    {
        for (int j = 0; j < col; j++)
        {
            printf("Mat[%d][%d] = ", i, j);
            scanf("%d", &mat[i][j]);
        }
    }
}

void trocarLinhas(int col, int mat[][col])
{
    int temp;

    for (int j = 0; j < col; j++)
    {
        temp = mat[0][j];
        mat[0][j] = mat[mat.length - 1][j];
        mat[mat.length - 1][j] = temp;
    }
}

int main()
{
    int col, lin;

    ler_Dim_Matriz(&col, &lin);

    int mat[lin][col];

    LerMatriz(lin, col, mat);

    printf("\nMatriz original:\n");
    for (int i = 0; i < lin; i++)
    {
        for (int j = 0; j < col; j++)
        {
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }

    trocarLinhas(col, mat);

    printf("\nMatriz com as linhas trocadas:\n");
    for (int i = 0; i < lin; i++)
    {
        for (int j = 0; j < col; j++)
        {
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }

    return 0;
}



