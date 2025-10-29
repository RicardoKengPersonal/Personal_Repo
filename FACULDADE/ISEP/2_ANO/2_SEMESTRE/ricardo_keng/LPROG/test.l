%{
#include "example.tab.h"
%}

%%
[0-9]+    { yylval = atoi(yytext); return NUMBER; }
[\n]      { return EOL; }
.         { return yytext[0]; }
%%

int yywrap() {
    return 1;
}
