%{
#include "bison.tab.h"
#include <string.h>
#include <stdlib.h>

void yyerror(const char *s);
%}

COD_FORMADOR   [a-z]{3}[0-9]{2}
COD_CURSO      [A-Z]{3}[0-9]{2}
NOME_FORMADOR  \"[^\"]+\"
NOME_CURSO     \"[^\"]+\"
ANO_CURRICULAR [1-9]
ALUNOS_INSCRITOS [1-9][0-9]*
CARGA_HORARIA  ([1-9]|1[0-9])

%%

{COD_FORMADOR}    { yylval.string = strdup(yytext); return COD_FORMADOR; }
{COD_CURSO}       { yylval.string = strdup(yytext); return COD_CURSO; }
{NOME_FORMADOR}   {
                    yylval.string = strdup(yytext + 1);
                    yylval.string[strlen(yylval.string) - 1] = '\0';
                    return NOME_FORMADOR;
                  }
{NOME_CURSO}      {
                    yylval.string = strdup(yytext + 1);
                    yylval.string[strlen(yylval.string) - 1] = '\0';
                    return NOME_CURSO;
                  }
{ANO_CURRICULAR}  { yylval.inteiro = atoi(yytext); return ANO_CURRICULAR; }
{ALUNOS_INSCRITOS} { yylval.inteiro = atoi(yytext); return ALUNOS_INSCRITOS; }
{CARGA_HORARIA}   { yylval.inteiro = atoi(yytext); return CARGA_HORARIA; }

[ \t]+           { /* ignora espaços e tabs */ }
\n               { yylineno++; return FIM_LINHA; }

.                { yyerror("Erro léxico"); return ERRO; }

%%

int yywrap() {
    return 1;
}
