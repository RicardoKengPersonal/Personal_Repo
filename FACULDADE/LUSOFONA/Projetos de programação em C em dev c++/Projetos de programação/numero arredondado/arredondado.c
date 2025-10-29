#include <stdio.h>
#include <math.h>

int main() {
  float num = 3.333;
  float num_2 = 1.6666;
  float conta, arredondado;

  printf("\t\t| Numero arredondado | .");

  conta = num * num_2;
  arredondado = roundf(conta * 100) / 100.0; // alterado aqui

  printf("\nConta: %.2f", conta);
  printf("\nNumero arredondado: %.2f\n", arredondado);

  return 0;
}

