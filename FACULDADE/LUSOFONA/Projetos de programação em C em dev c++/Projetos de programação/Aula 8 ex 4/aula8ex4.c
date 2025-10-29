#include <stdio.h>
#include <stdlib.h>



void ler_matriz_4x2 ( int m[1][2] )
{
    for (int i = 0 ; i < 4 ; i++)
    {

        for (int j = 0; j < 2 ; j++ )
        {

            printf(" Insira o elemento %d,%d",i+1,j+1);
            scanf("%d",&m[i][j]);
        }
    }

}
void imprimir_matriz_4x2 ( int m[1][2] )
{
    for (int i = 0 ; i < 4 ; i++)
    {

        for (int j = 0; j < 2 ; j++ )
        {

            printf(" %3d",m[i][j]);

        }
        printf("\n");
    }

    return;
}

int main ()
{

    int matriz [4][2];
    int matriz [2][4];
    int vec[4];

    ler_matriz_4x2



}
