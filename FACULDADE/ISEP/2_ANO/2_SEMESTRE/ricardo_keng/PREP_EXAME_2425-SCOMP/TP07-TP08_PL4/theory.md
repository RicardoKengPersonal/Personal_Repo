Shared memory
 Processes operate in isolation
▪ Each process has its own memory space, preventing direct access to
another process's memory
 This restriction can be inconvenient when processes need to
exchange data with increased efficiency and flexibility
 Shared memory as a solution
▪ A designated shared memory region allows multiple processes to access
and modify the same data, enabling fast inter-process communication (IPC)

Shared memory
 Memory region which is shared by several processes
▪ Processes with or without a hierarchical link can use this region to
communicate

Advantages
 Random access
▪ It is possible to directly access and change any part of the region
 Low latency
▪ Accesses are direct (contrarily to pipes, for instance), without the
intervention of the OS
 High performance
▪ It is the fastest communication mechanism between processes

Advantages
 Scalability
▪ Suitable for high-throughput applications that require frequent interprocess communication
 Flexibility
▪ Allows multiple processes to read and write simultaneously (with proper
synchronization mechanisms)

Disadvantages
 Complex synchronization
▪ Requires mechanisms like semaphores or mutexes to prevent race
conditions and data corruption
 Debugging challenges
▪ Harder to trace errors due to direct memory manipulation by multiple
processes
 Memory management overhead
▪ Requires explicit allocation and deallocation, increasing complexity
▪ Implementation may vary across OSes, making cross-platform
development more challenging

POSIX shared memory
 Implemented as temporary files
▪ On Linux, makes use of a dedicated tmpfs filesystem that is normally
mounted under /dev/shm
 Persistent
▪ Exists until explicitly deleted, or system reboot
 A process may map a shared memory area (in POSIX referred
frequently as an object), make changes, and disconnect
▪ These changes are visible for any process that maps this shared memory
object

The /dev/shm directory
 In Linux, the temporary file system (tmpfs) used for POSIX
shared memory is mapped in /dev/shm
▪ POSIX semaphores (next classes) are also mapped here
 Shared memory objects can be listed with ls and removed
with rm, as files

POSIX shared memory
1. Create/open
▪ shm_open(), ftruncate(), mmap()
2. Use the shared memory area
▪ With a pointer, similarly to any dynamic memory block
3. Remove (next class)
▪ munmap(), close(), shm_unlink()

POSIX shared memory
 Usual headers:
▪ <fcntl.h> - File control options (e.g., O_CREAT, O_RDWR)
▪ <sys/types.h> - Types used by system APIs
▪ <sys/stat.h> - Constants for file permissions and opening modes
▪ <sys/mman.h> - Declarations for shared memory management (e.g.,
shm_* and mmap())
 Functions related to shared memory are part of the POSIX realtime library (librt)
▪ To compile and link correctly, you must explicitly link against librt

$ gcc -Wall shm_test.c -o shm_test -lrt

Create/open a shared memory area
1. Use the shm_open() function
▪ Creates and opens (or if already exists, just opens) a shared memory area
▪ Returns a file descriptor to be used in subsequent functions
2. Use the ftruncate() function
▪ Defines the size of the shared memory area
3. Use the mmap() function
▪ Maps the shared area into the process’ address space


The shm_open function

#include <sys/mman.h>
#include <sys/stat.h> /* For constants “mode” */
#include <fcntl.h> /* For constants O_* */
int shm_open(const char *name, int oflag, mode_t mode);

 Creates and opens a new shared memory area, or just opens an
existing one
 Returns
▪ A file descriptor to the shared memory
▪ -1, in case of error

 The name parameter is the name to be used to identify the
shared memory area
▪ Must start with ‘/’
▪ On Linux, a new file is created under /dev/shm

 Creation flags
▪ O_CREAT: creates, if it not exists, or use existing one
▪ O_EXCL: (with O_CREAT) just creates, error if already exists
▪ O_TRUNC: if the shared memory object already exists, truncate it to zero
bytes
 Access flags
▪ O_RDONLY: open for reading only
▪ O_RDWR: open for reading and writing (no flag to just write)

 The mode parameter specifies the permissions of the shared
memory object only when it is created
▪ If the object already exists, this parameter is ignored
▪ The mode parameter is only relevant if O_CREAT is used
 RWX for user/group/others
▪ Common value for you is S_IRUSR|S_IWUSR
▪ Should be 0 (zero) if opening an existing area, since it has no effect

The ftruncate function

#include <unistd.h>
#include <sys/types.h>
int ftruncate(int fd, off_t length);

 Defines the size of the shared memory area and initializes the
new space to zero
▪ Must be used after shm_open(), as memory block is created with zero
size
 If the area already exists, only changes if new size is different
▪ If new size is greater than existing size, expands the area, filling the new
space with zeros
▪ If new size is less than existing size, shrinks the area, discarding extra data

 The fd parameter is the file descriptor returned by
shm_open()
 The length parameter is the size, in bytes, of the shared
memory area
 Returns
▪ 0, if successful
▪ -1, in case of error

The mmap function

#include <sys/mman.h>
void *mmap(void *addr, size_t length, int prot, int flags,
 int fd, off_t offset);

  Maps a shared memory object into the process’ address space
▪ In practice, you are required to handle arguments length and prot
 Returns
▪ A pointer to the object
▪ MAP_FAILED ((void *) -1), in case of error

The addr parameter allows to request the area to be mapped
in a specific address
▪ NULL in the usual case to let the OS define the address
 The length parameter is the required size to be used
▪ Must be less than or equal to the size defined in ftruncate()
▪ OS usually rounds to a multiple of the size of a memory page

 The prot parameter specifies the protection flags
▪ PROT_READ: just read
▪ PROT_READ | PROT_WRITE: read and write
 Must be consistent with shm_open()
▪ Do not use O_RDONLY in shm_open() and then PROT_READ |
PROT_WRITE in mmap()

 The flags parameter controls the behavior of mmap()
▪ When using shared memory, always specify MAP_SHARED to ensure that
modifications are visible to other processes
 fd is the file descriptor returned by shm_open()
 offset defines the starting point of the mapping within the
memory area
▪ It is typically set to zero to map the entire memory region from the
beginning
▪ If not, it must always be a multiple of the memory page size

## Example – create and open

int fd;
void * addr;
fd = shm_open(“/shmtest”, O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
ftruncate (fd, 100);
addr = mmap(NULL, 100,PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);

1. Creates (O_CREAT) a shared memory area named
"/shmtest" with read/write permissions (O_RDWR)
▪ The user is granted read (S_IRUSR) and write (S_IWUSR) access
▪ If the shared memory segment already exists, the function returns an error
due to the use of the O_EXCL flag, which ensures exclusive creation
2. Sets the shared memory area size to 100 bytes

3. Maps the shared memory region at an address chosen by the
OS (NULL), using the entire size (100)
▪ It grants read and write permissions (PROT_READ | PROT_WRITE),
ensuring consistency with the open mode
▪ The memory is shared (MAP_SHARED), linked to the previously opened
file descriptor (fd)
▪ The mapping starts from the beginning (offset = 0), covering the full
allocated space

How to use
 Use as a normal dynamic memory block
▪ The pointer returned by mmap() can be used just like a pointer returned
by malloc()
▪ It is common to define a structured data layout for the shared memory
region
 Concurrent access to shared data may result in data
inconsistency
▪ Maintaining data consistency requires synchronization mechanisms to
ensure the orderly execution of cooperating processes

Manual synchronization
 Next example:
▪ Shared memory between unrelated processes (no parent–child
relationship)
▪ Reading and writing within a structured data format
 No synchronization in the code!
▪ Demands manual synchronization, by manually executing the writer first
▪ Recall that once data is written to the shared memory, it remains
accessible to any process that maps the region
▪ The reader can retrieve and interpret the written data even after the
writer has finished its operation

## Example – writer

typedef struct{
 int var1;
 int var2;
}shared_data_type;
int main(){
 int fd, data_size = sizeof(shared_data_type);
 shared_data_type *shared_data;
 fd = shm_open(“/shmtest”, O_CREAT|O_EXCL|O_RDWR,
 S_IRUSR|S_IWUSR);
 ftruncate (fd, data_size);
 shared_data = (shared_data_type*)mmap(NULL, data_size,
 PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
 /* write */
 shared_data->var1 = 100;
 shared_data->var2 = 200;
}

## Example – reader

ypedef struct{
 int var1;
 int var2;
}shared_data_type;
int main(){
 int fd, data_size = sizeof(shared_data_type);
 shared_data_type *shared_data;
 int fd;
 /* shm_open without creation flags, only access flag */
 fd = shm_open(“/shmtest”, O_RDWR, 0);
 shared_data = (shared_data_type *)mmap(NULL, data_size,
 PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
 /* read */
 printf(“%d\n”, shared_data->var1);
 printf(“%d\n”, shared_data->var2);
}

Synchronization with active waiting
 Next example with synchronization with active waiting (also
called busy waiting)
▪ In this method, a process continuously checks a condition in a loop until it
becomes true
▪ (Passive mechanisms will be discussed in future classes)
 Processes are related
▪ Parent is the writer and child is the reader, illustrating how data can be
passed between them using active waiting

## Example with synchronization 

typedef struct {
 int var1;
 int var2;
 int new_data; /* used for synchronized read/write operations */
}shared_data_type;
int main(){
 int fd, data_size = sizeof(shared_data_type);
 shared_data_type *shared_data;

 fd = shm_open(“/shmtest”, O_CREAT|O_EXCL|O_RDWR,
S_IRUSR|S_IWUSR);
 ftruncate (fd, data_size);
 shared_data = (shared_data_type *)mmap(NULL, data_size,
 PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
 shared_data->new_data = 0; /* Is it really needed? */
 pid=fork();

 if (pid > 0){ /* writer */
 shared_data->var1 = 100;
 shared_data->var2 = 200;
 shared_data->new_data = 1;
 wait(NULL);
 }
 else{ /* reader */
 while(!shared_data->new_data);
 printf(“%d\n”, shared_data->var1);
 printf(“%d\n”, shared_data->var2);
 }
 return 0;
}


## TP08

Create/open a shared memory area
 As we discussed in the previous class, these are the typical
steps involved in creating or opening a shared memory area
1. Use the shm_open() function
▪ Creates and opens (or if already exists, just opens) a shared memory area
▪ Returns a file descriptor to be used in subsequent functions
2. Use the ftruncate() function
▪ Defines the size of the shared memory area
3. Use the mmap() function
▪ Maps the shared area into the process’ address space

Using the shared memory area
 For applications that exchange large amounts of data, shared
memory is faster and more flexible than message-passing
techniques like message queues or pipes
▪ Which require system calls for every data exchange, introducing overhead
and reducing throughput, especially under high-frequency communication
 The major disadvantage of is that the processes must take
extra precaution to synchronize access to the region to avoid
race conditions and data corruption
▪ Which adds complexity to the application design

Detach and remove
1. The munmap() function
▪ Detaches the shared memory region from the process’s address space
(opposite of mmap())
▪ Use it after the process no longer needs access to the shared memory
2. The close() function
▪ Closes the file descriptor obtained via shm_open()
▪ This does NOT remove the shared memory, only the file descriptor is
closed
3. The shm_unlink() function
▪ Removes the shared memory object from the file system namespace
▪ Marks the memory for deletion; Deletion occurs only after all processes
have closed the shared memory

The munmap function

#include <sys/mman.h>
int munmap(void *addr, size_t length);

 Disconnects the shared memory area from the process address
space
 Returns
▪ 0, if successful
▪ -1, in case of error

 The addr parameter is the pointer returned by mmap()
▪ Indicating the starting address of the mapped memory region
 length must match the size of the mapped region
▪ If it's smaller, only part of the mapping is removed, which might lead to
undefined behavior

The close function
#include <unistd.h>
int close(int fd);

 Closes the file descriptor associated with an open shared
memory object
 fd is the file descriptor returned by shm_open()
 Returns
▪ 0, if successful
▪ -1, in case of error

The shm_unlink function

#include <sys/mman.h>
int shm_unlink(const char *name);

 Removes the shared memory object from the file system
▪ Not immediate, if any process is still using the shared memory
▪ Final deletion occurs only after all processes have closed their file
descriptors and unmapped the shared memory
 name is the shared memory name used in shm_open()
 Returns
▪ 0, if successful
▪ -1, in case of error 

## Example – close a shared memory area

 A process should close its shared memory descriptor as soon as
it no longer needs it
▪ Other processes can still open and access the shared memory
independently

int fd;
 void *addr; /* pointer to shared memory */
 fd = shm_open(”/shmtest”, ...);
 ftruncate (fd, 100);
 addr = mmap(NULL, 100, ...);
 ... /* use – read/write */
 munmap(addr, 100); /* disconnects from shared memory */
 close(fd); /* closes file descriptor */
 exit(0); 

 ## Example – remove a shared memory area

 A process removes the shared memory object
▪ After a successful shm_unlink(), attempts to shm_open() an object
with the same name fail (unless O_CREAT was specified, in which case a
new, distinct object is created)

int fd;
 void *addr; /* pointer to shared memory */
 fd = shm_open(”/shmtest”, ...);
 ftruncate (fd, 100);
 addr = mmap(NULL, 100, ...);
 ... /* use – read/write */
 munmap(addr, 100); /* disconnects from shared memory */
 close(fd); /* closes file descriptor */
 shm_unlink(”/shmtest”); /* removes from system */
 exit(0);

 Proper cleanup of POSIX shared memory
 Why proper cleanup matters?
▪ Prevents memory leaks and orphaned shared memory objects
▪ Ensures shared memory is fully deallocated when no longer needed
▪ Avoids unexpected behavior when processes terminate
 Key takeaways
▪ Always use munmap() first, then close() the descriptor, and finally
remove the shared memory area with shm_unlink()
▪ Failure to shm_unlink() may leave unused objects in /dev/shm/,
leading to resource exhaustion
▪ If a process terminates unexpectedly, shared memory may persist until
manually unlinked

The fstat function

#include <sys/stat.h>
int fstat(int fd, struct stat *buf);

 Retrieves metadata about a shared memory object using its file
descriptor
 Common uses:
▪ Determining the size of a shared memory object (especially if created by
another process)
▪ Verifying permissions before mapping
▪ Monitoring changes in shared memory attributes

fd is the file descriptor returned from shm_open()
 buf is the address of a stat structure where information will
be placed
▪ The structure will have info on the area, such as size, permissions, owner,
etc.
 Returns
▪ 0, if successful
▪ -1, in case of error 

The stat structure

struct stat {
 dev_t st_dev; /* ID of device containing file */
 ino_t st_ino; /* inode number */
 mode_t st_mode; /* protection */
 nlink_t st_nlink; /* number of hard links */
 uid_t st_uid; /* user ID of owner */
 gid_t st_gid; /* group ID of owner */
 dev_t st_rdev; /* device ID (if special file) */
 off_t st_size; /* total size, in bytes */
 blksize_t st_blksize; /* blocksize for file system I/O */
 blkcnt_t st_blocks; /* number of 512B blocks allocated */
 time_t st_atime; /* time of last access */
 time_t st_mtime; /* time of last modification */
 time_t st_ctime; /* time of last status change */
 };

 ## Example – obtain information


int main(){
 int fd, ret;
 struct stat shm_stat;
 fd = shm_open(“/shmtest”, O_CREAT|O_EXCL|O_RDWR, 0600);
 if(fd < 0) exit(1);
 ftruncate (fd, 100);
 addr = mmap(NULL,100,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
 if(addr == MAP_FAILED) exit(1);
 if(fstat(fd, &shm_stat) < 0) exit (1);
 printf("mode = %d\n", shm_stat.st_mode);
 printf("size = %ld\n", shm_stat.st_size);
 ...
}