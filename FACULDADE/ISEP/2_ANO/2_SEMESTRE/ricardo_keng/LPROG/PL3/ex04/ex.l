%{
#include <stdio.h>
int numAlgarismos = 0;
int numLetras = 0;
int nLinhas = 0; 
int nTabs = 0;
int noID = 0;
%}

ALGARISMOS [0-9]

LETRAS [A-Za-z]

%%

{ALGARISMOS} {numAlgarismos++;}
{LETRAS} {numLetras++;}
\n {nLinhas++;}
[ \t] {nTabs++;}
. {noID++;}

%%

int yywrap()
{
    return 1;
}

int main()
{
    yylex();
    printf("Numero de algarismos : %d\n",numAlgarismos);
    printf("Numero de Letras: %d\n",numLetras);
    printf("Numero de linhas: %d\n",nLinhas);
    printf("Numero de tabs: %d\n",nTabs);
    printf("Caracteres ano identificados: %d\n",noID);

    return 0;
}


