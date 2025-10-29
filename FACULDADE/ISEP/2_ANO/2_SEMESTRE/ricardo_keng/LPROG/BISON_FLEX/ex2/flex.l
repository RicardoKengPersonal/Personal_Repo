%{
    #include "bison.tab.h"
%}

ALGARISMO [0-9]
IG =
MA >
ME <
ME_IGUAL <=
MA_IGUAL >=
DIFF <>

%%

{ALGARISMO} {yylval=atoi(yytext);return INT;}
{IG} {return IGUAL;}
{MA} {return MAIOR;}
{ME} {return MENOR;}
{MA_IGUAL} {return MAIOR_IGUAL;}
{ME_IGUAL} {return MENOR_IGUAL;}
{DIFF} {return DIFERENTE;}

[ \t]+   {}
\n       { return '\n'; }

%%

int yywrap()
{
    return 1;
}