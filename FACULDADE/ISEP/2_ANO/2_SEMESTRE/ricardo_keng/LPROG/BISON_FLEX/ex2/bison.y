%{
    #include <stdio.h>
    #include <stdlib.h>
    void yyerror(const char* s);
    int yyparse();
    int yylex();
%}

%token INT
%token IGUAL
%token MAIOR
%token MENOR
%token MAIOR_IGUAL
%token MENOR_IGUAL
%token DIFERENTE

%%

start:
      expr '\n'
    | start expr '\n'
;


expr:
    INT op INT {
        int result = 0;
        switch($2)
        {
			case 1: 
                result = ($1 == $3); 
                break;
			case 2: 
                result = ($1 < $3); 
                break;
			case 3: 
                result = ($1 > $3); 
                break;
			case 4: 
                result = ($1 <= $3); 
                break;
			case 5: 
                result = ($1 >= $3); 
                break;
			case 6: 
                result = ($1 != $3); 
                break;
        }
        printf("%s\n",result ? "verdadeiro" : "falso");
    }
    ;

op:
	IGUAL     	{ $$ = 1; }
	| MENOR 	{ $$ = 2; }
	| MAIOR     { $$ = 3; }
	| MENOR_IGUAL   { $$ = 4; }
	| MAIOR_IGUAL   { $$ = 5; }
	| DIFERENTE { $$ = 6; }
	;

%%

void yyerror(const char* s)
{
    fprintf(stderr,"Error: %s\n",s);
}

int main()
{
    yyparse();

    return 0;
}