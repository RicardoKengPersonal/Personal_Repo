%{
#include <stdio.h>
int yylex(void);
void yyerror(const char *s);
int yyparse(void);

%}

%token HELLO WORLD

%%

start: HELLO WORLD { printf("Hello World!!!\n"); };

%%

void yyerror(const char *s) {
    fprintf(stderr, "Erro: %s\n", s);
}

int main(void){
	return yyparse();
}
