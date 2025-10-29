#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void countAlphaDigitChars(char text[]);
void sortTextDesc(char text[]);
void sortTextAsc(char text[]);
int main()
{
char str[100],texto[1000],ch,temp;
int i,j,min,n,cont=0;
printf("String: ");
// read a string using getchar
i=0;
while((str[i] = getchar()) != '\n')
 i++;
 str[i] = '\0';

 printf("\nTexto: \n%s %d", str, strlen(str));
printf("\nTexto: ");
// read a string using gets
gets(texto);
// char count
printf("Caracter a pesquisar: ");
scanf("%c",&ch);
for (j=0; texto[j] != '\0'; j++)
if (ch == texto[j])
cont++;
printf("O caracter %c ocorre %d vezes",ch,cont);
// sort text (chars) ascending
sortTextAsc(texto);
// sort text (chars) descending
sortTextDesc(texto);
// count chars (letters and digits)
countAlphaDigitChars(texto);
return 0;
}
void sortTextAsc(char text[])
{
// selection sort
int n,min,i,j;
char temp;
n = strlen(text);
for (i = 0; i < n-1; i++)
 {
 min = i;
 for (j = i + 1; j < n; j++)
 {
 if (text[j] < text[min])
 min = j;
 }

 // swap
 if (min != i)
 {
 temp = text[i];
 text[i] = text[min];
 text[min] = temp;
 }
 }

 printf("\nSorted ascendent (array of chars): ");
 puts(text);

 // print sorted string
 printf("\n2 Sorted string (array of chars): ");
for (i = 0; i < n; i++)
printf("%c",text[i]);
}
void sortTextDesc(char text[])
{
// selection sort
int n,min,i,j;
char temp;
n = strlen(text);
for (i = 0; i < n-1; i++)
 {
 min = i;
 for (j = i + 1; j < n; j++)
 {
 if (text[j] > text[min])
 min = j;
 }

 // swap
 if (min != i)
 {
 temp = text[i];
 text[i] = text[min];
 text[min] = temp;
 }
 }

 printf("\nSorted descendent (array of chars): ");
 puts(text);
 
}
