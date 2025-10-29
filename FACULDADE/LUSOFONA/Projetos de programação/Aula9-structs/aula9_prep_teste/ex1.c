#include <stdio.h>
#include <math.h>

void LerCoordenadas(float *px, float *py)
{
    printf("Coordenada x: ");
    scanf("%f", px);

    printf("Coordenada y: ");
    scanf("%f", py);
}

float DistCoordenadas(float x, float y, float x2, float y2)
{
    float dist;
    dist = sqrt(pow((x2 - x), 2) + pow((y2 - y), 2));
    return dist;
}

int main()
{
    float x, y, x2, y2;

    LerCoordenadas(&x, &y);

    printf("Coordenadas ponto 1: (%.2f, %.2f)\n", x, y);

    printf("Coordenadas ponto 2:\n");

    LerCoordenadas(&x2, &y2);

    printf("Coordenadas ponto 2: (%.2f, %.2f)\n", x2, y2);

    float dist = DistCoordenadas(x, y, x2, y2);

    printf("A distancia entre os pontos e: %.2f\n", dist);

    return 0;
}

