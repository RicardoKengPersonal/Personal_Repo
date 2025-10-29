#include <stdio.h>

int main()
{
	unsigned long num = 0x12345678AABBCCAA;
	unsigned char c = get_greater_byte(num);
	printf("%#2x\n",c);//0xCC
	
	return 0;
	
}
