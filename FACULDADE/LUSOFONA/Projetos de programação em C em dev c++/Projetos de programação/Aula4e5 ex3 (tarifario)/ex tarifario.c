#include <stdio.h>

int main ()
{

    int min,seg,tempo,conversao_min_seg;
    float tarifa;


    printf("Insira o numero de minutos: ");
    scanf("%d",&min);
    printf("Insira o numero de segundos: ");
    scanf("%d",&seg);

    conversao_min_seg = 0;
    conversao_min_seg = min * 60;

    printf("\nA conversao de minutos para segundos e : %d",conversao_min_seg);


    tempo = 0;
    tempo =  conversao_min_seg + seg;
    tarifa = 0;

    printf("\nO tempo total em segundos e: %d",tempo);

    if (tempo <= 60)
    {
        tarifa = tempo * 0.005;

    }
    if ( tempo > 60 && tempo <= 300 )
    {
        tarifa = 0.30 + (tempo - 60 ) * 0.0025;

    }
    if ( tempo > 300 )
    {
        tarifa = 0.645 + ( tempo - 300 ) * 0.0013;

    }
    if ( tempo > 1200 )
    {
        tarifa = 1.765 + ( tempo - 1200 ) * 0.0013;
        tarifa = tarifa * 0.9;

    }


    printf(" \nO valor final a pagar e: %.2f",tarifa);

    return 0;

}
