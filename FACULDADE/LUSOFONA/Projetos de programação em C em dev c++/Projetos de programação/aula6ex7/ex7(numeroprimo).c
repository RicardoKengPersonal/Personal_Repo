#include <stdio.h>

int main() {
    int num, i, primo = 1;

    printf("Insira o numero para verificar se e primo: ");
    scanf("%d", &num);

    if (num == 1) {
        printf("Numero nao primo.\n");
    } else {
        for (i = 2; i < num; i++) {
            if (num % i == 0) {
                primo = 0;
                break;
            }
        }
        if (primo == 1) {
            printf("Numero primo.\n");
        } else {
            printf("Numero nao primo.\n");
        }
    }

    return 0;
}
