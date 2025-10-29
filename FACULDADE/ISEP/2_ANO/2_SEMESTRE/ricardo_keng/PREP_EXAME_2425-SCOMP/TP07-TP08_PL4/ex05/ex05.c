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

#define N_CHILDS 10
#define SIZE 1000
#define SECTION SIZE/10

typedef struct{
    int v[1000];
    int max[10];
} shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    pid_t pid[N_CHILDS];
    int start,end,local_max,global_max;

    /* creates/opens shared memory area */
    if((fd = shm_open("/shm_ex05",O_CREAT|O_RDWR,S_IRUSR|S_IWUSR)) == -1){
        perror("shmopen");
        exit(1);
    }

    /* defines size of shm */
    if(ftruncate(fd,sizeof(shared_data_t)) == -1){
        perror("ftruncate");
        exit(2);
    }

    /* maps shm into address space */
    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED){
        perror("mmap");
        exit(3);
    }

    srand(time(NULL));

    for (int i = 0; i < SIZE; i++)
    {
        shm->v[i] = rand() % 1000 + 1; // Random values 0-1000
    }


    for(int i = 0; i < N_CHILDS; i++)
    {
        start = i * SECTION;
        end = start + SECTION;

        pid[i] = fork();

        if (pid[i] == -1)
        {
            perror("Fork failed.\n");
            exit(EXIT_FAILURE);
        }

        if (pid[i] == 0 )
        {
            // child

            local_max = shm->v[start];

            for(int j = start; j < end; j++ )
            {
                if(shm->v[j] > local_max)
                {
                    local_max = shm->v[j];
                }
            }
            shm->max[i] = local_max;
            printf("Child %d local max is: %d\n",i,shm->max[i]);
            exit(0);
        }   
    }

    for(int i = 0; i < N_CHILDS; i++)
    {
        wait(NULL);
    }

    global_max = shm->max[0];

    for(int i = 0; i < N_CHILDS; i++)
    {
        printf("Child %d max : %d\n",i,shm->max[i]);
        if(shm->max[i] > global_max)
        {
            global_max = shm->max[i];
        }
    }

    printf("Global max is: %d\n",global_max);

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    /* removes shm */
    shm_unlink("/shm_ex05");

    return 0;
}