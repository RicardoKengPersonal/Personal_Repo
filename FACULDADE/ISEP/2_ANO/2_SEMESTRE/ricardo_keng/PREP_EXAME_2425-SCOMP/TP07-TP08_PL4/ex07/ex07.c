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

#define STR_SIZE 50
#define NR_COURSE 10
#define N_CHILDS 2


typedef struct{
    int number;
    char name[STR_SIZE];
    int courses[NR_COURSE];
    int available;
}shared_data_t;

int main()
{
    int fd;
    shared_data_t *shm;
    pid_t pid[N_CHILDS];

    if((fd = shm_open("/shm_ex07",O_CREAT|O_RDWR,S_IWUSR|S_IRUSR)) == -1)
    {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }

    if((ftruncate(fd,sizeof(shared_data_t)))==-1)
    {
        perror("ftruncate");
        exit(EXIT_FAILURE);
    }

    if((shm = (shared_data_t*)mmap(0,sizeof(shared_data_t),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0))== MAP_FAILED)
    {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    shm->available = 0;

    // Parent
    printf("[Father]: Enter the student name:");
    if (fgets(shm->name, sizeof(shm->name), stdin) == NULL) {
        fprintf(stderr, "Failed to read name\n");
        exit(1);
    }

    printf("[Father]: Enter the student number:");
    if (scanf("%d", &shm->number) != 1) {
        fprintf(stderr, "Failed to read number\n");
        exit(1);
    }

    for(int j = 0; j < NR_COURSE; j++) {
        printf("[Father]: Course %d grade:", j);
    if (scanf("%d", &shm->courses[j]) != 1) {
        fprintf(stderr, "Failed to read course grade\n");
        exit(1);
    }
    }

    shm->available = 1;

    for(int i = 0; i < N_CHILDS; i++)
    {
        pid[i] = fork();

        if(pid[i] == -1)
        {
            perror("Fork");
            exit(EXIT_FAILURE);
        }

        if(pid[i] == 0)
        {
            while(shm->available != 1)
            {
                sleep(1);
            }

            if(i == 0)
            {
                // First child
                int local_max = shm->courses[0];
                int local_min = shm->courses[0];

                for(int k = 0; k < NR_COURSE; k++)
                {
                    if (local_max < shm->courses[k])
                    {
                        local_max = shm->courses[k];
                    } else {
                        local_max = local_max;
                    }
                }

                for(int p = 0; p < NR_COURSE; p++)
                {
                    if(local_min > shm->courses[p])
                    {
                        local_min = shm->courses[p];
                    } else {
                        local_min = local_min;
                    }
                }

                printf("[Child %d]: \nMAX: %d, MIN = %d\n",i,local_max,local_min);

            } else {
                float avg = 0;
                for(int k = 0; k < NR_COURSE; k++)
                {
                    avg += shm->courses[k];
                }

                avg = avg / (float)NR_COURSE;
                printf("[Child %d] \nAVG is: %.2f\n",i,avg);
            }

            exit(0);

        } 
    }

    for(int i = 0; i < N_CHILDS; i++)
    {
        wait(NULL);
    }

    close(fd);
    munmap(shm,sizeof(shared_data_t));

    shm_unlink("/shm_ex07");

    exit(0);
}