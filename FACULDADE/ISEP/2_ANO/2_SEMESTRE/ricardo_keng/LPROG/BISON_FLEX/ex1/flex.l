%{
    #include "bison.tab.h"
%}

HELLO [hH][eE][lL][lL][oO]
WORLD [wW][oO][rR][lL][dD]

%%

{HELLO} {return HELLO;}
{WORLD} {return WORLD;}

.|\n    {}

%%

int yywrap()
{
    return 1;
}