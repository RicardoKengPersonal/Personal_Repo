- Used to establish a one-way communication channel between processes

- File descriptors are integral to understanding how pipes work

    ▪ When a pipe is created, two file descriptors are returned: one for reading
and one for writing

# Unnamed pipes
- file descriptors must be created before the fork() call

# The pipe function

#include<unistd.h>

int pipe(int fd[2]);



 Creates a pipe, placing two file descriptors in fd[]
▪ fd[0] is the read descriptor
▪ fd[1] is the write descriptor
 Returns
▪ 0 upon success
▪ -1 in case of error

# The read function

#include <unistd.h>

size_t read(int fd, void *buffer, size_t n_bytes);

 Attempts to read n_bytes, from descriptor fd, placing the bytes in buffer

 Returns
▪ Number of bytes effectively read
▪ 0, if there will be no more bytes to read
▪ -1, in case of error

# The write function

#include <unistd.h>

size_t write(int fd, void *buffer, size_t n_bytes);

 Attempts to write up to n_bytes from buffer to the resource referred to by fd

 Returns
▪ Number of bytes effectively written
▪ -1, in case of error

# Reading and writing

 Reading/writing synchronization is guaranteed
    ▪ write() blocks when the pipe is full
    ▪ read() blocks if there is no data in the pipe

 Ideal for the bounded-buffer producer-consumer problem
    ▪ One of the small collection of standard, well-known problems in
    concurrent programming
    ▪ (more on this in future lectures)

# The close function

#include <unistd.h>

int close(int fd);

 Closes the resource referred to by fd
    ▪ Removes the descriptor from the table
    ▪ The entry can be reused
 Returns
    ▪ 0 in case of success
    ▪ -1 in case of error

## Closing descriptors

 Descriptors which are not used by a process must be closed

    ▪ Good practice that is fundamental to guarantee correct behavior

 Closing descriptors used in a pipe indicates the end of communication by the process

 Write to a pipe with all read descriptors closed (no one is reading) fails and the process receives the SIGPIPE signal

 Reading from a pipe with all the write descriptors closed (no one can write) returns 0 (end-of-file)

# Example

#define BUFFER_SIZE 80

int main(void)
{
    char read_message[BUFFER_SIZE];
    char write_message[BUFFER_SIZE] = "It works!";

    int fd[2];

    pid_t pid;

    int n;

    /*Creates a pipe*/
    if(pipe(fd) == -1)
    {
        perror("Pipe failed");
        exit(EXIT_FAILURE);
    }

    /*Child inherits file descriptor table*/
    pid = fork();

    /*Parent will be the producer*/
    if (pid > 0)
    {
        /*Closes unused descriptor*/
        close(fd[0]);

        /*Writes message in pipe*/
        n = write(fd[1], write_msg, strlen(write_msg)+1);

        close(fd[1]);
        wait(NULL);
    }

    /*Child will be the consumer*/

    else {
        /* closes unused write descriptor*/
        close(fd[1]);

        /*read string from pipe*/
        n = read(fd[0], read_msg, BUFFER_SIZE);

        printf("Child read from pipe: %s\n",read_msg);

        /*Closes read descriptor*/
        close(fd[0]);

        exit(0);
    }
}

# Example – synchronization

 The next example demonstrates that the OS handles synchronization
    ▪ Ensuring that each message is correctly transmitted through the pipe
    without interference
 Why is no additional synchronization mechanism needed?

    ▪ The kernel manages concurrent writes to a pipe
    ▪ As long as messages are smaller than PIPE_BUF (typically 4096 bytes),
    each write() call is atomic
    ▪ The order of messages is unpredictable, but no race conditions occur

#define MESSAGE_SIZE 50 /* Small enough to be written atomically */
#define NUM_CHILDREN 2 /* Number of child processes */

int main()
{
    int fd[2], len, bytesRead;
    pid_t pids[NUM_CHILDREN];
    char buffer[MESSAGE_SIZE];

    /* Create the pipe */
    if (pipe(pipefd) == -1) 
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    /* Fork multiple child processes */
    for (int i = 0; i < NUM_CHILDREN; i++) 
    {
        if ((pids[i] = fork()) == 0) 
        {
            close(pipefd[0]);
            
            len = snprintf(buffer, MESSAGE_SIZE, "My PID is %d",getpid());

            write(pipefd[1], buffer, len);

            close(pipefd[1]);

            exit(EXIT_SUCCESS);
        }
    }
    /* parent closes unused write descriptor */
    close(fd[1]);
    /* Read messages from the pipe (order is non-deterministic) */
    for(int i = 0; i < NUM_CHILDREN; i++) 
    {
        bytesRead = read(fd[0], buffer, MESSAGE_SIZE - 1);
        if(bytesRead > 0) 
        {
            buffer[bytesRead] = '\0';
            printf("Parent received: %s", buffer);
        }
    }
    /* closes read descriptor */
    close(fd[0]);
    for(i = 0; i < NUM_CHILDREN; i++)
    {
        wait(NULL);
    }

 return 0;
}

# Pipes and exec functions

 Exec functions allow a process to run external code within thesame process

    ▪ File descriptors remain open across exec unless explicitly changed via
    fcntl()
 The new program code cannot access any previous variables

    ▪ Including arrays holding pipe descriptors, because the original process
    image is overwritten

# The dup function

#include <unistd.h>

int dup(int fd);

 Duplicates the descriptor in fd
 The OS copies the descriptor to the first free position in the descriptor table

 Returns
    ▪ The new descriptor (a different number) that points to the same file/resource
    ▪ -1, if a failure occurs


# The dup2 function

#include <unistd.h>

int dup2(int fd1, int fd2);

 Duplicates fd1 to the position of fd2
    ▪ (instead of a free position)
 If fd2 was previously open, it is closed before being reused
    ▪ Closing and reusing fd2 is atomic
 Returns
    ▪ A value that is equal to the value of fd2, if successful
    ▪ -1, if a failure occurs, it returns a value of -1


Example – redirect stdout
 Consider the following code compiled to executable test1

int main()
{
    int i;

    for(int i = 0; i < 5; i++)
    {
        fprintf(stdout,"Line %d\n",i+1);
        sleep(1);
    }

    return 0;
}


#define BUFFER_SIZE 80
int main(void)
{
    char buffer[BUFFER_SIZE];
    int fd[2],i,n;
    pid_t pid;

    /*Creates a pipe*/
    if(pipe(fd) == -1)
    {
        perror("Pipe failed.\n");
        exit(1);
    }

    /*Child inherits file descriptor table*/
    pid = fork();

    if(pid == 0)
    {
        printf("Child process\n");
    }

    /*Closes unused descriptors and redirects stdout to pipe*/
    close(fd[0]);
    dup2(fd[1],1);
    close(fd[1]);

    execlp("./test1","./test1", (char*)NULL);

    perror("exec error.\n");
    exit(1);

    else
    {
        printf("Parent...\n");

        /*Closes unused write descriptor*/
        close(fd[1]);

        /*reads from pipe*/
        while((n = read(fd[0],buffer,BUFFER_SIZE-1))!= 0)
        {
            buffer[n] = 0;
            printf("Parent read: %s\n",buffer);
        }

        /*Closes read descriptor*/
        close(fd[0]);
        wait(NULL);
    }
}


