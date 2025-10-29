#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

int stack[MAX_SIZE];
int top = -1;

void push(int value) {
    if (top == MAX_SIZE - 1) {
        printf("Error: Stack overflow\n");
        return;
    }
    top++;
    stack[top] = value;
}

void pop() {
    if (top == -1) {
        printf("Error: Stack underflow\n");
        return;
    }
    top--;
}

void display() {
    printf("The stack is: ");
    for (int i = top; i >= 0; i--)
        printf("%d ", stack[i]);
    printf("\n");
}

int main() {
    int value;
    char choice;

    while (1) {
        printf("Enter choice (p for push, o for pop, d for display, q for quit): ");
        scanf(" %c", &choice);

        if (choice == 'q')
            break;

        switch (choice) {
            case 'p':
                printf("Enter an integer value to push: ");
                scanf("%d", &value);
                push(value);
                break;
            case 'o':
                pop();
                break;
            case 'd':
                display();
                break;
            default:
                printf("Invalid choice\n");
                break;
        }
    }

    return 0;
}

