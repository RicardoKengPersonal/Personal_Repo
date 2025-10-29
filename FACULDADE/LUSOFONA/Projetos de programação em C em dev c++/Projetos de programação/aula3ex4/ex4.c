#include <stdio.h>


int main ()
{
    int x,m,b,y;

    printf("Calculadora de equacoes lineares.");
    printf("\t\t\t|y = m * x + b|\nInsira o valor de x: ");
    scanf("%d",&x);
    printf("\t\t\t|y = m * x + b|\nInsira o valor de m: ");
    scanf("%d",&m);
    printf("\t\t\t|y = m * x + b|\nInsira o valor de b: ");
    scanf("%d",&b);

    y = m * x + b ;

    printf("\nO valor de y e : %d\n.",y);

    return 0;

}
