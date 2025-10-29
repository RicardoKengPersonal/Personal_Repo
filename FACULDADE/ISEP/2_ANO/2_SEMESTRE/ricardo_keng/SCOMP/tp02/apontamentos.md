# Exec functions: 
- Set of functions that allow for a process to execute a
completely new program

    - These functions replace the image of a process with the image of a different program

- The program being executed is replaced, but the process is still
the same
    ▪ Same PID, same open files, …
    ▪ However, signals are reset to their default handlers

# Why are exec functions important?

- Exec functions replace the current process image with a new one
- Unlike fork(), which creates a new process, exec keeps the same PID but loads a different program
- It is fundamental in UNIX-based systems for process management


# Typical uses of exec functions
▪ Launching new programs
▪ Implementing shell commands
▪ Replacing a child process’ code after fork()
▪ Executing a different program within the same process

# Important!
- The process does not return to the previous program because
its code is entirely replaced

- The original program will only continue if the exec function
encounters an error on its setup

- A successful execution of an exec function does not allow any
return to the previous code



Typical use of exec functions:

- A child process is created using fork()
- The exec() function is called in the child process to replace its  image
- The parent process waits for the child’s termination using
wait() or waitpid()