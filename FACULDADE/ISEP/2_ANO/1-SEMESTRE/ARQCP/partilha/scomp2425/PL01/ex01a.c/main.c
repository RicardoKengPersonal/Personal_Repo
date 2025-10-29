#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(void)
{
	pid_t p,a;
	p = fork();
	a = fork();
	
	printf("Concurrent Programming\n");
	return 0;
}
