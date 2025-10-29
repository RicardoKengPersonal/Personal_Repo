#include <stdio.h>
#include <stdlib.h>

int main(void)
{
   int sz;
   printf("Insira o tamanho do array:");
   scanf("%d",&sz);
   int randArray[sz],i;
   for(i=0;i<sz;i++)
     randArray[i]=rand()%100;   
   printf("\nElementos do array:");
   for(i=0;i<sz;i++)
   {
     printf("\nElemento numero %d:%d",i+1,randArray[i]);
   }
   return 0;
       
        
  
}
