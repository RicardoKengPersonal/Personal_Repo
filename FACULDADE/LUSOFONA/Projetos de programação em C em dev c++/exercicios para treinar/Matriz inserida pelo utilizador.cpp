#include <stdio.h>

int main ()
{
	
	int linhas,colunas,i,j;
	printf("Numero de linhas: ");
	scanf("%d",&linhas);
	printf("Numero de colunas: ");
	scanf("%d",&colunas);
	
	int mat[i][j];
	
	printf("\nInserir valores da matriz: ");
	
	for (i=0;i<linhas;i++)
	{
		for (j=0; j < colunas ;j++)
		{
			printf("Mat [%d][%d] : ",i,j);
			scanf("%d", &mat[i][j]);
			
		}
	}
	printf("\n\nMatriz\n");
	for (i=0;i<linhas;i++)
	{
		for(j=0; j < colunas ;j++)
		{
			printf("%d\t",mat[i][j]);
		}
		printf("\n");
	}
}
