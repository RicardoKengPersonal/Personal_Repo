#include <stdio.h>

int get_ascii_code(char c)
{
	return (int)c;
}

int main()
{
	char character;
	
	printf("Character:");
	scanf("%c",&character);
	
	int result = get_ascii_code(character);
	
	printf("ASCII code of '%c': %d\n",character,result);
	
	return 0;
	
}
