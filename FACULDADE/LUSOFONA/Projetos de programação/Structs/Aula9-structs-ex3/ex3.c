#include <stdio.h>

typedef struct aluno
{
    char nome[20];
    char apelido[20];
    int idade;

} aluno_tipo;

void preencherStruct(aluno_tipo *aluno, int n)
{
    for (int i = 0; i < n; i++)
    {
        printf("\nNome do %do aluno: ", i + 1);
        scanf("%s", aluno[i].nome);

        printf("\nApelido do %do aluno: ", i + 1);
        scanf("%s", aluno[i].apelido);

        printf("\nIdade do %do aluno: ", i + 1);
        scanf("%d", &aluno[i].idade);
    }
}

void mostrarStruct (aluno_tipo *aluno,int n)
{

    for (int i = 0; i < n ; i++)
    {

        printf("\n-----------------------");
        printf("\nNome %do aluno: %s", i + 1, aluno[i].nome);
        printf("\nApelido %do aluno: %s", i + 1, aluno[i].apelido);
        printf("\nIdade %do aluno: %d", i + 1, aluno[i].idade);

    }

}

float mediaIdades(aluno_tipo *aluno, int n)
{

    float media = 0;
    int soma = 0;

    for(int i = 0; i < n; i++)
    {
        soma = soma + aluno[i].idade;
    }

    media = (soma/(float)n);

    return media;
}

int main()
{
    int n;

    float media;

    printf("Numero de alunos: ");
    scanf("%d", &n);

    aluno_tipo aluno[n];

    preencherStruct(aluno, n);

    mostrarStruct(aluno,n);

    media = mediaIdades(aluno,n);

    printf("\n\nA media de idades e: %.2f",media);


    return 0;
}
