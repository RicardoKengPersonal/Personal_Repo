#include <stdio.h>

int soma(int a ,int b)
{
	return a+b;
}

int sum_digits(int n)
{
	int soma_digito = 0;

    while (n > 0)
    {
        int digito = n % 10;

        soma_digito = soma(soma_digito, digito);

        n /= 10;
    }

    return soma_digito;
}

int main()
{
	int num1;
	
	do
	{
		printf("Integer:");
		scanf("%d",&num1);
		
	}while(num1<0);
	
	int result = sum_digits(num1);
	
	printf("The result of the sum of %d digits: %d\n",num1,result);
	
	return 0;
}
