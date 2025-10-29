#include <stdio.h>

char get_ascii_char(int c)
{
	return (char)c;
}

int main()
{
	int num;
	
	do
	{
		printf("Number between 0-255:");
		scanf("%d",&num);
		
	}while(num<0 || num > 255);
	
	char cod_ascii = get_ascii_char(num);
	
	printf("%d corresponds to '%c'\n",num,cod_ascii);
	
	return 0;
}
