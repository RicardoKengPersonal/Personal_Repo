#include <stdio.h>
#include <math.h>

typedef struct
{
    float x;
    float y;
} Ponto;

void LerCoordenadas(float *x, float *y)
{
    printf("Coordenada X: ");
    scanf("%f", x);

    printf("Coordenada Y: ");
    scanf("%f", y);
}

float DistPontos(float x1, float y1, float x2, float y2)
{
    return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
}

int main()
{
    Ponto p1, p2;
    float dist;

    printf("Ponto 1:\n");
    LerCoordenadas(&p1.x, &p1.y);

    printf("\nPonto 2:\n");
    LerCoordenadas(&p2.x, &p2.y);

    dist = DistPontos(p1.x, p1.y, p2.x, p2.y);

    printf("\nA distancia entre os pontos e: %.2f\n", dist);

    return 0;
}

