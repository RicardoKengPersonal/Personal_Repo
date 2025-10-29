## TP01

The fork function:

#include <sys/types.h>
#include <unistd.h>
pid_t fork();

 Processes can be created:
▪ By the OS (e.g., system startup, scheduled tasks)
▪ By a user (e.g., launching an application)
▪ By another process (e.g., spawning child processes)

Process hierarchy
 The process that calls fork() is known as the parent process
 The newly created process is called the child process
 Both parent and child processes can create additional
processes, forming a process tree
▪ Each process has exactly one parent but can have zero or more child
processes

Process creation
 After fork(), both processes execute the same code, starting
from the instruction immediately after the fork() call
 The execution order is non-deterministic
▪ The OS scheduler determines which process runs at any given moment
▪ Parent and child are independent processes that compete for CPU time


fork() return values
 -1, in case of error
▪ It was not possible to create the child, so a single process still exists
 On success, the different values received by parent and child
allows the separation of the code each one will execute
 The PID of the child (> 0) is returned to the parent process
▪ Parent may need this PID to communicate or synchronize with the child
 Zero is returned to the child process
▪ Indicating that fork() was not executed by the child

pid = fork();
if(pid == -1){
 perror(“Fork failed”);
 exit(1);
}
if(pid > 0)
 printf(“Luke, I’m your father\n”);
else
 printf(“I am the child\n”);
printf(“Who executes this line?”);

The fork function
 fork() is a unique function!
 Called once, returns twice
▪ Return value in parent is
the PID of the newly created child
▪ Return value in the child
is zero

Changes after fork
 In the child process, variables start with the same values as in
the parent at the moment fork() is executed
 However, any modifications made in either process after
fork() are independent and do not affect the other process

The getpid and getppid functions

#include <sys/types.h>
#include <unistd.h>
pid_t getpid(); -> returns the PID of the calling process
pid_t getppid(); -> returns the PID of the parent of the calling process

The sleep function

#include <unistd.h>
int sleep(int seconds);

 Suspends execution of the process during (at least) n seconds
▪ If a process receives a signal while it is sleeping, it may wake up before its
intended time (more on this in the next classes)
 Important note!
▪ sleep() can be used to try to obtain an order between processes, but it
is not guaranteed
▪ So, use it only for testing and debugging, not to guarantee an execution
order and/or synchronization!


The exit function

#include <stdlib.h>
void exit(int status);

 Terminates a process, returning an exit status to the OS
▪ The exit status is stored in a single byte, meaning it can hold values
between 0 and 255
 Two constants can be used, EXIT_SUCCESS and
EXIT_FAILURE, to indicate success or failure in the execution
▪ 0 typically indicates successful execution
▪ Nonzero values (1–255) usually signal errors or specific termination codes


The wait function

#include <sys/wait.h>
pid_t wait(int *status);

 Blocks execution until one of the process’s child processes
terminate
▪ Returns the PID of the terminated child, or -1 in case of error
 The status parameter must be a pointer to a variable that
will store the child's exit status
▪ The exit status includes the child’s return value and additional information
provided by the OS (e.g., termination signal, abnormal exit conditions)

Process exit information

 WIFEXITED(status)
▪ Checks if the child terminated normally
 WEXITSTATUS(status)
▪ Exit value (1 byte) given in the exit function
 WIFSIGNALED(status)
▪ Checks if the child terminated due to a non-treated signal
 WTERMSIG(status)
▪ Gets the number of the signal that terminated the child
 WCOREDUMP(status)
▪ Checks if a core dump was generated
 WIFSTOPPED(status)
▪ Checks if the child is in the STOPPED state
 WIFCONTINUED(status)
▪ Checks if the process was continued

## EXAMPLE

pid_t pid1, pid2;
int status;

pid1 = fork();

if(pid1 > 0)
{
    /* Parent waits for child to terminate */
    pid2 = wait(&status);

    if(WIFEXITED(status))

    printf(“Parent: child %d returned %d\n”, pid2,WEXITSTATUS(status));

} else {

    sleep(5);
    exit(10);
}

## TP02

Process termination

 A process terminates when:
    ▪ Executes the last instruction
    ▪ Invokes the exit() system call
    ▪ Voluntarily in an error
    ▪ Involuntarily in an error
    ▪ By action of a user or another process

 When a process terminates, the parent is informed by the OS
through the SIGCHLD signal

    ▪ This event is asynchronous – the parent can receive it at any time
    ▪ The parent can ignore the signal (the default behavior) or provide a
    function to be executed when the signal is received (a handler)
    ▪ (more on this in future classes)

The waitpid function

#include <sys/wait.h>
pid_t waitpid(pid_t pid, int *status, int options);

 Blocks until the specified child process (given by pid)
terminates

    ▪ pid = -1: Waits for any child process (similar to wait())
     The options parameter can be:
    ▪ 0 → Blocks until the child terminates
    ▪ WNOHANG → Does not block; returns immediately if no child has exited
    ▪ WUNTRACED → Also returns if a child is in the STOPPED state

The wait and waitpid functions

 When invoking wait() or waitpid(), the parent process
may experience the following outcomes:
▪ Be blocked until the child terminates
▪ The calling process is put on hold until one of its child processes finishes
execution
▪ Return immediately, if the child has already terminated
▪ If the child process has already completed before the call to wait() or
waitpid(), the function will return without delay
▪ Return immediately with an error, if there are no children
▪ If there are no child processes to wait for, the function will return an
error

Zombie processes
 If the child terminates before the parent
▪ It becomes a zombie, meaning it cannot execute, but the operating system
still must retain information about its termination
 OS cannot remove the process from the process table
▪ But can free all process resources
 Parent must acknowledge the termination for the entry to be
removed
▪ Acknowledgement is made with the wait/waitpid functions
▪ After acknowledgement, the process disappears and is no longer a zombie

Orphan processes
 If the parent terminates before the child, the child become
orphan
 In that case, it is necessary for the process to be adopted by
another process to maintain hierarchy
 In UNIX-like OSes, orphan processes are adopted by the
init/systemd process which periodically invokes wait for all
its children
▪ But they can still linger if not handled correctly

Why use wait/waitpid functions?
 Resource management
▪ Reclaiming resources used by zombie processes prevents resource
exhaustion and keeps the system running smoothly
 Process management
▪ Knowing when child processes terminate allows for proper cleanup, error
handling, and coordination between parent and child processes
 System stability
▪ Preventing a buildup of zombie processes contributes to overall system
stability and performance

Exec functions
 Set of functions that allow for a process to execute a
completely new program
▪ These functions replace the image of a process with the image of a
different program
 The program being executed is replaced, but the process is still
the same
▪ Same PID, same open files, …
▪ However, signals are reset to their default handlers (more on this in the
next class)

Exec functions
 Why are exec functions important?
▪ Exec functions replace the current process image with a new one
▪ Unlike fork(), which creates a new process, exec keeps the same PID but
loads a different program
▪ It is fundamental in UNIX-based systems for process management
 Typical uses of exec functions
▪ Launching new programs
▪ Implementing shell commands
▪ Replacing a child process’ code after fork()
▪ Executing a different program within the same process

Important!
 The process does not return to the previous program because
its code is entirely replaced
 The original program will only continue if the exec function
encounters an error on its setup
 A successful execution of an exec function does not allow any
return to the previous code

The execl and execv functions

#include <unistd.h>
int execl(const char *path, const char *arg0, const char *arg1,
 ..., (char*)NULL);
int execv(const char *path, const char *args[]);

 Replace the process’s code with a program given by path
▪ arg0 should have the name of the executable
 Only one difference between execl() and execv()
▪ execl() receives the parameters with a list of arguments, ended with
the NULL string
▪ execv() receives arguments in a vector of strings; last position must
have the NULL string

## execl

#include <unistd.h>
int main(){
 int ret;
 /* arg0 is the command name */
 ret = execl(“/bin/ls”, “ls”, “-l”, (char*)NULL);
 /* The program only reaches this point if execl fails! */
 exit(ret);
}

## execv 

#include <unistd.h>
int main(){
 int ret;
 /* arg0 is the command name */
 char *cmd[] = {“ls”, “-l”, (char*)NULL};

 ret = execv(“/bin/ls”, cmd);
 /* The program only reaches this point if execv fails! */
 exit(ret);
}

The execle and execve functions

#include <unistd.h>
int execle(const char *path, const char *arg0, const char *arg1,
 ..., (char*)NULL, const char *envp[]);
int execve(const char *path, const char *args[],
const char *envp[]);

 Same behavior as previous functions
 Only adding the environment variables
▪ Using const *char envp[]

## execle

#include <unistd.h>
int main(){
 int ret;
 char *env[] = {“HOME=/usr/home”, “LOGNAME=home”, (char
*)NULL};
 ret = execle (“/bin/ls”, “ls”, “-l”, (char *) NULL, env);
 /* The program only reaches this point if execle fails! */
 exit(ret);
}

The execlp and execvp functions

#include <unistd.h>
int execlp(const char *path, const char *arg0, const char *arg1,
 ..., (char*)NULL);
int execvp(const char *path, const char *args[]);

 If the full path is not specified, the executable is searched in
the folders provided in the $PATH environment variable

## execlp

#include <unistd.h>
int main(){
 int ret;
 /* arg0 is the command name */
 ret = execlp(“ls”, “ls”, “-l”, (char*)NULL);
 /* The program only reaches this point if execl fails! */
 exit(ret);
}

Typical use of exec functions
 A child process is created using fork()
 The exec() function is called in the child process to replace its
image
 The parent process waits for the child’s termination using
wait() or waitpid()


