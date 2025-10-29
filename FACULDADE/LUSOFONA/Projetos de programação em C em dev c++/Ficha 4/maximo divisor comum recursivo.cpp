#include <stdio.h>

int gcd(int,int);

int main()
{
	int num1, num2;
	
	printf("Insira um numero:");
	scanf("%d", &num1);
	
	printf("Insira um numero:");
	scanf("%d", &num2);
	
	printf("maximo divisor comum de  %d e %d e %d.\n",num1,num2,gcd(num1,num2));
	
	return 0;

}

int gcd (int n1,int n2)
{
	if(n1 == 0) 
	return n2;
	if (n2 == 0)
	return n1;
	
	if (n1>n2)
		return gcd (n1%n2,n2);
	else 
	return gcd( n1, n2%n1);
			
}

