#include <stdio.h>
void Base10toBin(long num);
int main()
{
int number[64];
int n, i, rev = 0, soma = 0, resto, orig, numdig, nbin=0;
 printf("Enter a positive integer: ");
 scanf("%d", &n);
 orig = n;
 while (n != 0)
{
 resto = n % 10;
 rev = rev * 10 + resto;
 n = n / 10;
 // array of digits
 number[i] = resto;
 i++;
 }
// number palindrome? (capicua)
 if (orig == rev)
 printf("%d is a number palindrome (capicua)", orig);
 else
 printf("%d is not a number palindrome", orig);

 // sum the digits
 numdig = i-1;
 for (i=0; i<=numdig; i++)
 soma = soma + number[i];

 printf("\n\nSum the digits of %d: %d", orig, soma);

 Base10toBin(orig);

 return 0;
}
void Base10toBin(long num)
{
int number[64];
int i=0, numdig, n;
n = num;
// convert to base 2
while (num != 0)
{
number[i] = num % 2;
num = num / 2;
i++;
}
 numdig = i-1;
printf("\n\n%d converted to binary base = ",n);
for(i=numdig; i>=0; i--)
printf("%d",number[i]);
}
