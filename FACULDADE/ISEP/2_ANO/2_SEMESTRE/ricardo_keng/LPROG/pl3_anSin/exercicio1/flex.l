%{
#include "bison.tab.h"
%}

%%

hello   { return HELLO; }
world   { return WORLD; }
[ \t\n] ;
.       ;

%%

int yywrap() { return 1; }
