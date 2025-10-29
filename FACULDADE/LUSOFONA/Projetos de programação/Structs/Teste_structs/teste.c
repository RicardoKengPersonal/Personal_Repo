#include <stdio.h>

typedef struct aluno
{
    char nome[20];
    char apelido[20];
    int idade;

}aluno_t;

void preencher_vetor_alunos(aluno_t alunos[], int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("Insira dados de aluno %d\n", i + 1);
        preencher_struct(&alunos[i]);
    }
}

void mostrar_struct(aluno_t aluno)
{
    printf("Nome: %s %s (%d anos)\n", aluno.nome, aluno.apelido, aluno.idade);
}

void preencher_struct(aluno_t *aluno_p)
{
    printf("Insira o nome: ");
    scanf("%s", aluno_p->nome);

    printf("Insira o apelido: ");
    scanf("%s", aluno_p->apelido);

    printf("Insira a idade: ");
    scanf("%d", &aluno_p->idade);
}

int main()
{
    int n;

    printf("Numero de alunos: ");
    scanf("%d",&n);

    aluno_t alunos[n];

    preencher_vetor_alunos(alunos, n);

    for (int i = 0; i < n; i++)
    {
        printf("Aluno %d:\n", i + 1);
        mostrar_struct(alunos[i]);
    }

    return 0;
}

