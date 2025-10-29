Introduction
 Pipes are a fundamental Inter-Process Communication (IPC)
mechanism in operating systems
 Used to establish a one-way communication channel between
processes
 File descriptors are integral to understanding how pipes work
▪ When a pipe is created, two file descriptors are returned: one for reading
and one for writing

I/O in Linux
 UNIX-like OSes strive to offer a consistent interface for
communication between processes and the file system
 Many IPC mechanisms rely on understanding the file system
interface, reinforcing the principle that “everything is a file”
 Mastery of this uniform interface is crucial for the development
of efficient and flexible communication channels between
processes

I/O in Linux
 In C, it is usual to use the stdio library for I/O
▪ printf(), scanf(), fopen(), ...
 It provides a higher level of abstraction
▪ Buffering, I/O converting, formatting, …
 However, it is not always desirable/possible to use these
functionalities
▪ E.g., if intending to control the specific operations of a particular device,
control buffer sizes, or non-blocking I/O

I/O in Linux
 In POSIX systems, a file descriptor is an integer that identifies
an open file (or resource) in the system
 File descriptors provide a low-level interface for I/O operations
▪ open(), read(), write(), close(), ...
 This descriptor is an index in a table, named the file descriptors
table
▪ The table is only accessible by the OS kernel and cannot be manipulated by
user-level processes

 Each process has its own descriptors table
▪ Descriptors 0, 1 and 2 are predefined: stdin, stdout, and stderr,
respectively 

File descriptor table
 A process may open the same file/resource more than once,
getting different descriptors
 If two different processes open the same file, there is no
relation between the descriptors in the two processes
▪ i.e., their values can be different
 File descriptors are copied in a fork() and preserved (usually)
in an exec function

Pipes
 A byte-oriented communication mechanism accessed via read
and write file descriptors
 Utilizes a First In, First Out (FIFO) buffer in the OS kernel for
message passing
▪ Ensures a sequential and orderly exchange of data between processes
 Since the buffer resides in the kernel, the OS always intervenes
▪ Which provides several advantages in synchronizing reads and writes
between processes
▪ (but also introduces overhead and potential performance bottlenecks,
which will be addressed by using shared memory latter in the semester)

Named vs unnamed pipes
 Unnamed pipes are used for communication between parent
and child processes
▪ Exist only during the lifetime of the processes involved
▪ No name in the file system
▪ Data is lost once both ends are closed
▪ (this will be the variant used in SCOMP)
 Named pipes can be used for communication between
unrelated processes
▪ Persist until explicitly removed, even after the processes close the pipe
▪ Have a name in the file system
▪ Allows for more flexible inter-process communication

Unnamed pipes
 Explore the inheritance of file descriptors by child processes
▪ Therefore, file descriptors must be created before the fork() call
 Requires a hierarchical relationship between processes for
proper communication

Pipe data flow
 A flow of bytes written to the pipe using the write descriptor
can be subsequently read using the read descriptor
 Data transfers to the pipe are atomic up to a certain limit,
defined by the constant PIPE_BUF
▪ This ensures that writes of data smaller than or equal to PIPE_BUF are
completed as a single, uninterrupted operation
 Reading removes the data from the pipe
▪ Which ensures that each byte of data is only read once
 As a result, pipes are unidirectional
▪ To have bidirectional communication, it is necessary to use two pipes

The pipe function
#include <unistd.h>
int pipe(int fd[2]);

 Creates a pipe, placing two file descriptors in fd[]
▪ fd[0] is the read descriptor
▪ fd[1] is the write descriptor
 Returns
▪ 0 upon success
▪ -1 in case of error

The read function

#include <unistd.h>
size_t read(int fd, void *buffer, size_t n_bytes);

 Attempts to read n_bytes, from descriptor fd, placing the
bytes in buffer
 Returns
▪ Number of bytes effectively read
▪ 0, if there will be no more bytes to read
▪ -1, in case of error

The write function

#include <unistd.h>
size_t write(int fd, void *buffer, size_t n_bytes);

 Attempts to write up to n_bytes from buffer to the
resource referred to by fd
 Returns
▪ Number of bytes effectively written
▪ -1, in case of error

Reading and writing
 Reading/writing synchronization is guaranteed
▪ write() blocks when the pipe is full
▪ read() blocks if there is no data in the pipe
 Ideal for the bounded-buffer producer-consumer problem
▪ One of the small collection of standard, well-known problems in
concurrent programming
▪ (more on this in future lectures)

The close function

#include <unistd.h>
int close(int fd);

 Closes the resource referred to by fd
▪ Removes the descriptor from the table
▪ The entry can be reused
 Returns
▪ 0 in case of success
▪ -1 in case of error

 Closes the resource referred to by fd
▪ Removes the descriptor from the table
▪ The entry can be reused
 Returns
▪ 0 in case of success
▪ -1 in case of error

#define BUFFER_SIZE 80
int main(void){
 char read_msg[BUFFER_SIZE];
 char write_msg[BUFFER_SIZE] = ”It works!";
 int fd[2];
 pid_t pid;
 int n;
 /* Creates a pipe */
 if(pipe(fd) == -1){
 perror("Pipe failed”);
 exit(1);
 }

 /* Child inherits file descriptor table */
 pid = fork();
/* parent will be the producer */
 if(pid > 0){
 /* closes unused read descriptor */
 close(fd[0]);
 /* writes string in pipe */
 n = write(fd[1],write_msg,strlen(write_msg)+1);
 /* closes write descriptor */
 close(fd[1]);
 wait(NULL);
 }

/* child will be the consumer */
 else{
 /* closes unused write descriptor */
 close(fd[1]);
 /* read string from pipe */
 n = read(fd[0], read_msg, BUFFER_SIZE);
 printf(“Child read from pipe: %s\n”, read_msg);
 /* closes read descriptor */
 close(fd[0]);
 exit(0);
 }

 Example – synchronization
 The next example demonstrates that the OS handles
synchronization
▪ Ensuring that each message is correctly transmitted through the pipe
without interference
 Why is no additional synchronization mechanism needed?
▪ The kernel manages concurrent writes to a pipe
▪ As long as messages are smaller than PIPE_BUF (typically 4096 bytes),
each write() call is atomic
▪ The order of messages is unpredictable, but no race conditions occur

## Example - synchronization

#define MESSAGE_SIZE 50 /* Small enough to be written atomically */
#define NUM_CHILDREN 2 /* Number of child processes */
int main(){
 int fd[2], len, bytesRead;
 pid_t pids[NUM_CHILDREN];
 char buffer[MESSAGE_SIZE];
 /* Create the pipe */
 if (pipe(fd) == -1) {
 perror("pipe");
 exit(EXIT_FAILURE);
 }

 /* Fork multiple child processes */
 for (int i = 0; i < NUM_CHILDREN; i++) {
 if ((pids[i] = fork()) == 0) {
 close(fd[0]);
 len = snprintf(buffer, MESSAGE_SIZE, "My PID is %d",
getpid());
 write(fd[1], buffer, len);
 close(fd[1]);
 exit(EXIT_SUCCESS);
 }
 }

 /* parent closes unused write descriptor */
 close(fd[1]);

/* Read messages from the pipe (order is non-deterministic) */
 for(int i = 0; i < NUM_CHILDREN; i++) {
 bytesRead = read(fd[0], buffer, MESSAGE_SIZE - 1);
 if(bytesRead > 0) {
 buffer[bytesRead] = '\0';
 printf("Parent received: %s", buffer);
 }
 }

 /* closes read descriptor */
 close(fd[0]);
 for(i = 0; i < NUM_CHILDREN; i++){
 wait(NULL);
 }

 return 0;
}

## Recall from previous class

 After fork(), parent and child have the same descriptors
 Unused descriptors must be closed
▪ EOF is only returned in a read() if all write descriptors are closed
▪ The SIGPIPE signal only unblocks a write() if all read descriptors are
closed

Pipes and exec functions
 Exec functions allow a process to run external code within the
same process
▪ File descriptors remain open across exec unless explicitly changed via
fcntl()
 The new program code cannot access any previous variables
▪ Including arrays holding pipe descriptors, because the original process
image is overwritten
 How can we design multiple processes, each running an
external executable, so they run concurrently and exchange
data through pipes?

Pipes and exec functions
 Most programs are designed to read data from stdin and
output data to stdout
▪ These can be redirected to a pipe
 Redirecting the flow of bytes from/to a pipe requires
manipulating the descriptor table

The dup function

#include <unistd.h>
int dup(int fd);

 Duplicates the descriptor in fd
 The OS copies the descriptor to the first free position in the
descriptor table
 Returns
▪ The new descriptor (a different number) that points to the same
file/resource
▪ -1, if a failure occurs

The dup2 function

#include <unistd.h>
int dup2(int fd1, int fd2);

 Duplicates fd1 to the position of fd2
▪ (instead of a free position)
 If fd2 was previously open, it is closed before being reused
▪ Closing and reusing fd2 is atomic
 Returns
▪ A value that is equal to the value of fd2, if successful
▪ -1, if a failure occurs, it returns a value of -1

## Example – redirect stdout

 Consider the following code compiled to executable test1
int main(){
 int i;
 for(i=0;i<5;i++){
 fprintf(stdout, “Line %d\n”, i+1);
 sleep(1);
 }
 return 0;
} 

#define BUFFER_SIZE 80
int main(void){
 char buffer[BUFFER_SIZE];
 int fd[2],i,n;
 pid_t pid;
 /* creates a pipe */
 if(pipe(fd) == -1){
 perror(“Pipe failed”);
 exit(1);
 }
 /* Child inherits file descriptor table */
 pid = fork();
 if(pid == 0){
 printf(“Child process\n”);
 /* Closes unused descriptors and
 redirects stdout to pipe */
 close(fd[0]);
 dup2(fd[1],1);
 close(fd[1]);
 execlp(“./test1”, “./test1”, (char*)NULL);
 perror(“exec error”);
 exit(1);
 } 

 else{
 printf(“Parent...\n”);
 /* closes unused write descriptor */
 close(fd[1]);
 /* reads from pipe */
 while((n=read(fd[0],buffer,BUFFER_SIZE-1))!=0){
 buffer[n] = 0;
 printf(“Parent read: %s\n”,buffer);
 }
 /* closes read descriptor */
 close(fd[0]);
 wait(NULL);
}