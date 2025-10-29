# Signals

A signal is a notification of an event sent to a specific process

    ▪ Process receives an integer that maps to a specific event
    ▪ They are a limited form of inter-process communication

- Notifications are asynchronous

    ▪ A process may receive a notification at any time, during its execution

- If you can’t predict when an event occurs, then it is not
practical to handle it in the main body of the program

    ▪ The solution is to write specialized code to handle the signal and execute it only when necessary

# Signal sources in a system
- User-raised signals
    ▪ Sent manually using commands like kill()

- Inter-process or OS signals
    ▪ Sent by other processes or the operating system

- Self-raised signals
    ▪ A process can send signals to itself using functions like raise() or abort()

# Which events? Which signals?

- You can list the signals in your system, with the following
command:
    - $ kill -l (L minusculo)

- …and look at the manual pages:
    - $ man signal

# Some examples of signals
Signal  Default action      Description


SIGINT: Terminate process;  Interrupt program

SIGILL: Create core image ; Illegal instruction

SIGKILL: Terminate process; Kill program (can’t be ignored or handled)

SIGSEGV: Create core image; Segmentation violation (illegal memory access)

SIGALRM: Terminate process; Real-time timer expired

SIGSTOP: Stop process; Stop (can’t be ignored or handled)

SIGCONT: Discard signal; Continue after stop

SIGCHLD: Discard signal; Child status has changed

SIGUSR1: Terminate process; User defined signal 1

SIGUSR2: Terminate process; User defined signal 2


# Dealing with an incoming signal

- Default action for a specific signal
    ▪ Terminate: SIGKILL, SIGINT, SIGUSR1, SIGUSR2
    ▪ Create core image and terminate: SIGILL, SIGSEGV, SIGFPE,
    SIGABORT

▪ Stop/Continue: SIGSTOP, SIGCONT

▪ Ignore: SIGCHLD, SIGIO, SIGINFO

- Programmer-defined action (if signal allows)
    ▪ Ignore
    ▪ Execute a handler function

# Signaling from the command line
- Use the kill command, specifying the signal and the target
process
- Some examples:
    ▪ Send the default SIGTERM to process with PID 1234
            $ kill 1234

    ▪ Send SIGKILL to process with PID 1234
            $ kill –SIGKILL 1234

    ▪ Send SIGKILL to process with PID 1234 using the signal’s number
            $ kill –9 1234

# Signaling using the kill function

#include <signal.h>
int kill(pid_t pid, int sig)

- Sends the signal specified by sig to the process specified by
pid
    ▪ A non-root process cannot send signals to processes owned by other users

- If pid equals 0, then sig is sent to every process in the
process group of the calling process

- If pid equals -1, then sig is sent to every process for which
the calling process has permission to send signals

# Waiting for a signal

#include <signal.h>

int pause();

- Causes the calling process to pause until a signal is received
    - That either terminates the process or causes the invocation of a signalhandling function

- Upon termination of a signal handler started during a pause,
the pause() call will return

# IMPORTANT NOTE

- There is no signal buffering
    ▪ If a signal arrives before pause() is called, it is lost and will not wake up the process

    ▪ pause() will not return until another signal arrives

- This can lead to synchronization issues if pause() is used
without ensuring the signal is sent at the right moment

# Signaling using the raise function

#include <signal.h>

int raise(int sig);

- Sends the signal specified by sig to the calling process
    ▪ It is equivalent to kill(getpid(), sig)

- If the signal causes a handler to be called, raise() will return
only after the signal handler has returned

# Signaling using the alarm function

#include <signal.h>

unsigned int alarm(unsigned int seconds);



- Sends the SIGALRM signal to the current process in seconds
seconds
    ▪ If seconds is zero, any pending alarm is canceled

- Alarms created by alarm() are preserved across exec() and
are not inherited by children created via fork()

# Setting the action for a signal

#include <signal.h>

int sigaction(int sig, const struct sigaction *act, struct sigaction *oact);


- Modifies the behavior of a process when receiving a specific
signal sig
    ▪ act specifies the action details for the signal sig
    ▪ oact (if not NULL) stores the previously set action details

- Let’s explore struct sigaction
    ▪ A powerful mechanism for defining how a process responds to signals, allowing custom handlers, signal masks, and fine-grained control over signal behavior


# struct sigaction

- May differ slightly across architectures but these fields must
comply with POSIX

▪ sa_handler
    ▪ Pointer to an ANSI C handler function

▪ sa_sigaction
    ▪ Pointer to a POSIX handler function (next class)

▪ sa_mask
    ▪ This specifies a set of signals to be blocked while the handler runs (next class)

▪ sa_flags
    ▪ This specifies various flags which can affect the behavior of the signal


# The sa_flags field

-  The sa_flags field determines several different things, but
the important ones are:
    ▪ Whether we get the extended information (SA_SIGINFO)

    ▪ Whether system calls that were interrupted by the signal are automatically restarted (SA_RESTART)

    ▪ The alternative is that the interrupted system calls will fail, so restarting them is obviously a better approach

- Each signal number has its own set of flags
    ▪ Check out the manual pages

# Defining signal handlers

-  A signal handler is just a function that you compile together
with the rest of the program
    ▪ Instead of directly invoking the function, you use sigaction() to tell the OS to call it when a signal arrives

- You need to take special care in writing handler functions
because they can be called asynchronously
    ▪ That is, a handler might be called at any point in the program,
    unpredictably

    ▪ If two signals arrive during a very short interval, one handler can run within another

    ▪ (more on this in the next class)


# The sa_handler field

- Used to set an ANSI C handler function
    ▪ Minimal control over the behavior of signals during their handling

- Possible values:

    ▪ SIG_DFL for the default action

    ▪ SIG_IGN to ignore the signal
        ▪ Recall that SIGKILL and SIGSTOP cannot be ignored!

    ▪ void (*sa_handler)(int)
        ▪ Pointer to an ANSI C signal handler function that is executed upon signal reception
        ▪ This function receives the signal number as its only argument

# Example - ignoring SIGUSR1

/*(...)*/

int main(int argc, char *argv[])
{
    struct sigaction act;

    /*Zeroes the sigaction structure*/
    memset(&act,0,sizeof(struct sigaction));

    act.sa_handler = SIG_IGN;
    sigaction(SIGUSR1,&act,NULL);

    /*SIGUSR1 will now be ignored.*/
    /*(...)*/
}

