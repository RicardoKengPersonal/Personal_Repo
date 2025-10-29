#include <stdio.h>
#include <math.h>

typedef struct ponto
{
    float x;
    float y;

}Coord_Pontos;

void LeituraPontos(float *x, float *y)
{
    printf("\nCoordenada x : ");
    scanf("%f",x);

    printf("\nCoordenada y: ");
    scanf("%f",y);

}

float Dist_Pontos (float x1,float y1,float x2,float y2)
{
    float distancia;

    distancia =  sqrt(pow((x1-x2),2)+pow((y1-y2),2));

    return distancia;
}

int main ()
{

    Coord_Pontos p1;
    Coord_Pontos p2;

    float dist;

    printf("Valores para o ponto 1: ");

    LeituraPontos(&p1.x,&p1.y);

    printf("\nValores lidos para ponto 1: (%.2f , %.2f)",p1.x,p1.y);

    printf("\n\nValores para o ponto 2: ");

    LeituraPontos(&p2.x,&p2.y);

    printf("\nValores lidos para ponto 2: (%.2f , %.2f)",p2.x,p2.y);

    dist = Dist_Pontos(p1.x,p1.y,p2.x,p2.y);

    printf("\nDistancia entre os pontos: %.2f",dist);

}
