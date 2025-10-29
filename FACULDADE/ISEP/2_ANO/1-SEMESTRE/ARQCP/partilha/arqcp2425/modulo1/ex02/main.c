#include <stdio.h>

int main()
{
	int a = 12;
	
	printf("Integer:\n");
	printf("Value: %d\n",a);
	printf("Address: 0x%x\n",(unsigned int)&a);
	printf("Memory size: %lu\n",sizeof(a));

	return 0;
	
}
