%{
#include <stdio.h>

/*
Matriculas válidas:
	1º caso: AA-00-AA
	2º caso: AA-00-00
	3º caso: 00-AA-00
	4º caso: 00-00-AA
*/
%}

%%

[a-zA-Z]{2}-[0-9]{2}-[a-zA-Z]{2} 	{printf("Valida: %s\n", yytext);}
[a-zA-Z]{2}-[0-9]{2}-[0-9]{2} 		{printf("Valida: %s\n", yytext);}
[0-9]{2}-[a-zA-Z]{2}-[0-9]{2}		{printf("Valida: %s\n", yytext);} 
[0-9]{2}-[0-9]{2}-[a-zA-Z]{2}		{printf("Valida: %s\n", yytext);}
.|\n

%%

int main() {
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}
