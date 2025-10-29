#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int x;
    int y;

} Ponto;

int main()
{
    Ponto* ptr = malloc(sizeof(Ponto));  // Aloca memória para a estrutura

    // Atribui valores aos membros da estrutura usando o operador '->'
    ptr->x = 10;
    ptr->y = 20;

    // Acessa e imprime os valores dos membros da estrutura usando o operador '->'
    printf("Coordenadas: (%d, %d)\n", ptr->x, ptr->y);

    free(ptr);  // Libera a memória alocada para a estrutura

    return 0;
}

