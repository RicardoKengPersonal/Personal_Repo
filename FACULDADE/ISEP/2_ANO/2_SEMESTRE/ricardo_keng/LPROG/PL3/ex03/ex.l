%{
#include <stdio.h>
#include <string.h>

char *linha;
%}

%%

// Valida padrões completos de matrícula por linha inteira
^[A-Za-z]{2}-[0-9]{2}-[A-Za-z]{2}$    { printf("Matricula valida\n"); }
^[0-9]{2}-[A-Za-z]{2}-[A-Za-z]{2}$    { printf("Matricula valida\n"); }

// Tudo o resto: linha inválida
.*                                    { printf("Matricula invalida\n"); }

%%

int yywrap() {
    return 1;
}

int main() {
    char buffer[256];

    while (fgets(buffer, sizeof(buffer), stdin)) {
        YY_BUFFER_STATE bp = yy_scan_string(buffer);
        yylex();
        yy_delete_buffer(bp);
    }

    return 0;
}
