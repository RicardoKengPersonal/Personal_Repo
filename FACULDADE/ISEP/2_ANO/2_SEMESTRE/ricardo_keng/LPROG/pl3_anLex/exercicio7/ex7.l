%{
#include <stdio.h>
%}

%%

[-+]?[0-9]+"."[0-9]+    { printf("%s ", yytext); }
[ \t\n]        
.              

%%

int main() {
    yylex();
    printf("\n");
    return 0;
}
