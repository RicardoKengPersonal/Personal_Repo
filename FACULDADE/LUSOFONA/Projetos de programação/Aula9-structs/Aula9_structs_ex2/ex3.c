#include <stdio.h>
#include <stdlib.h>

typedef struct _aluno
{
    char nome[20];
    char apelido[20];
    int idade;
} aluno_tipo;

void preencherStruct(aluno_tipo *aluno)
{
    printf("Nome: ");
    scanf("%s", aluno->nome);

    printf("Apelido: ");
    scanf("%s", aluno->apelido);

    printf("Idade: ");
    scanf("%d", &aluno->idade);
}

int main()
{
    int n;

    printf("Numero de alunos: ");
    scanf("%d", &n);

    aluno_tipo *aluno = malloc(n * sizeof(aluno_tipo));

    for (int i = 0; i < n; i++)
    {
        printf("\n-------------");
        printf("\n%do aluno:\n", i + 1);
        preencherStruct(&aluno[i]);
    }

    for (int i = 0; i < n; i++)
    {
        printf("\n-------------");
        printf("\n%do aluno:\n", i + 1);
        printf("Nome: %s\nApelido: %s\nIdade: %d\n", aluno[i].nome, aluno[i].apelido, aluno[i].idade);
    }

    free(aluno);

    return 0;
}

