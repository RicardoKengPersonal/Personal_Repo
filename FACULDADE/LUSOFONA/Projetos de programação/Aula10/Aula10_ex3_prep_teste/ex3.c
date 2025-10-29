#include <stdio.h>

typedef struct aluno
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
    aluno_tipo aluno1;

    preencherStruct(&aluno1);

    printf("Nome: %s\n", aluno1.nome);
    printf("Apelido: %s\n", aluno1.apelido);
    printf("Idade: %d\n", aluno1.idade);

    return 0;
}


