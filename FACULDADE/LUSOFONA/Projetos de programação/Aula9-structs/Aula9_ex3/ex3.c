#include <stdlib.h>
#include <stdio.h>

typedef struct disciplina
{
    char nome[11];
    int nota;
    int faltas;

}disciplina_t;

void preencher_disciplina(disciplina_t *disciplina_p)
{
    printf("Nome Disciplina: "); scanf(" %s", (*disciplina_p).nome);
    printf("Nota Disciplina: "); scanf("%d", &(*disciplina_p).nota);
    printf("Faltas Disciplina: "); scanf("%d", &(*disciplina_p).faltas);
}

typedef struct aluno
{
    char nome[20];
    char apelido[20];
    int idade;
    disciplina_t disciplina;

}aluno_t;

aluno_t preencher_struct2()
{
    aluno_t aux;

    printf("Insira o Nome: "); scanf(" %s", aux.nome);
    printf("Insira o Aplido: "); scanf(" %s", aux.apelido);
    printf("Insira a Idade: "); scanf("%d", &aux.idade);


    preencher_disciplina(&aux.disciplina);

    return aux;
}

void preencher_struct(aluno_t *aluno_p)
{
    printf("\nInsira o nome: "); scanf(" %s",(*aluno_p).nome);
    printf("Insira o apelido: "); scanf(" %s", (*aluno_p).apelido);
    printf("Insira a idade: "); scanf("%d", &(*aluno_p).idade);


    preencher_disciplina(&aluno_p->disciplina);

}

void mostrar_struct(aluno_t aluno)
{
    printf("Nome: %s %s (%d anos) [%d valores em %s]\n", aluno.nome, aluno.apelido, aluno.idade, aluno.disciplina.nota, aluno.disciplina.nome);
}

void preencher_vetor_alunos(aluno_t alunos[], int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("Insira dados de aluno %d\n",i+1);
        preencher_struct(&alunos[i]);
    }

}

float calcula_media_idades(aluno_t alunos[], int size)
{
    int soma = 0;
    float media;

    for (int i = 0; i < size; i++)
    {
        soma += alunos[i].idade;
    }

    media = soma / (float)size;

    return media;
}

int main()
{


    aluno_t alunos[3];
    preencher_vetor_alunos(alunos, 3);

    for (int i = 0; i < 3; i++)
    {
        printf("Aluno %d:",i+1);
        mostrar_struct(alunos[i]);
    }

    printf("Media de idades: %.2f\n", calcula_media_idades(alunos, 3));

}
