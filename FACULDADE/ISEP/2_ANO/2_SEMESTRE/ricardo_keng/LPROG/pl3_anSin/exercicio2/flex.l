%{
#include "bison.tab.h"
%}

%%

[0-9]+ 		{ yylval = atoi(yytext); return INT; }
"="         { return IGUAL; }
"<>"        { return DIFERENTE; }
"<="        { return MENORIG; }
">="        { return MAIORIG; }
"<"         { return MENOR; }
">"         { return MAIOR; }
[\t ]+      ;
\n          { return '\n'; }
.           { return -1; }

%%

int yywrap() { return 1; }
