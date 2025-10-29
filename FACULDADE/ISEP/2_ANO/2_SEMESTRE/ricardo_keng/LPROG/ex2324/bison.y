%{
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void yyerror(const char *s);
int yylex();

typedef struct JogadorGol {
    char nome[100];
    int golos;
    struct JogadorGol *next;
} JogadorGol;

typedef struct SelecaoGol {
    char nome[100];
    int total_golos;
    JogadorGol *jogadores;
    struct SelecaoGol *next;
} SelecaoGol;

SelecaoGol *selecoes = NULL;
SelecaoGol *ultima_selecao = NULL;

int nerros = 0;

JogadorGol *procura_ou_cria_jogador(SelecaoGol *sel, const char *nome) {
    JogadorGol *j = sel->jogadores;
    while (j) {
        if (strcmp(j->nome, nome) == 0) return j;
        j = j->next;
    }
    JogadorGol *novo = malloc(sizeof(JogadorGol));
    strcpy(novo->nome, nome);
    novo->golos = 0;
    novo->next = sel->jogadores;
    sel->jogadores = novo;
    return novo;
}

SelecaoGol *cria_selecao(const char *nome) {
    SelecaoGol *sel = malloc(sizeof(SelecaoGol));
    strcpy(sel->nome, nome);
    sel->total_golos = 0;
    sel->jogadores = NULL;
    sel->next = NULL;
    return sel;
}

void adiciona_selecao(const char *nome) {
    SelecaoGol *sel = cria_selecao(nome);
    if (!selecoes) {
        selecoes = sel;
        ultima_selecao = sel;
    } else {
        ultima_selecao->next = sel;
        ultima_selecao = sel;
    }
}

void yyerror(const char *s) {
    fprintf(stderr, "Erro: %s\n", s);
    nerros++;
}
%}

%union {
    char str[100];
    int num;
}

%token <str> SELECAO JOGADOR JOGO
%token <num> MINUTO

%type <str> nome_selecao nome_jogador jogo
%type <num> minuto lista_golos golos_jogo jogador_golos

%%

entrada:
    lista_selecoes
;

lista_selecoes:
      /* vazio */
    | lista_selecoes selecao
;

selecao:
    SELECAO linha_jogadores {
        adiciona_selecao($1);
    }
;

linha_jogadores:
    /* vazio */
    | linha_jogadores jogador_golos
;

jogador_golos:
    JOGADOR lista_golos '\n' {
        if (!ultima_selecao) {
            yyerror("Jogador sem seleção");
            YYABORT;
        }
        JogadorGol *jg = procura_ou_cria_jogador(ultima_selecao, $1);
        jg->golos += $2;
        ultima_selecao->total_golos += $2;
        $$ = $2; // número de golos deste jogador
    }
;

lista_golos:
      /* vazio */ { $$ = 0; }
    | lista_golos ';' golos_jogo { $$ = $1 + $3; }
;

golos_jogo:
    JOGO ',' MINUTO { $$ = 1; }
;

%%

int main() {
    if (yyparse() == 0 && nerros == 0) {
        printf("## EURO 2024\n");
        printf("## Sistema de Informação LPROG\n");

        SelecaoGol *sel = selecoes;
        int max_golos = -1;
        char selecao_mais_golos[100];
        strcpy(selecao_mais_golos, "");

        JogadorGol *top_jogador = NULL;
        int max_golos_jogador = -1;

        while (sel) {
            printf("Seleção: %s\n", sel->nome);
            JogadorGol *jog = sel->jogadores;
            if (jog) {
                while (jog) {
                    printf(" ** Jogador: %s = %d golos\n", jog->nome, jog->golos);
                    if (jog->golos > max_golos_jogador) {
                        max_golos_jogador = jog->golos;
                        top_jogador = jog;
                    }
                    jog = jog->next;
                }
            }
            printf("Total de golos marcados pela seleção de %s = %d golos\n", sel->nome, sel->total_golos);
            if (sel->total_golos > max_golos) {
                max_golos = sel->total_golos;
                strcpy(selecao_mais_golos, sel->nome);
            }
            sel = sel->next;
        }

        printf("A seleção com o maior número de golos marcados foi %s\n", selecao_mais_golos);
        if (top_jogador)
            printf("O jogador com mais golos marcados foi %s\n", top_jogador->nome);
    } else {
        printf("Ficheiro inválido. Erros: %d\n", nerros);
    }
    return 0;
}
