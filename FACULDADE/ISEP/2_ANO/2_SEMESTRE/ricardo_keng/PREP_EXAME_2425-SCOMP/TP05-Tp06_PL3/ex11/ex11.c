#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

#define CHILDS 5

typedef struct {
    char name[30];
    float price;
} product_info;

int main() {
    int shared_pipe[2];
    int individual_pipe[CHILDS][2];
    pid_t childs[CHILDS];

    if (pipe(shared_pipe) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < CHILDS; i++) {
        if (pipe(individual_pipe[i]) == -1) {
            perror("pipe");
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < CHILDS; i++) {
        childs[i] = fork();

        if (childs[i] == -1) {
            perror("fork");
            exit(EXIT_FAILURE);
        }

        if (childs[i] == 0) {
            // Child
            close(shared_pipe[0]); // close unused read-end
            close(individual_pipe[i][1]); // close write-end

            int product_id = i + 1;
            int child_id = i;

            printf("[CHILD %d] REQUESTED PRODUCT %d\n", i + 1, product_id);

            write(shared_pipe[1], &child_id, sizeof(int));
            write(shared_pipe[1], &product_id, sizeof(int));

            char product_name[30];
            float product_price;

            read(individual_pipe[i][0], &product_name, sizeof(product_name));
            read(individual_pipe[i][0], &product_price, sizeof(float));

            close(individual_pipe[i][0]);

            printf("[CHILD %d] RECEIVED FROM PIPE: %s, %.2f\n", i + 1, product_name, product_price);
            exit(0);
        }
    }

    // Parent
    close(shared_pipe[1]); // close write-end after all forks

    product_info info[5] = {
        {"iogurte", 3.49},
        {"pao", 2.20},
        {"queijo", 3.00},
        {"fiambre", 5.45},
        {"banana", 7.98}
    };

    for (int i = 0; i < CHILDS; i++) {
        int child_id, product_id;
        char product_name[30];
        float product_price;

        read(shared_pipe[0], &child_id, sizeof(int));
        read(shared_pipe[0], &product_id, sizeof(int));

        strcpy(product_name, info[product_id - 1].name);
        product_price = info[product_id - 1].price;

        write(individual_pipe[child_id][1], &product_name, sizeof(product_name));
        write(individual_pipe[child_id][1], &product_price, sizeof(float));

        close(individual_pipe[child_id][1]);
    }

    close(shared_pipe[0]);

    for (int i = 0; i < CHILDS; i++) {
        wait(NULL);
    }

    return 0;
}
