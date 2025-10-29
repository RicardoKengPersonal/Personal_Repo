#include <stdio.h>


int main ()
{

    int num_a_descobrir,num_inserido;

    num_a_descobrir = 7;

    do
    {

        printf("\nTenta descobrir o numero de 0 a 10: ");
        scanf("%d",&num_inserido);

            if ( num_inserido < num_a_descobrir )
            {

                printf("\nNumero maior que esse.\n");

            } else if ( num_inserido > num_a_descobrir )
                {

                    printf("\nNumero menor que esse.\n");

                }

    } while ( num_a_descobrir != num_inserido );

        printf("\nAcertaste no numero!");

        return 0;


}
