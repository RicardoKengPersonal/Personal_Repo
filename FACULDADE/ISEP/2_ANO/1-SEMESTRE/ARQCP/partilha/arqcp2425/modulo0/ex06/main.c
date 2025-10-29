#include <stdio.h>

int get_greater_digit(int n)
{
	int maxDigit = -1;

    if (n < 0) 
    {
        n = -n; 
    }

    while (n > 0) 
    {
        int digit = n % 10; 
        
        if (digit > maxDigit) 
        {
            maxDigit = digit; 
        }
        
        n /= 10;
    }

    return maxDigit;
} 

int main()
{
	int num;
	
	printf("Number:");
	scanf("%d",&num);
	
	int result = get_greater_digit(num);
	
	printf("Greater digit of %d : %d\n",num,result);
	
	return 0;
	
}
