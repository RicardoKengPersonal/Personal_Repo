#include<stdio.h>
int find_factorial(int);
int main()
{
   int num, fact;
 
   printf("\nInserir qualquer numero inteiro: ");
   scanf("%d",&num);
 
   //Calling our user defined function
   fact =find_factorial(num);
 
   //Displaying factorial of input number
   printf("\nfactorial de %d e: %d",num, fact);
   return 0;
}
int find_factorial(int n)
{
   //Factorial of 0 is 1 
   if(n==0)
      return(1);
 
   //Function calling itself: recursion
   return(n*find_factorial(n-1));
}
