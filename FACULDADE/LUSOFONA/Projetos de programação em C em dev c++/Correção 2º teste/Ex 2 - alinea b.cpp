#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MIN_DIM 2
#define MAX_DIM 10
#define NUM_CHARS 62

char randomChar() {
  int r = rand() % NUM_CHARS;
  if (r < 10) {
    return '0' + r;
  } else if (r < 36) {
    return 'A' + r - 10;
  } else {
    return 'a' + r - 36;
  }
}

int main() {
  srand(time(NULL));

  int n = MIN_DIM + rand() % (MAX_DIM - MIN_DIM + 1);
  char matrix[n][n];

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      matrix[i][j] = randomChar();
    }
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      printf("%c ", matrix[i][j]);
    }
    printf("\n");
  }

  return 0;
}

