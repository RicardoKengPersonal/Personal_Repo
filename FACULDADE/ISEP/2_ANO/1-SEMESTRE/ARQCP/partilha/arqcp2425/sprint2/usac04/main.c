#include <stdio.h>
#include "asm.h"

int main(void) {
	int value = 26;
	char str []= "CMD";
	char cmd[20];
	int res = format_command( str , value , cmd ) ;
	printf("%d: %s\n", res, cmd ); // 1: ON ,1 ,1 ,0 ,1 ,0
	char str2 [] =" aaa ";
	res = format_command(str2 , value , cmd ) ;
	printf("%d: %s\n", res, cmd ); // 0:

    return 0;
}
