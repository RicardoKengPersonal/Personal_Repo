#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define SIZE 100

void convert_to_upper_func(char* str)
{
    for (int i = 0; str[i] != '\0'; i++) {
        if(str[i] >= 'a' && str[i] <= 'z') {
            str[i] = str[i] - ('a' - 'A');
        }
    }
}

int main()
{
    pid_t pid;

    int up_pipe[2];
    int down_pipe[2];

    char msg[SIZE];

    if(pipe(up_pipe) == -1)
    {
        perror("Pipe: up_pipe");
        exit(EXIT_FAILURE);
    }

    if(pipe(down_pipe) == -1)
    {
        perror("Pipe: down_pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork();

    if(pid > 0)
    {
        // FATHER (SERVER)
        close(up_pipe[1]); // SERVER WILL READ FROM THE UP PIPE, SO CLOSE THE WRITE DESCRIPTOR HERE
        close(down_pipe[0]); // SERVER WILL WRITE ON THE DOWN PIPE; SO CLOSE THE READ HERE

        read(up_pipe[0],msg,sizeof(msg));

        convert_to_upper_func(msg);

        printf("[Father]: Finished converting...\n");

        write(down_pipe[1],msg,sizeof(msg));

        close(down_pipe[1]);
        
    } else {
        // CHILD (CLIENT)
        close(up_pipe[0]); // CLIENT WRITES ON THE UP, SO CLOSES THE READ UP PIPE
        close(down_pipe[1]); // CLOSE THE WRITE AS IT WILL READ FROM THE DOWN PIPE
        
        printf("[CHILD]: Reading Message....:");
        fgets(msg,sizeof(msg),stdin);

        write(up_pipe[1],msg,sizeof(msg));
        close(up_pipe[1]);

        close(up_pipe[0]); // WONT BE USED ANYMORE

        read(down_pipe[0],msg, sizeof(msg));
        close(down_pipe[0]);

        printf("[CHILD] -> CONVERTED STRING (GOT FROM PIPE): %s\n",msg);

        exit(0);
    }

    wait(NULL);

    exit(0);
}