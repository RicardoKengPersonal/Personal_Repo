# include <stdio.h>
int main()
{
 int i,num;

 int fact = 1;

 printf("Inserir numero inteiro: ");
 scanf("%d",&num);

 // factorial de numero inteiro iterativo
 fact = 1;
 for (i = 1; i <= num; i++)
 fact *= i;
 printf("\nFactorial(%d) = %d", num, fact);
 
}


