%{
#include "bison.tab.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
%}
%option noinput
%option nounput
%option noyywrap
%option header-file="lex.yy.h"
%%
"jogo"[0-9]+ return JOGO;
[A-Z][A-Z][A-Z] { yylval.str = strdup(yytext); return SELECAO; }
[A-Za-z]{1,20} { yylval.str = strdup(yytext); return JOGADOR; }
[0-9]+ { yylval.inteiro = atoi(yytext); return MINUTO; }
";" return yytext[0];
"," return yytext[0];
\n return yytext[0];
[ \t] ;
. printf("Erro %s\n", yytext);
%% 