#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    int ret;

    /* arg0 is the command name*/
    char *cmd[] = {"ls","-1",(char*)NULL};

    ret = execv("/bin/ls",cmd);

    /* The program only reaches this point if execv fails! */
    exit(ret);
}