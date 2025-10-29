#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

int main()
{
    int ret;

    /* arg0 is the command name*/
    ret = execlp("ls","ls","-1", (char*)NULL);

    /* The program only reaches this point if execl fails!*/
    exit(ret);
}

/* The execlp and execvp functions

int execlp(const char *path, const char *arg0, const char *arg1,....,(char*)NULL);

int execvp(const char *path, const char *args[]);


 If the full path is not specified, the executable is searched in
the folders provided in the $PATH environment variable

*/