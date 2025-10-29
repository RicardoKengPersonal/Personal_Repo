#include <stdio.h>

float maiorMenor (float *ma)
{

    float mn,num;
    char inicial;

    inicial = 1;

    while (1)
    {
        printf("Digite um numeror real (0-termina) : ");
        scanf("%f",&num);

        if (inicial)
        {
            mn = *ma = num;
            inicial = 0;

        }

        if(num == 0.0f)
        {
            break;
        }

        if (*ma < num )
        {

            *ma = num;
        } else if ( mn > num)
        {
            mn = num;
        }

        return mn;
    }

}


main ()
{

    float maior,menor;
    menor = maiorMenor (&maior);
    printf("\nMaior valor lido %.2f",maior);
    printf("\nMenor valor lido %.2f",menor);
    return 0;
}
