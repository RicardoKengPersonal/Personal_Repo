#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(void)
{
	pid_t pid;
	
	int i;
	
	for( i = 0; i < 4 ; i++)
	{
		pid = fork();
	}
	
	printf("Concurrent Programming\n");
	
	return 0;
	
}
