%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void yyerror(const char *s);
extern int yylineno;

typedef struct {
    char cod_curso[10];
    char nome[100];
    int ano;
    int alunos;
    int carga;
} Curso;

typedef struct {
    char cod_formador[10];
    char nome[100];
    Curso cursos[100];
    int total_cursos;
    int total_alunos;
    int maior_index;
} Formador;

Formador formadores[100];
int total_formadores = 0;
int erro_detectado = 0;

Formador* formador_atual = NULL;

void registrar_formador(char* cod, char* nome) {
    formador_atual = &formadores[total_formadores++];
    strcpy(formador_atual->cod_formador, cod);
    if (nome) strcpy(formador_atual->nome, nome);
    else strcpy(formador_atual->nome, "");
    formador_atual->total_cursos = 0;
    formador_atual->total_alunos = 0;
    formador_atual->maior_index = -1;
}

void registrar_curso(char* cod, char* nome, int ano, int alunos, int carga) {
    if (formador_atual == NULL) return;
    Curso* c = &formador_atual->cursos[formador_atual->total_cursos++];
    strcpy(c->cod_curso, cod);
    if (nome) strcpy(c->nome, nome);
    else c->nome[0] = '\0';
    c->ano = ano;
    c->alunos = alunos;
    c->carga = carga;

    formador_atual->total_alunos += alunos;
    if (formador_atual->maior_index == -1 || alunos > formador_atual->cursos[formador_atual->maior_index].alunos) {
        formador_atual->maior_index = formador_atual->total_cursos - 1;
    }
}

void imprimir_resultados() {
    for (int i = 0; i < total_formadores; i++) {
        Formador* f = &formadores[i];
        if (f->maior_index != -1) {
            Curso* c = &f->cursos[f->maior_index];
            printf("%s %s %d aluno(s)\n", f->cod_formador, c->cod_curso, c->alunos);
            printf("Total: %d aluno(s)\n", f->total_alunos);
        } else {
            printf("Erro: registo incompleto\n");
        }
    }
}

%}

%union {
    int inteiro;
    char* string;
}

%token <string> COD_FORMADOR NOME_FORMADOR COD_CURSO NOME_CURSO
%token <inteiro> ANO_CURRICULAR ALUNOS_INSCRITOS CARGA_HORARIA
%token FIM_LINHA ERRO

%type <string> nome_formador_opt nome_curso_opt

%start ficheiro

%%

ficheiro:
    lista_formadores
;

lista_formadores:
    /* vazio */
  | lista_formadores formador
;

formador:
    COD_FORMADOR nome_formador_opt FIM_LINHA lista_cursos
    {
        registrar_formador($1, $2);
        free($1);
        if ($2) free($2);
    }
;

nome_formador_opt:
    /* vazio */ { $$ = NULL; }
  | NOME_FORMADOR { $$ = $1; }
;

lista_cursos:
    /* vazio */
  | lista_cursos curso
;

curso:
    COD_CURSO nome_curso_opt ANO_CURRICULAR ALUNOS_INSCRITOS CARGA_HORARIA FIM_LINHA
    {
        registrar_curso($1, $2, $3, $4, $5);
        free($1);
        if ($2) free($2);
    }
  | COD_CURSO ANO_CURRICULAR ALUNOS_INSCRITOS CARGA_HORARIA FIM_LINHA
    {
        registrar_curso($1, NULL, $2, $3, $4);
        free($1);
    }
  | error FIM_LINHA
    {
        yyerror("Erro: registo incompleto");
        yyerrok;
    }
;

nome_curso_opt:
    /* vazio */ { $$ = NULL; }
  | NOME_CURSO { $$ = $1; }
;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Linha %d: %s\n", yylineno, s);
    erro_detectado = 1;
}

int main() {
    if (yyparse() == 0 && !erro_detectado) {
        imprimir_resultados();
        return 0;
    } else {
        fprintf(stderr, "Foram encontrados erros no ficheiro.\n");
        return 1;
    }
}
