#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void sortScores(int scores[], char names[][100], int n) {
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (scores[j] < scores[j+1]) {
                swap(&scores[j], &scores[j+1]);
                char temp[100];
                strcpy(temp, names[j]);
                strcpy(names[j], names[j+1]);
                strcpy(names[j+1], temp);
            }
        }
    }
}

int searchName(char names[][100], char search[], int n) {
    for (int i = 0; i < n; i++) {
        if (strcmp(names[i], search) == 0) {
            return i;
        }
    }
    return -1;
}

int main() {
    int n;
    printf("Insira o numero de estudantes: ");
    scanf("%d", &n);

    int scores[n];
    char names[n][100];
    for (int i = 0; i < n; i++) {
        printf("Insira o nome e a nota do aluno %d: ", i+1);
        scanf("%s%d", names[i], &scores[i]);
    }

    sortScores(scores, names, n);

    printf("Ranking:\n");
    for (int i = 0; i < n; i++) {
        printf("%d. %s: %d\n", i+1, names[i], scores[i]);
    }

    char search[100];
    printf("Insira o nome a procurar: ");
    scanf("%s", search);

    int index = searchName(names, search, n);
    if (index == -1) {
        printf("Aluno\a nao encontrado.\n");
    } else {
        printf("A nota de %s e %d.\n", search, scores[index]);
    }

    return 0;
}

