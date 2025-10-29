%{
#include <stdio.h>
int numAlgarismos=0;
int numLetras=0;
int numLinhas=0;
int numEspTab=0;
int numCarOutros=0;
%}

%%

[0-9]		{numAlgarismos++;}
[a-zA-Z] 	{numLetras++;}
\n 			{numLinhas++;}
[ \t] 		{numEspTab++;}
.			{numCarOutros++;}

%%

int main() {
    yylex();
    printf("Número de algarismos: %d\n", numAlgarismos);
    printf("Número de letras: %d\n", numLetras);
    printf("Número de linhas: %d\n", numLinhas);
    printf("Número de espaços e/ou tabs: %d\n", numEspTab);
    printf("Número de outros caracteres: %d\n", numCarOutros);
    return 0;
}

int yywrap() {
    return 1;
}
