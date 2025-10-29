#include <stdio.h>

int main()
{
    int ano_nasc, sexo, menos_30 = 0, feminino = 0, masculino = 0, total_condutores = 0, masc_sup_alcool = 0;
    long num_bi = 1;
    float alcool, perc_cond_trinta = 0, perc_mulheres = 0, perc_masc_alcool = 0;

    do {
        printf("\nNumero de bilhete de identidade: ");
        scanf("%ld", &num_bi);

        if (num_bi != 0) {
            total_condutores++;

            printf("\nAno de nascimento: ");
            scanf("%d", &ano_nasc);

            printf("\nInsira 1 para Homem, 2 para mulher: ");
            scanf("%d", &sexo);

            switch (sexo) {
                case 1:
                    masculino++;

                    printf("\nAlcool no sangue : ");
                    scanf("%f", &alcool);

                    if (alcool > 0.5) {
                        masc_sup_alcool++;
                    }

                    break;

                case 2:
                    feminino++;
                    break;

                default:
                    printf("\nInvalido.");
                    break;
            }

            if (ano_nasc < 1993) {
                menos_30++;
            }
        }
    } while (num_bi != 0);

    perc_cond_trinta = (menos_30 / (float) total_condutores) * 100;
    perc_mulheres = (feminino / (float) total_condutores) * 100;
    perc_masc_alcool = (masc_sup_alcool / (float) total_condutores) * 100;

    printf("\nPercentagem de condutores com menos de 30 anos: %.2f", perc_cond_trinta);
    printf("\nPercentagem de condutores femininos: %.2f", perc_mulheres);
    printf("\nPercentagem de condutores masculinos com alcool superior a 0.5 : %.2f", perc_masc_alcool);

    return 0;
}

