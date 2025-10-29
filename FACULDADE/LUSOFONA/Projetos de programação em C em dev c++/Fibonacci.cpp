#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int main ()
{

     int f;
     printf("Inserir numero inteiro:  ");
     scanf("%d", &f );
     time_t tini, tfim;
     
     {
	 
     tini = time(NULL);
     printf("fibonacci iterativo: %lld\n", ifibonacci(f));
     tfim = time(NULL);
     
     printf("\tTempo (segundos): %f\n\n ", difftime(tfim, tini));
     
     tini = time(NULL);
     printf(" fibonacci recursivo: %lld\n", rfibonacci(f));
     tfim = time(NULL)
     printf("\tTempo (segundos): %f\n\n",difftime(tfim, tini));
     }
     return 0;
}
