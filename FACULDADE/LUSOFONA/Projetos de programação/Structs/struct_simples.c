#include <stdio.h>

struct funcionario
{
    char nome[25];
    int idade;

};

int main ()
{
   struct funcionario func[3];

   int i = 0;

   for ( i = 0; i < 3; i++)
   {
       printf("\nNome do funcionario %d: ",i+1);
       scanf("%s",&func[i].nome);

       printf("\nIdade do funcionario %d: ",i+1);
       scanf("%d",&func[i].idade);

   }

   for ( i = 0; i < 3 ; i++)
   {
       printf("\n------------------");
       printf("\nNome do funcionario %d: %s",i+1,func[i].nome);
       printf("\nIdade do funcionario %d: %d",i+1,func[i].idade);
   }

   return 0;


}
