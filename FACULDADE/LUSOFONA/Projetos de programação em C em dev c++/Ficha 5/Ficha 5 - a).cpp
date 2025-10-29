#include <stdio.h>
#include <string.h>

int main() {
    char text[100], c;
    int i, count = 0;

    printf("Insira uma string: ");
    fgets(text, 100, stdin);
    printf("Insira um caracter para verificar a sua frequencia na string: ");
    scanf("%c", &c);

    for (i = 0; i < strlen(text); i++) {
        if (text[i] == c) {
            count++;
        }
    }

    printf("A frequencia de  %c na string dada e  %d.\n", c, count);

    return 0;
}

