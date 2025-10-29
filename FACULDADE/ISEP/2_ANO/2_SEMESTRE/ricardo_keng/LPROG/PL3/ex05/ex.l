%{
    #include <stdio.h>
    int numNaturais = 0;
%}

%%

[0-9]+     { printf("%s ", yytext); numNaturais++; }   // número natural
[A-Za-z]+  { /* ignora palavras */ }
.|\n       { /* ignora espaços, pontos, etc. */ }

%%

int main()
{
    yylex();
    printf("\nNumero de Numeros Naturais: %d\n", numNaturais);
    return 0;
}

int yywrap()
{
    return 1;
}
