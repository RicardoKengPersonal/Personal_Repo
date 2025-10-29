#include <stdio.h>

int main()
{
 int i,num, fact = 1;
 
 printf("Inserir numero inteiro: ");
 scanf("%d",&num);

if (num < 2)
 printf("\n%d: nao primo",num);
 else if (num == 2)
 printf("\n%d: numero primo",num);
 else
 {
 i=2;
 while ((num % i != 0) && (i <= num/2))
 i++;
 if (num % i != 0)
 printf("\n%d: numero primo",num);
 else
 printf("\n%d: nao primo",num);
 }
 return 0;
}
