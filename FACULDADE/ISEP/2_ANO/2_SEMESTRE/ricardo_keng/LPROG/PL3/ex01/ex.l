%{
#include <stdio.h>
int count = 0;
%}

SEQ    [aA][bB][cC]

%%

{SEQ}   {
    count++;
    printf("Encontrado: %s\n", yytext);
}

.|\n    ;  // Ignorar outros caracteres

%%

int main() {
    yylex();
    printf("Total de ocorrencias da cadeia 'abc': %d\n", count);
    return 0;
}
