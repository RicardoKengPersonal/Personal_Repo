#include <stdio.h>
int main()
{
 int i,j,n,m,total;
 // dimensao da matriz
 printf("Numero de linhas: ");
 scanf("%d",&n);
 printf("Numero de colunas: ");
 scanf("%d",&m);
 int mat[n][m];
 // ler a matriz
 printf("\nInserir os elementos da  matriz\n");
 for (i = 0; i < n; i++)
 {
 for (j = 0; j < m; j++)
 {
 printf("Mat[%d][%d] = ",i+1, j+1);
 scanf("%d", &mat[i][j]);
 }
 }
 printf("\n\nMatriz \n");
 for (i = 0; i < n; i++)
 {
 for (j = 0; j < m; j++)
 {
 printf("%d\t", mat[i][j]);
 }
 printf("\n");
 }
 total = 0;
 for (i = 0; i < n; i++)
 
 total = total + mat[i][i];
 printf("\nsoma dos elementos da matriz: %d", total);
 return 0;

}
