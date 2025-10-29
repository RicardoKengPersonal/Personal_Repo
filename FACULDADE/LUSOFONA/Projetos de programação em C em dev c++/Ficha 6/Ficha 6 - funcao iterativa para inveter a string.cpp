#include <stdio.h>
#include <string.h>

int main() {
    char input[100];
    printf("Insira uma string: ");
    fgets(input, 100, stdin);
    int len = strlen(input);

    for (int i = len - 1; i >= 0; i--) {
        printf("%c", input[i]);
    }

    return 0;
}

