%{
#include <stdio.h>
#include <stdlib.h>
int yylex(void);
void yyerror(const char *s);
int yyparse(void);

%}

%token INT
%token IGUAL MENOR MAIOR MENORIG MAIORIG DIFERENTE    

%%

start:
	/* vazio */
	| start expr '\n'
	;
	
expr:
	INT op INT {
		int result = 0;
		switch($2){
			case 1: result = ($1 == $3); break;
			case 2: result = ($1 < $3); break;
			case 3: result = ($1 > $3); break;
			case 4: result = ($1 <= $3); break;
			case 5: result = ($1 >= $3); break;
			case 6: result = ($1 != $3); break;
		}
		printf("%s\n", result ? "verdadeiro" : "falso");
	}
	;
	
op:
	IGUAL     	{ $$ = 1; }
	| MENOR 	{ $$ = 2; }
	| MAIOR     { $$ = 3; }
	| MENORIG   { $$ = 4; }
	| MAIORIG   { $$ = 5; }
	| DIFERENTE { $$ = 6; }
	;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Erro: %s\n", s);
}

int main(void){
	return yyparse();
}
