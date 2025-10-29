#include <stdio.h>

typedef struct aluno
{
    char nome[20];
    char apelido[20];
    int idade;

}aluno_tipo;

int main ()
{
    int n;

    printf("Numero de alunos: ");
    scanf("%d",&n);

    aluno_tipo aluno[n];

    for(int i = 0; i < n; i++)
    {
        printf("\nNome do %do aluno: ",i+1);
        scanf("%s",aluno[i].nome);

        printf("\nApelido do %do aluno: ",i+1);
        scanf("%s",aluno[i].apelido);

        printf("\nIdade do %do aluno: ",i+1);
        scanf("%d",&aluno[i].idade);
    }

    for (int i = 0; i < n; i++)
    {
        printf("\n---------------");
        printf("\n%do Aluno : %s,%s,%d",i+1,aluno[i].nome,aluno[i].apelido,aluno[i].idade);
    }


}
