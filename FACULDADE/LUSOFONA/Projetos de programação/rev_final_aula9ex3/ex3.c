#include <stdio.h>

typedef struct _aluno
{
    char nome[20];
    char apelido[20];
    int idade;

}aluno_tipo;

void preencherStruct (aluno_tipo *aluno,int n)
{
    for (int i = 0; i < n; i++)
    {
        printf("Nome %do aluno: ",i+1);
        scanf("%s",&aluno[i].nome);

        printf("Apelido %do aluno: ",i+1);
        scanf("%s",&aluno[i].apelido);

        printf("Idade %do aluno: ",i+1);
        scanf("%d",&aluno[i].idade);

    }
}

void MostrarStruct ( aluno_tipo *aluno,int n)
{
    for (int i = 0 ; i < n; i++)
    {
        printf("\n------------------");
        printf("\n%s , %s (%d)",aluno[i].nome,aluno[i].apelido,aluno[i].idade);
    }
}

int main ()
{
    int n;

    printf("Numero de alunos: ");
    scanf("%d",&n);

    aluno_tipo aluno[n];

    preencherStruct(aluno,n);


    printf("\n Dados dos alunos: ");

    MostrarStruct(aluno,n);
}
