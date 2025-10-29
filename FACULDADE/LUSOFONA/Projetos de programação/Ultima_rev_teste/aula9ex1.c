#include <stdio.h>
#include <math.h>

typedef struct Pontos
{
    float x;
    float y;

} Ponto_ref;

void LerCoordenadas(float *x, float *y)
{
    printf("\nCoordenada x: ");
    scanf("%f", x);

    printf("\nCoordenada y: ");
    scanf("%f", y);
}


float distancia_pontos(float x1 , float y1 ,float x2, float y2)
{
    float dist = 0;

    dist = sqrt(pow((x1-x2),2)+pow((y1-y2),2));

    return dist;
}
int main()
{
    Ponto_ref p1;
    Ponto_ref p2;

    float distancia = 0;

    printf("\nCoordenadas ponto1: ");

    LerCoordenadas(&p1.x, &p1.y);

    printf("\nCoordenadas ponto1: (%.2f, %.2f)", p1.x, p1.y);

    printf("\nCoordenadas ponto2: ");

    LerCoordenadas(&p2.x, &p2.y);

    printf("\nCoordenadas ponto2: (%.2f, %.2f)", p2.x, p2.y);

    distancia = distancia_pontos(p1.x,p1.y,p2.x,p2.y);

    printf("\nDistancia entre os pontos: %.2f",distancia);

    return 0;
}

