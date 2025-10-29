#include <stdio.h>

int calculo_salario (int num_h,int custo_h,int salario_b)
{

    return ((num_h * custo_h)+salario_b);

}



int main ()
{

    int nh,ch,sb,salario;

    printf("Insira o salario base : ");
    scanf("%d",&sb);
    printf("Insira o numero de horas : ");
    scanf("%d",&nh);
    printf("Insira o custo por hora : ");
    scanf("%d",&ch);

    salario = calculo_salario(nh,ch,sb);

    printf("\nO salario e : %d",salario);

    return 0;



}
