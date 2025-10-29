#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void M(char *s) {
    printf("%s\n", s);
}

int main() {
    for (int i = 0; i < 2; i++) {
        M("A");  // All processes print A

        pid_t pid = fork();
        if (pid < 0) {
            perror("Fork failed");
            exit(1);
        }

        if (pid == 0) {
            // Child
            M("B");

            pid_t pid2 = fork();
            if (pid2 < 0) {
                perror("Fork failed");
                exit(1);
            }

            if (pid2 == 0) {
                // Grandchild
                M("A");
            } else {
                wait(NULL); // Wait for grandchild
            }

            // Do NOT exit! Let the child and grandchild continue to next iteration
        } else {
            wait(NULL); // Wait for child
        }
    }

    return 0;
}
