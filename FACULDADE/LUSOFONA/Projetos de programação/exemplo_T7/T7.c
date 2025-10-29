#include <stdio.h>
#include <stdlib.h>

struct funcionario
{
    char nome [25];
    int idade;

};

int main ()
{
    struct funcionario func[10];

    int i = 0;

    for (i = 0; i < 10; i++)
    {
        printf("\nNome do %do funcionario: ",i+1);
        scanf("%s",&func[i].nome);

        printf("idade do %do funcionario: ",i+1);
        scanf("%d",&func[i].idade);

    };

    for ( i = 0; i < 10; i++)
    {
        printf("-------------\n");
        printf("Nome do %do funcionario: %s",i+1,func[i].nome);
        printf("\nIdade do %uo funcionario : %d \n ", i+1,func[i].idade);
        printf("--------------------\n");
        printf("\n");

    };
    return 0;

}
