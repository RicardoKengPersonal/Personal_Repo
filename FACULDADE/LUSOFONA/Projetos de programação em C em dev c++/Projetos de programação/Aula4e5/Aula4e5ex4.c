#include <stdio.h>


int main ()
{

    int a,b,c,interior_raiz;
    float formula_resolvente_pos,formula_resolvente_neg;


    printf("Calculadora de raizes quadradas.\n");
    printf("Valor de a : ");
    scanf("%d",&a);
    printf("Valor de b : ");
    scanf("%d",&b);
    printf("Valor de c : ");
    scanf("%d",&c);

    interior_raiz = ( b*b ) - 4 * a *c ;


    if ( interior_raiz <= 0)
    {
        printf("Nao existem raizes reais.");
        return 0;
    } else
    {
        formula_resolvente_pos = (-b+interior_raiz)/ ( 2 * a );
        formula_resolvente_neg = (-b-interior_raiz)/ ( 2 * a );

    }

    printf("\nRaizes dos valores %d,%d,%d = %.3f V %.3f",a,b,c,formula_resolvente_neg,formula_resolvente_pos);
    return 0;

}
