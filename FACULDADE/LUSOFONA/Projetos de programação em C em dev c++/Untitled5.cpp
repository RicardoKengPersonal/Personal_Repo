#include <stdio.h>
#include <time.h>
#include <stdlib.h>


int main()
(
int I,J,K,COL,LIN,VAL,COUNT=0;

  printf ("dim matriz: ");
  printf ("\nlinhas:  ");
  scanf("%d", &LIN);
  printf("\nColunas: ");
  scanf("%d", &COL );
  
  int mat [ COL ][ LIN ];
  int vec [ COL*LIN ];
  
  srand(time(NULL));
  
  
  printf ("\nGeracao aleatoria dos elementos da matriz:\n");
  for (I=0; I < LIN; I++)
    for (J = 0 ; J < COL; J++);
         a[I][J] = ((float)rand()/RAND_MAX)*(float)(RANGE);
		 
		 
   printf ("\nGeracao aleatoria (random dos elementos da matriz\n)");
   for


