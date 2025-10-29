#include<unistd.h>
#include<sys/types.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct {
    int number;
    char name[80];
    int can_read_flag; // This flag can be used to indicate if the data is ready to be read
} shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;

    /* creates/opens shared memory area */
    // Note: The shm_open function creates a new shared memory object or opens an existing one
    // The first argument is the name of the shared memory object, which must start with a slash ('/').
    // The second argument specifies the flags for creating or opening the object.
    // The third argument specifies the permissions for the object.
    // The O_CREAT flag indicates that the object should be created if it does not exist.
    // The O_RDWR flag indicates that the object should be opened for reading and writing.
    // The S_IRUSR and S_IWUSR flags indicate that the owner of the object
    // has read and write permissions, respectively.
    // The shm_open function returns a file descriptor for the shared memory object,
    // which can be used to perform operations on the object.
    // If the function fails, it returns -1 and sets errno to indicate the error.
    if((fd = shm_open("/shm_ex01", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shm_open");
        exit(1);
    }

    /* defines size of shm */
    // The ftruncate function is used to set the size of the shared memory object.
    // It takes three arguments: the file descriptor of the shared memory object,
    // the new size of the object, and the mode (which is not used in this case).
    // The function returns 0 on success and -1 on failure, setting errno to indicate the error.
    // If the size is set to a value smaller than the current size of the object,
    // the excess data is discarded, and if the size is set to a value
    // larger than the current size, the object is extended with zero-filled bytes.
    // In this case, the size of the shared memory object is set to the size of
    // the shared_data_t structure, which is defined to contain an integer and a character array
    // of size 80.
    if(ftruncate(fd, sizeof(shared_data_t)) == -1) {
        perror("ftruncate");
        exit(2);
    }

    /* maps shm into address space */
    // The mmap function is used to map the shared memory object into the address space of the
    // process. It takes five arguments: the starting address for the mapping (0 means the
    // kernel chooses the address), the length of the mapping (the size of the shared_data_t structure),
    // the protection flags (PROT_READ and PROT_WRITE indicate that the mapping
    // should be readable and writable), the flags for the mapping (MAP_SHARED indicates that
    // the mapping should be shared with other processes), the file descriptor of the shared memory object
    // (fd), and the offset within the object (0 means the mapping starts at the beginning).
    // The function returns a pointer to the mapped memory on success and MAP_FAILED on failure
    // (which is defined as ((void *)-1)). If the mapping is successful,
    if((shm = (shared_data_t *)mmap(0, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0)) == MAP_FAILED) {
        perror("mmap");
        exit(3);
    }

    shm->can_read_flag = 0; // Initialize the flag to indicate that data is not ready yet

    printf("Writer: Enter a number: ");

    if (scanf("%d", &shm->number) != 1) 
    {
        fprintf(stderr, "Failed to read number\n");
        exit(4);
    }

    getchar(); // Clear the newline character from the input buffer

    printf("Writer: Enter a name: ");
    if (fgets(shm->name, sizeof(shm->name), stdin) == NULL) {
        shm->name[strcspn(shm->name, "\n")] = '\0';
        fprintf(stderr, "Failed to read name\n");
        exit(5);
    }

    shm->can_read_flag = 1; // Set the flag to indicate that data is ready to be read

    printf("Writer: wrote number and name in shm\n");
    printf("Writer: Number: %d, Name: %s\n", shm->number, shm->name);

    return 0;
}