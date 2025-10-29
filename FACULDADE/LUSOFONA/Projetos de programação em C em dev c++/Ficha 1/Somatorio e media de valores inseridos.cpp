#include <stdio.h>


int main ()
{
	int i,n,val,total;
	float media;
	printf("Insira o numero de elementos: ");
	scanf("%d", &n);
	int array[n];
	total = 0;
	for (i = 0; i < n; i++) {
	
	printf("Insira o %d valor: ", i+1 );
	scanf("%d", &val);
	total = total + val;	
 }
 media = total / n ;
 printf("o Total e %d: ", total);
 printf ("\nA media e %f: ", media);
}
