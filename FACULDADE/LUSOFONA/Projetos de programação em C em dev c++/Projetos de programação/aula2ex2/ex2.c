#include <stdio.h>
#include <math.h>


int leitura(int num)
{
    printf("Calculo do volume da esfera.");
    printf("\nInsira o raio da esfera: ");
    scanf("%d",&num);
    return num;

}

float calcularArea(float raio)
{
    return 3.14 * pow(raio, 2);
}


float calcularVolume(float raio)
{
    return (4.0 / 3.0) * 3.14 * pow(raio, 3);
}


int main ()
{
    int raio;

    float area,volume;

    raio = leitura(raio);

    area = calcularArea(raio);
    volume = calcularVolume(raio);

    printf("A area da esfera e : %.2f \nO volume da esfera com o mesmo raio e : %.2f",area,volume);

    return 0;


}
