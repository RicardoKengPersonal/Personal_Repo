%{
#include <stdio.h>
%}

%%

[-+]?[0-9]+    { printf("%s ", yytext); }  // Captura números inteiros (com ou sem sinal)
[0-9]+"."[0-9]+
[ \t\n]        
.              

%%

int main() {
    yylex();
    printf("\n");
    return 0;
}
