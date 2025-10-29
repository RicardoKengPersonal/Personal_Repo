#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define RANGE 750
void selection_sort(int v[],int size);
void bubble_sort(int array[],int n);
void bubble_sort2(int array[],int n);
int main()
{
 int i,j,k,rows,cols,val,cont=0;
 // dimensao da matriz
 printf("dim matriz: ");
 printf("\nlinhas: ");
 scanf("%d",&rows);
 printf("\ncolunas: ");
 scanf("%d",&cols);
 float a[rows][cols];
 int mat[rows][cols];
 int vec[rows*cols];

 srand(time(NULL));
 // geracao de elementos aleatorios (random) para a matriz
 printf("\nGeracao aleatoria (random) dos elementos da matriz\n");
 for (i = 0; i < rows; i++)
 for (j = 0; j < cols; j++)
 a[i][j] = ((float)rand()/RAND_MAX)*(float)(RANGE);

 // geracao de elementos aleatorios (random) para a matriz
 printf("\nGeracao aleatoria (random) dos elementos da matriz\n");
 for (i = 0; i < rows; i++)
 for (j = 0; j < cols; j++)
 mat[i][j] = ((float)rand()/RAND_MAX)*(float)(RANGE);
 // ver matriz (floats)
 printf("\n\nMatriz de valores aleatorios (random): \n");
 for (i = 0; i < rows; i++)
 {
 for (j = 0; j < cols; j++)
 printf("%.2f\t", a[i][j]);
 printf("\n");
 }

 // ver matriz (integers)
 printf("\n\nMatriz de valores aleatorios (random): \n");
 for (i = 0; i < rows; i++)
 {
 for (j = 0; j < cols; j++)
 printf("%d\t", mat[i][j]);
 printf("\n");
 }

 // search
 printf("\nInserir valor a pesquisar na matriz: ");
 scanf("%d",&val);

 for (i = 0; i < rows; i++)
{
for (j = 0; j < cols; j++)
{
if (mat[i][j]== val)
{
printf("\nValor %d na posicao [%d][%d]",val,i,j);
cont++;
}
}
}
if (cont == 0)
printf("\nValor %d nao existe na matriz",val);
// array com os elementos da matriz
k = 0;
for (i = 0; i < rows; i++)
 {
 for (j = 0; j < cols; j++)
 {
 vec[k] = mat[i][j];
 k++;
 }
 }

 printf("\n\nArray (vector):\n");
 for (i = 0; i < rows*cols; i++)
 printf("%d\t",vec[i]);
// call sort procedure
selection_sort(vec,rows*cols);
// call sort procedure
bubble_sort(vec,rows*cols);
bubble_sort2(vec,rows*cols);
return 0;
}
// selection sort procedure
void selection_sort(int v[], int n)
{
 int i, j, pos, val=0;
 float temp;
 for (i = 0; i < n-1; i++)
 {
 pos = i;
 for (j = i + 1; j < n; j++)
 {
 if (v[pos] > v[j])
 pos = j;
 }
 
 if (pos != i)
 {
 temp = v[i];
 v[i] = v[pos];
 v[pos] = temp;
 }
 }

 printf("\n\nArray (vector) ordenado (selection sort algorithm) por ordem crescente:\n");
 for (i = 0; i < n; i++)
 printf("%d\t", v[i]);
}
// bubble sort procedure
void bubble_sort(int array[],int n)
{
int i,j,temp;
for (i=0; i<n-1; i++)
 {
 for (j=0; j<n-i-1; j++)
 {
 // swap - for decreasing order use less than '<'
 // swap - for increasing order use greater than '>'
 if (array[j] < array[j+1])
 {
 temp = array[j];
 array[j] = array[j+1];
 array[j+1] = temp;
 }
 }
 }

 printf("\n\nArray (vector) ordenado (bubble sort algorithm) por ordem decrescente:\n");
 for (i = 0; i < n; i++)
 printf("%d\t", array[i]);
}
 
 
 
 
 
 
