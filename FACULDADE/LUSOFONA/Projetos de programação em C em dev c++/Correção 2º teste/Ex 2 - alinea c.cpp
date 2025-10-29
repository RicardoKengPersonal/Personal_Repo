#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MIN_DIM 3
#define MAX_DIM 10
#define NUM_CHARS 26

char randomChar() {
  int r = rand() % NUM_CHARS;
  return (char) (r + 'A');
}

int main() {
  srand(time(NULL));
  int dim = rand() % (MAX_DIM - MIN_DIM + 1) + MIN_DIM;
  char matrix[dim][dim];
  for (int i = 0; i < dim; i++) {
    for (int j = 0; j < dim; j++) {
      matrix[i][j] = randomChar();
    }
  }
  int strLen = 2 * dim - 1;
  char str[strLen + 1];
  int strIndex = 0;
  for (int i = 0; i < dim; i++) {
    str[strIndex++] = matrix[i][i];
    str[strIndex++] = matrix[i][dim - i - 1];
  }
  str[strIndex] = '\0';
  printf("String com os caracteres da primeira e segunda diagonal:\n%s\n", str);
  return 0;
}

