%{
#include "bison.tab.h"
#include <string.h>
#include <stdlib.h>
extern int nerros;
%}

%option noyywrap

TITLE \"[A-Za-z0-9 ]{2,80}\"
NAME_ORIENTADOR \#[A-Za-z_ ]+
PUB_DATE (198[0-9]|199[0-9]|200[0-9]|201[0-9]|202[0-5])
TIPO (MESTRADO|DOUTORAMENTO)
ACESSO (ABERTO|FECHADO|RESTRITO)
NUMERO [0-9]+

%%

{TITLE} {
    // Remove aspas das strings entre aspas
    int len = yyleng;
    if (len > 2) {
        strncpy(yylval.str, yytext+1, len-2);
        yylval.str[len-2] = '\0';
    } else {
        yylval.str[0] = '\0';
    }
    return TITULO;
}

{NAME_ORIENTADOR} {
    strncpy(yylval.str, yytext+1, sizeof(yylval.str)-1);
    yylval.str[sizeof(yylval.str)-1] = '\0';
    return ORIENTADOR; 
}

{PUB_DATE} {
    yylval.num = atoi(yytext);
    return ANO_PUB;
}

{TIPO} {
    strncpy(yylval.str, yytext, sizeof(yylval.str)-1);
    yylval.str[sizeof(yylval.str)-1] = '\0';
    return TYPE;
}

{ACESSO} {
    strncpy(yylval.str, yytext, sizeof(yylval.str)-1);
    yylval.str[sizeof(yylval.str)-1] = '\0';
    return ACCESS;
}

{NUMERO} {
    yylval.num = atoi(yytext);
    return DOWNLOADS;
}

"," {
    return COMMA;
}

[ \t]+   ;

\n       ;

. {
    fprintf(stderr, "Erro léxico: Caractere inválido: %s\n", yytext);
    nerros++;
}

%%
