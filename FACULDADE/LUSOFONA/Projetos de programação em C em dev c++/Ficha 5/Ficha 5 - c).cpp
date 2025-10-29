#include <stdio.h>
#include <ctype.h>
#include <string.h>

int main() {
    char text[100];
    int i, len, alpha_count[26] = {0}, digit_count = 0;

    printf("Insira uma string : ");
    fgets(text, 100, stdin);

    len = strlen(text) - 1;

    for (i = 0; i < len; i++) {
        if (isalpha(text[i])) {
            alpha_count[tolower(text[i]) - 'a']++;
        } else if (isdigit(text[i])) {
            digit_count++;
        }
    }

    printf("Frequecia absoluta dos caracteres alfabeticos :\n");
    for (i = 0; i < 26; i++) {
        printf("%c: %d\n", 'a' + i, alpha_count[i]);
    }
    printf("frequencia absoluta de digitos na string: %d\n", digit_count);

    return 0;
}

