%{
#include <stdio.h>
%}

%%

FEUP 	{	printf("ISEP");	}
2007 	{	printf("2008");	}
.|\n	{ 	printf("%s", yytext); } 

%%

int main() {
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}
