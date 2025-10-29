#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/mman.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

#define N_CHILDS 5

typedef struct{
    char message[100];
    int turn;
} shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    pid_t pid[5];

    if((fd = shm_open("/shm_ex06",O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) == -1)
    {
        perror("Shm_open");
        exit(EXIT_FAILURE);
    }

    if((ftruncate(fd,sizeof(shared_data_t))) == -1)
    {
        perror("Ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ | PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    shm->turn = 0;
    shm->message[0] = '\0'; 

    for(int i = 0; i < N_CHILDS; i++)
    {
        pid[i] = fork();

        if(pid[i] == -1)
        {
            perror("Fork failed");
            exit(EXIT_FAILURE);
        }

        if(pid[i] == 0)
        {
            while(shm->turn != i)
            {
                sleep(1);
            }

            printf("[Child %d]: Write a word to append to the message: %s\n",i,shm->message);
            char word[20];
            
            if (scanf("%19s", word) != 1) {
                fprintf(stderr, "Failed to read word\n");
                exit(1);
            }

            if(strlen(shm->message) > 0)
            {
                strcat(shm->message," ");
            }
            strcat(shm->message, word);
            shm->turn++;
            exit(0);
        }
    }

    for(int i = 0; i < N_CHILDS; i++)
    {
        wait(NULL);
    }

    printf("[Father]: The final message is: %s\n",shm->message);

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    shm_unlink("/shm_ex06");

    exit(0);

}