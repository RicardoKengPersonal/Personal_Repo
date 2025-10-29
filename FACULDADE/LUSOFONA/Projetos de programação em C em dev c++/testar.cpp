#include <stdio.h>
int main()
{
 int i,val,total,n;
 float media;
 printf("Numero de elementos: ");
 scanf("%d",&n);
 total = 0;
 // ciclo (loop) for
 printf("\nFor loop: \n");
 for (i=0; i < n; i++)
 {
 printf("\ninserir %d valor: ", i+1);
 scanf("%d",&val);
 total = total + val;
 printf("%d\n",total);
 }
 media = (float)total/n;
 printf("\nTotal: %d",total);
 printf("\nMedia: %f",media);
 printf("\nMedia: %.2f",media);

 total = 0;
 // ciclo (loop) while
 printf("\nWhile loop: \n");
 i=0;
 while (i < n)
 {
 printf("\ninserir %d valor: ", i+1);
 scanf("%d",&val);
 total = total + val;
 i++;
 }
 media = (float)total/n;
 printf("\nTotal: %d",total);
 printf("\nMedia: %f",media);
 printf("\nMedia: %.2f",media);

 return 0;
}
