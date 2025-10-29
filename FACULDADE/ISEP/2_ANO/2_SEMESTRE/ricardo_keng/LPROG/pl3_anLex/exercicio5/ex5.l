%{
#include <stdio.h>
%}

%%

[0-9]+		{printf("%s ", yytext);}
.|\n

%%

int main() {
    yylex();
    printf("\n");
    return 0;
}

int yywrap() {
    return 1;
}
