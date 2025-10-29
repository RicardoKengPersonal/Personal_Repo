%{
    #include <stdio.h>
    int nums = 0;
%}

NUMS [-?0-9]+

%%

{NUMS} {nums++;}

.|\n {}

%%

int main()
{
    yylex();

    printf("Numeros introduzidos: %d\n",nums);

    return 0;
}

int yywrap()
{
    return 1;
}
