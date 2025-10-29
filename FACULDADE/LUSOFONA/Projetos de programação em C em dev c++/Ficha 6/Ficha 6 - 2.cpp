#include <stdio.h>
#include <string.h>

int main() {
    char strings[5][100];
    int n = 5;

    printf("Insira %d strings:\n", n);
    for (int i = 0; i < n; i++) {
        fgets(strings[i], 100, stdin);
    }

    // Algoritmo de ordenação por seleção
    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++) {
            if (strcmp(strings[j], strings[min]) < 0) {
                min = j;
            }
        }
        if (min != i) {
            char temp[100];
            strcpy(temp, strings[i]);
            strcpy(strings[i], strings[min]);
            strcpy(strings[min], temp);
        }
    }

    printf("Strings ordenadas:\n");
    for (int i = 0; i < n; i++) {
        printf("%s", strings[i]);
    }

    return 0;
}

