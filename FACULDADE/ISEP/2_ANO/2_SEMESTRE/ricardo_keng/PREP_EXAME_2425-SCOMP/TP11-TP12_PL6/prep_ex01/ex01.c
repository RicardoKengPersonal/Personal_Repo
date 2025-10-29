#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include <pthread.h>

void* print_name(void* arg)
{
    char local_name[50];
    strcpy(local_name,(char*)arg);
    free(arg);

    char buffer[50];
    int len = snprintf(buffer,sizeof(buffer),"%s\t",local_name);
    write(STDOUT_FILENO,buffer,len);

    pthread_exit(NULL);
}

int main()
{
    pthread_t t1,t2;

    char string1[]="First_Name";
    char string2[]="Second_Name";

    char *arg;
    arg = malloc(strlen(string1) + 1);
    strcpy(arg,string1);
    pthread_create(&t1,NULL,print_name,(void*)arg);

    arg = malloc(strlen(string2) + 1);
    strcpy(arg,string2);
    pthread_create(&t2,NULL,print_name,(void*)arg);

    pthread_join(t1,NULL);
    pthread_join(t2,NULL);

    exit(0);
}