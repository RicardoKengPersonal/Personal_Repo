#include <stdio.h>


int main ()
{

    int mt_1,mt_2,mt_3,t1,t2,aulas_dadas,aulas_assistidas;
    float classif,media_mt,media_trab,nota_aulas;

    classif = 0;
    media_mt = 0;
    media_trab = 0;

    printf("\n\t\t| Calculo de nota final |");
    printf("Insira valores apenas de 0 a 20");
    printf("\nNota do miniteste 1 : ");
    scanf("%d",&mt_1);
    printf("\nNota do miniteste 2 : ");
    scanf("%d",&mt_2);
    printf("\nNota do miniteste 3 : ");
    scanf("%d",&mt_3);
    printf("\nNota do trabalho 1 : ");
    scanf("%d",&t1);
    printf("\nNota do trabalho 2 : ");
    scanf("%d",&t2);
    printf("\nNumero de aulas dadas : ");
    scanf("%d",&aulas_dadas);
    printf("\nNumero de aulas assistidas : ");
    scanf("%d",&aulas_assistidas);


    media_mt = (mt_1 + mt_2 + mt_3 ) / 3.0;
    media_trab = ( (t1*0.4)+(t2*0.6) );
    nota_aulas = ( aulas_assistidas / (float) aulas_dadas ) * 20;

    printf("\nA media dos minitestes e : %.2f",media_mt);
    printf("\nA media dos trabalhos e : %.2f",media_trab);
    printf("\nA media das aulas e : %.2f",nota_aulas);


    classif = ( media_mt * 0.5 ) + (media_trab * 0.4) + ( nota_aulas ) * 0.1 ;

    printf("\nA classificacao final e : %.2f",classif);

    return 0;

}
