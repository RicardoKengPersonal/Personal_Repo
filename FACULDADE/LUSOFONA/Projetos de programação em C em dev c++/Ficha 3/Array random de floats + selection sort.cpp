#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define RANGE 750



void selection_sort(int v[],int size);


int main()

{
 int i,k,rows,val,cont=0;
 float max,min;
 // dimensao da matriz
 printf("\nNumero de linhas: ");
 scanf("%d",&rows);

 float a[rows];
 int mat[rows];


 srand(time(NULL));
 // geracao de elementos aleatorios (random) para a matriz
 printf("\nGeracao aleatoria (random) dos elementos da matriz\n");
 for (i = 1; i <= rows; i++)

 a[i] = ((float)rand()/RAND_MAX)*(float)(RANGE);

// ver matriz (floats)
 printf("\n\nMatriz de valores aleatorios (random): \n");
 for (i = 1; i <= rows; i++)
 {

 printf("Elemento %d:  %.2f\t",i, a[i]);
 printf("\n");
 }
 //ordenação por selection sort
     for ( i = 0; i < rows-1; i++ )
	{
    	int min = i ;
    	for ( int j = i+1; j < rows; j++)
		{
			if (a[j] < a[min] ) 
			min = j;
		}
		if (min != i)
		{
			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;
			
		}
	}
	
	for (int i=1; i <= rows; i++)
	{
		printf("\na[%d]= %.2f",i,a[i]);
	}
	
	// linear search
	
	int linearSearch(int v[], int size, int x);
	{
		int i;
		for ( i = 0 ; i < rows ; i++)
		if ( a[i] == x) 
		return i;
		return -1;
	}
					
	
	int sumArray(int v[], int size)
	{
		int i,soma;
		for ( i = 0; i < size;i++)
		{
			soma = soma v[i];
			return soma;
		}
	
				


   //maximo e minimo

    max = a[1];
    min = a[1];

 
   
    for(i=1; i<=rows; i++)
    {
      
        if(a[i] > max)
        {
            max = a[i];
           
        }

   
        if(a[i] < min)
        {
            min = a[i];
          
        }
    }


    printf("\n\nMaximo = %.2f\n", max);
    printf("\nMinimo = %.2f", min);
    
    return 0;

}







