#include <stdio.h>

typedef struct fracao
{
    int num, den;

} Fracao;

Fracao somarFracao(Fracao f1, Fracao f2)
{
    Fracao resultado;
    resultado.num = (f1.num * f2.den) + (f2.num * f1.den);
    resultado.den = f1.den * f2.den;
    return resultado;
}

Fracao subtrairFracao(Fracao f1, Fracao f2)
{
    Fracao resultado;
    resultado.num = (f1.num * f2.den) - (f2.num * f1.den);
    resultado.den = f1.den * f2.den;
    return resultado;
}

Fracao multiplicarFracao(Fracao f1, Fracao f2)
{
    Fracao resultado;
    resultado.num = f1.num * f2.num;
    resultado.den = f1.den * f2.den;
    return resultado;
}

Fracao dividirFracao(Fracao f1, Fracao f2)
{
    Fracao resultado;
    resultado.num = f1.num * f2.den;
    resultado.den = f1.den * f2.num;
    return resultado;
}

int main()
{
    Fracao f1, f2;
    Fracao resultado;

    // Ler as frações
    printf("Digite a primeira fracao (num/den): ");
    scanf("%d/%d", &f1.num, &f1.den);

    printf("Digite a segunda fracao (num/den): ");
    scanf("%d/%d", &f2.num, &f2.den);

    // Somar as frações
    resultado = somarFracao(f1, f2);
    printf("Soma das fracoes: %d/%d\n", resultado.num, resultado.den);

    // Subtrair as frações
    resultado = subtrairFracao(f1, f2);
    printf("Subtracao das fracoes: %d/%d\n", resultado.num, resultado.den);

    // Multiplicar as frações
    resultado = multiplicarFracao(f1, f2);
    printf("Multiplicacao das fracoes: %d/%d\n", resultado.num, resultado.den);

    // Dividir as frações
    resultado = dividirFracao(f1, f2);
    printf("Divisao das fracoes: %d/%d\n", resultado.num, resultado.den);

    return 0;
}
