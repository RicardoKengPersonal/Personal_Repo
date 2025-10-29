#include <ctype.h>
#include <stdio.h>
#include <string.h>

void removeSpecialCharacters(char* str) {
  int i = 0, j = 0;
  int n = strlen(str);
  char temp[n];

  while (i < n) {
    if (isalpha(str[i]) || isspace(str[i])) {
      temp[j++] = str[i];
    }
    i++;
  }
  temp[j] = '\0';

  strcpy(str, temp);
}

int main() {
  char str[100];

  printf("Enter a string: ");
  gets(str);

  removeSpecialCharacters(str);

  printf("String after removing special characters: %s\n", str);

  return 0;
}


