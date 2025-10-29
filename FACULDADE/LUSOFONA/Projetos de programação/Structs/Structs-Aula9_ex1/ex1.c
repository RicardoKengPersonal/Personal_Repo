#include <stdio.h>
#include <math.h>


typedef struct ponto
{
    float x;
    float y;

}Pontos;

void lerCoordenadas(float *x, float *y)
{
    printf("\nCoordenada X: ");
    scanf("%f", x);

    printf("\nCoordenada Y: ");
    scanf("%f", y);
}

float DistPontos (float x1,float y1, float x2, float y2)
{
   return sqrt(pow(x2-x1,2)+pow(y2-y1,2));
}

int main()
{
    Pontos ponto1;
    Pontos ponto2;

    printf("\nCoordenadas 1: ");

    lerCoordenadas(&ponto1.x, &ponto1.y);

    printf("\nCoordenadas 2: ");

    lerCoordenadas(&ponto2.x, &ponto2.y);

    printf("\nCoordenadas ponto 1: (%.2f, %.2f)", ponto1.x, ponto1.y);
    printf("\nCoordenadas ponto 2: (%.2f, %.2f)", ponto2.x, ponto2.y);


    float dist = 0;

    dist = DistPontos(ponto1.x,ponto1.y,ponto2.x,ponto2.y);

    printf("\n\nDistancia entre pontos: %.2f",dist);

    return 0;
}

