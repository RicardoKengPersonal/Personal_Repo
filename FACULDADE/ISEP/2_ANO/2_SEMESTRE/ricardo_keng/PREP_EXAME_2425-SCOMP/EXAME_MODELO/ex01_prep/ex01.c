#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#define NPROC 5

typedef struct {
    int num;
    char proprietario[20];
    int valido; // 1 = válido, 0 = inválido
} BilheteInfo;

int main() 
{
    int req_pipe[2]; // Pipe for requests (children -> parent)
    int resp_pipe[NPROC][2]; // Pipes for responses (parent -> each child)
    pid_t pid[NPROC];

    // Simulated ticket database
    BilheteInfo db[5] = 
    {
        {101, "Ricardo", 1},
        {102, "Ana", 1},
        {103, "Joao", 0},
        {104, "Maria", 1},
        {105, "Luis", 0}
    };

    if (pipe(req_pipe) == -1) 
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < NPROC; i++) 
    {
        if (pipe(resp_pipe[i]) == -1) 
        {
            perror("pipe");
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < NPROC; i++) 
    {
        pid[i] = fork();

        if (pid[i] == -1) 
        {
            perror("fork");
            exit(EXIT_FAILURE);
        }

        if (pid[i] == 0) 
        {
            // CHILD (scanner)
            close(req_pipe[0]); // Only writes to request pipe

            for (int j = 0; j < NPROC; j++) 
            {
                if (j != i) close(resp_pipe[j][0]);
                close(resp_pipe[j][1]);
            }
            // Simulate scanning a ticket (choose a ticket number to request)
            int ticket_to_check = db[i].num; // Each child asks for its own index
            write(req_pipe[1], &ticket_to_check, sizeof(int));

            // Wait for response from parent
            BilheteInfo resposta;
            read(resp_pipe[i][0], &resposta, sizeof(BilheteInfo));

            printf("[Scanner %d] Bilhete %d: Proprietario: %s | %s\n",
                   i, resposta.num, resposta.proprietario,
                   resposta.valido ? "Valido" : "Invalido");

            close(req_pipe[1]);
            close(resp_pipe[i][0]);
            exit(0);
        }
    }

    // PARENT
    close(req_pipe[1]); // Only reads requests

    for (int i = 0; i < NPROC; i++) 
    {
        close(resp_pipe[i][0]); // Only writes to response pipes
    }

    for (int i = 0; i < NPROC; i++) 
    {
        int requested_num;
        read(req_pipe[0], &requested_num, sizeof(int));
        // Find ticket in db
        BilheteInfo resposta = {0, "", 0};

        for (int j = 0; j < 5; j++) 
        {
            if (db[j].num == requested_num) 
            {
                resposta = db[j];
                break;
            }
        }
        // Send response to the correct child
        write(resp_pipe[i][1], &resposta, sizeof(BilheteInfo));
        close(resp_pipe[i][1]);
    }

    close(req_pipe[0]);

    for (int i = 0; i < NPROC; i++) 
    {
        wait(NULL);
    }
    return 0;
}