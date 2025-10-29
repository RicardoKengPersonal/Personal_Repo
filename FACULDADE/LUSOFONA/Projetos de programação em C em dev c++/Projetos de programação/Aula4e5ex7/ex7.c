#include <stdio.h>

int main ()
{

    int num,graus,opcao,kelvin;
    float Farenheit;

    kelvin = 0;
    Farenheit = 0;

    printf("Conversao de graus celsius.\n");
    printf("Insira 1 para Kelvin, 2 para Farenheit: ");
    scanf("%d",&opcao);
    printf("Insira o numero de graus celsius: ");
    scanf("%d",&graus);



    switch(opcao)
    {
        case 1 :
            printf("\nConversao para Kelvin.\n");

            kelvin = graus + 273;

            printf("\n%d graus celsius sao %d graus kelvin.\n\n",graus,kelvin);
            break;

        case 2 :
            printf("\nConversao para Farenheit.\n");


            Farenheit = (( 9 / 5.0 ) * graus ) + 32;

            printf("\n%d graus celsius sao %.3f graus kelvin.\n\n",graus,Farenheit);
            break;

        default :
            printf("\nO numero inserido nao e valido.");

    }
    return 0;
}
