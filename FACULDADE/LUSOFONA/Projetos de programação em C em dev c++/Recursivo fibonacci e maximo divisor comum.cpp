#include <stdio.h>
#include <stdlib.h>
long long int factorial(int n);
long long int rfactorial(int n);
int mdc_iterativo1(int a, int b);
int mdc_iterativo2(int a, int b);
int mdc_recursivo1(int a, int b);
int mdc_recursivo2(int a, int b);
long long int rfibonacci(int n);
long long int ifibonacci(int n);
int main()
{
 int i, num1,num2;
 // factorial de um numero inteiro e numeros primos (intervalo)
 printf("Inserir numero inteiro (limite inferior): ");
 scanf("%d",&num1);
 printf("\nInserir numero inteiro (limite superior): ");
 scanf("%d",&num2);

// factorial
 printf("\nFactorial(%d) = %lld", num1, factorial(num1));
 printf("\nFactorial(%d) = %lld", num1, rfactorial(num1));

 // máximo divisor comum
printf("\n\nMax divisor comum (mdc iterativo1) entre %d e %d = %d",
num1, num2, mdc_iterativo1(num1,num2));
printf("\n\nMax divisor comum (mdc iterativo2) entre %d e %d = %d",
num1, num2, mdc_iterativo2(num1,num2));
printf("\n\nMax divisor comum (mdc recursivo) entre %d e %d = %d",
num1, num2, mdc_recursivo2(num1,num2));
printf("\n\nMax divisor comum (mdc recursivo - algoritmo de Euclides)entre %d e %d = %d", num1, num2, mdc_recursivo1(num1,num2));


printf("\n\n Fibonacci numbers (iterative) between %d e %d:", num1,
num2);
for (i = num1; i<=num2; i++)
printf("\t%lld", ifibonacci(i));
 printf("\n\n Fibonacci numbers (recursive) between %d e %d:", num1, num2);
 for (i = num1; i<=num2; i++)
printf("\t%lld", rfibonacci(i));
 return 0;
}
// funcao factorial (iterativo)
long long int factorial(int num)
{
 int i;
 unsigned long long int fact;
 fact = 1;
 for (i = 2; i <= num; i++)
 fact *= i;
 return (fact);
}
// funcao factorial (recursivo)
long long int rfactorial(int n)
{
 if (n == 0) return 1;
 return(n * rfactorial(n-1));
}
int mdc_iterativo1(int a, int b)
{
 int resto;
 while (b != 0)
 {
 resto = a % b;
 a = b;
 b = resto;
 }
 return a;
}
int mdc_iterativo2(int n1, int n2)
{
 int min, maxdiv, flag, r1, r2;
 if (n1 < n2)
 min = n1;
 else
 min = n2;
 maxdiv = min;
 flag = 1;
 while (flag == 1)
 {
 r1 = n1 % maxdiv;
 r2 = n2 % maxdiv;
 if (r1 == 0 && r2 == 0)
 flag = 0;
 else
 maxdiv = maxdiv - 1;
}
 return(maxdiv);
}
// Euclides algorithm
int mdc_recursivo1(int x, int y)
{
if (y == 0) // caso base
return x;
 else
 return mdc_recursivo1(y, x % y); // passo recursivo
}
int mdc_recursivo2(int x, int y)
{
 if (x < y)
 return mdc_recursivo2(y, x);
 else
{
 if (x % y == 0)
 return y;
 else
 return mdc_recursivo2(y, x % y);
 }
}
long long int rfibonacci(int n)
{
if (n == 0)
 return 0;
if (n == 1)
 return 1;
 return (rfibonacci(n-1) + rfibonacci(n-2));
}
long long int ifibonacci(int n)
{
int i;
long long int fib1 = 1, fib2 = 1, fsum;
for (i = 3; i <= n; i++)
{
fsum = fib1 + fib2;
fib1 = fib2;
fib2 = fsum;
 }
return fib2;
}
