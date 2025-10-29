#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>


int main()
{
    int ret;

    /* arg0 is the command name*/
    ret = execl("/bin/ls","ls","-1",(char*)NULL);

    /* The program only reaches this point if execl fails! */
    exit(ret);
}

//int execl(const char *path, const char *arg0, const char *arg1,......,(char*)NULL);
// Replace the process' code with a program given by path
// arg0 should have the name of the executable

// Only one difference between execl() and execv()
// ▪ execl() receives the parameters with a list of arguments, ended with
// the NULL string
// ▪ execv() receives arguments in a vector of strings; last position must
// have the NULL string