%{
#include <stdio.h>
#include <stdlib.h>

int yylex();         // <- add this
int yyerror(char *); // <- and this
%}

%token NUMBER
%token EOL

%%
input:
    /* empty */
    | input line
    ;

line:
    expr EOL  { printf("Result = %d\n>", $1); }
    ;

expr:
    NUMBER            { $$ = $1; }
    | expr '+' expr   { $$ = $1 + $3; }
    | expr '-' expr   { $$ = $1 - $3; }
    ;
%%

int main() {
    printf("Test expression(ctrl+d to exit):\n>");
    return yyparse();
}

int yyerror(char *s) {
    fprintf(stderr, "Error: %s\n", s);
    return 0;
}
