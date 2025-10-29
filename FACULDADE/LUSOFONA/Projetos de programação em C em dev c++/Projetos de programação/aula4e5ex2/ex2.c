#include <stdio.h>



int main ()
{

    int peso;
    float altura,imc;

    imc = 0;


    printf("\t\t | Calculadora de IMC |");

    do
    {
        printf("\nInsira o peso :  ");
        scanf("%d",&peso);

    } while ( peso <= 0 );


    do
    {
        printf("\nInsira a altura em metros: ");
        scanf("%f",&altura);

    } while ( altura <= 0 || altura >= 3.00  );

    imc = peso / (altura*altura);

    if ( imc <= 19 )
    {
        printf("\nIMC de %.2f",imc);
        printf("\nPeso abaixo do normal.\n");

        return 0;
    }
     if ( imc > 19 && imc <= 25 )
    {
        printf("\nIMC de %.2f",imc);
        printf("\nPeso normal.\n");

        return 0;
    }
     if ( imc > 25 && imc <= 30 )
    {
        printf("\nIMC de %.2f",imc);
        printf("\nExcesso de peso.\n");

        return 0;
    }
    if ( imc > 30 )
    {
        printf("\nIMC de %.2f",imc);
        printf("\nObesidade.\n");

        return 0;
    }

}
