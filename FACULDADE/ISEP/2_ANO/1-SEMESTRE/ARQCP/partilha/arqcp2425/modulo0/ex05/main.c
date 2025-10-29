#include <stdio.h>

int cmp(int a, int b)
{
	int resultado = 0;
	
	if(a > b)
	{
		resultado = 1;
	}
	if(a == b)
	{
		resultado = 0;
	}
	if(a < b)
	{
		resultado = -1;
	}
	
	return resultado;
}

int main()
{
	int num1,num2;
	
	printf("Integer A:");
	scanf("%d",&num1);
	
	printf("Integer B:");
	scanf("%d",&num2);
	
	int result = cmp(num1,num2);
	
	printf("Result: %d\n",result);
	
	return 0;
}
