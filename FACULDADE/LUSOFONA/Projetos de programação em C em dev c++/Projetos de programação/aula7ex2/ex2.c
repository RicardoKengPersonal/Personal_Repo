#include <stdio.h>

int conversao_em_segundos(int h, int m, int s)
{
    int seg;
    seg = h * 3600 + m * 60 + s;
    return seg;
}

void leitura(int l_sup, int l_inf, int *horas, int *minutos, int *segundos)
{
    do
    {
        printf("\nDigite as horas (%d,%d):  ", l_inf, l_sup);
        scanf("%d", horas);

        if (*horas < l_inf || *horas > l_sup)
        {
            printf("\nValor inválido (fora dos limites).\n");
        }

    } while (*horas < l_inf || *horas > l_sup);

    do
    {
        printf("\nDigite os minutos (%d,%d):  ", l_inf, 59);
        scanf("%d", minutos);

        if (*minutos < l_inf || *minutos > 59)
        {
            printf("\nValor inválido (fora dos limites).\n");
        }

    } while (*minutos < l_inf || *minutos > 59);

    do
    {
        printf("\nDigite os segundos (%d,%d):  ", l_inf, 59);
        scanf("%d", segundos);

        if (*segundos < l_inf || *segundos > 59)
        {
            printf("\nValor inválido (fora dos limites).\n");
        }

    } while (*segundos < l_inf || *segundos > 59);
}

int main()
{
    int hor, min, seg, segundos;
    int l_sup = 23, l_inf = 0;

    leitura(l_sup, l_inf, &hor, &min, &seg);

    segundos = conversao_em_segundos(hor, min, seg);
    printf("Total em segundos: %d", segundos);

    return 0;
}

