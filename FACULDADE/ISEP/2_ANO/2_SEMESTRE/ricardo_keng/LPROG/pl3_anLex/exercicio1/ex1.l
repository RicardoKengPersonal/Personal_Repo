%{
#include <stdio.h>

int count = 0;  // Variável para contar as ocorrências
char *procura = "abc";  // A cadeia de caracteres que estamos procurando

%}

%%

"abc" {  // Sempre que encontrar "abc", incrementa o contador
    count++;
}

%%

int main() {
    yylex();  // Processa a entrada e conta as ocorrências
    printf("A cadeia '%s' apareceu %d vez(es).\n", procura, count);
    return 0;
}
