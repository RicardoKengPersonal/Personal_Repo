#include <stdio.h>

int main () {
    int idade;

    do {
        printf("Insira a idade: ");
        scanf("%d", &idade);

        if (idade < 0 || idade > 130)
        {
            printf("Idade invalida.\tInsira novamente.\n");
        }
    } while (idade < 0 || idade > 130);


    printf("Idade do individuo: %d\n", idade);
    return 0;
}

