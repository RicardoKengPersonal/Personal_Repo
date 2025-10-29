%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void yyerror(const char *s);
int yylex();

int nerros = 0;
int maisDownloads = 0;
char tituloPopular[100];
%}

%union {
    char str[100];
    int num;
}

%token <str> TITULO ORIENTADOR TYPE ACCESS

%token <num> ANO_PUB DOWNLOADS

%token COMMA

%type <num> orientadores coma_outra_orient acesso_opt
%type <str> autor

%%

entrada:
      /* vazio */
    | entrada linha
;

linha:
    TITULO autor orientadores ANO_PUB TYPE acesso_opt DOWNLOADS optional_newlines
    {
        printf("%s - %s (%d) - %s - Downloads:%d\n",
               $1, $2, $4, $5, $7);

        if ($7 > maisDownloads) {
            maisDownloads = $7;
            strncpy(tituloPopular, $1, sizeof(tituloPopular)-1);
            tituloPopular[sizeof(tituloPopular)-1] = '\0';
        }
    }
;

autor:
    ORIENTADOR { strcpy($$, $1); }
;

optional_newlines:
    | optional_newlines '\n'
;

orientadores:
      ORIENTADOR { $$ = 1; }
    | ORIENTADOR coma_outra_orient { $$ = 1 + $2; }
;

coma_outra_orient:
      COMMA ORIENTADOR { $$ = 1; }
    | COMMA ORIENTADOR coma_outra_orient { $$ = 1 + $3; }
;

acesso_opt: { $$ = 0; }
    | ACCESS { $$ = 1; }
;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Erro sintático: %s\n", s);
    nerros++;
}

int main()
{
    yyparse();
    if (nerros == 0) {
        printf("Tese com maior nº de downloads: %s - %d\n", tituloPopular,maisDownloads);
    } else {
        printf("Ficheiro invalido\nNumero de erros = %d\n", nerros);
    }
    return 0;
}
