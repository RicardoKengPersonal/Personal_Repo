#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
    int ret;

    char *env[] = {"HOME=/usr/home","LOGNAME=home",(char*)NULL};

    ret = execle("/bin/ls","ls","-1",(char*)NULL,env);

    /* The program only reaches this point if execle fails!*/
    exit(ret);
}

/* The execle and execve functions:
int execle(const char *path, const char *arg0, const char *arg1,....,(char*)NULL, const char *envp[]);


int execve(const char *path, const char *args[],const char *envp[]);

- Same behavior as previous functions

- Only adding the environment variables
    - Using const *char envp[] 
*/