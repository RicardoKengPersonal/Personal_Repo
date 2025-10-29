#include <stdio.h>
#include <string.h>

void bubbleSort(char *text, int n) 
{
    int i, j;
    char temp;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (text[j] > text[j + 1]) {
                temp = text[j];
                text[j] = text[j + 1];
                text[j + 1] = temp;
            }
        }
    }
}

int main()
 {
    char text[100];
    int i, n;

    printf("Insira uma string: ");
    fgets(text, 100, stdin);

    n = strlen(text) - 1;

    bubbleSort(text, n);

    printf("String ordenada por ordem alfabetica: \n%s\n", text);

    return 0;
}

