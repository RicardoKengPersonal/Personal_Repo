%{
    #include <stdio.h>
    int numIntroDec = 0;
    int numInteiros = 0;
    int total = 0;
%}

ALGARISMO [0-9]
SINAL     -?
DEC       {SINAL}{ALGARISMO}+\.{ALGARISMO}+
INT       {SINAL}{ALGARISMO}+

%%

{DEC}      { numIntroDec++; }
{INT}      { numInteiros++; }

[ \n\t]+   { /* ignora espaços, tabs e linhas vazias */ }
.          { /* ignora qualquer outro caractere */ }

%%

int main()
{
    yylex();

    printf("Numeros introduzidos (decimais): %d\n", numIntroDec);
    printf("Numeros naturais (negativos e positivos): %d\n", numInteiros);

    total = numIntroDec + numInteiros;

    printf("Total de numeros introduzidos: %d\n", total);
    return 0;
}

int yywrap()
{
    return 1;
}
