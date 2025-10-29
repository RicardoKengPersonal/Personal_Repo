%{
    #include <stdio.h>
    void yyerror();
    int yyparse();
    int yylex();
%}

%token HELLO 
%token WORLD

%% 

start: HELLO WORLD {printf("Hello World\n");};

%%

void yyerror(const char *s)
{
    fprintf(stderr,"Erro:%s\n",s);
}

int main()
{
    yyparse();
}