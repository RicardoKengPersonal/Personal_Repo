#include <stdio.h>

int conversao_em_segundos (int h, int m, int s)
{
    int seg;
    seg = h * 3600 + m * 60 + s;

    return seg;
}

main ()
{

    int hor,min,seg,segundos;

    printf("Digite as horas: ");            scanf("%d",&hor);
    printf("Digite os minutos: ");          scanf("%d",&hor);
    printf("Digite os segundos: ");         scanf("%d",&hor);

    segundos = conversao_em_segundos (hor,min,seg);

    printf("Total em segundos: %d",segundos);


}
