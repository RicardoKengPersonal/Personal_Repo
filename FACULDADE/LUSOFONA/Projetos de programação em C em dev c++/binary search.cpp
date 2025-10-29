#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main ()
(
      int i,j,k,col,lin,val,cont=0;
      
      printf("dim matriz:  ");
      printf("\nLinhas:  ");
      scanf("%d", &lin);
      printf("\nColunas:  ");
      scanf("%d", %col);
      float a[ lin ][ col ];
      int mat [lin][col];
      int vec [lin*col];
      
      srand (time(NULL));
      
      
      printf("\nGeracao aleatoria (random) dos elementos da matriz\n:  ");
      for(i=0; i<lin; i++)
        for (j=0;j<col;j++)
            mat[i][j] = ((float)rand()/RAND_MAX)*(float)(RANGE);
            
            
         printf("\n\nMatriz de valores aleatorios (random):  \n");
		 for (i=0; i<lin;i++)
		 (
		       for (j=0;j<col;j++)
			        printf("%.2f\t", a[i][j]);
				printf ("\n");
					   
	  )
	  
	     
	  
