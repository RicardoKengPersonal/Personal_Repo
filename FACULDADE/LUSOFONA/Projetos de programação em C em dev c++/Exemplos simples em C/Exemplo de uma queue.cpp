#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

int queue[MAX_SIZE];
int front = -1;
int rear = -1;

void enqueue(int value) {
    if (rear == MAX_SIZE - 1) {
        printf("Error: Queue overflow\n");
        return;
    }
    if (front == -1)
        front = 0;
    rear++;
    queue[rear] = value;
}

void dequeue() {
    if (front == -1 || front > rear) {
        printf("Error: Queue underflow\n");
        return;
    }
    front++;
}

void display() {
    printf("The queue is: ");
    for (int i = front; i <= rear; i++)
        printf("%d ", queue[i]);
    printf("\n");
}

int main() {
    int value;
    char choice;

    while (1) {
        printf("Enter choice (e for enqueue, d for dequeue, s for display, q for quit): ");
        scanf(" %c", &choice);

        if (choice == 'q')
            break;

        switch (choice) {
            case 'e':
                printf("Enter an integer value to enqueue: ");
                scanf("%d", &value);
                enqueue(value);
                break;
            case 'd':
                dequeue();
                break;
            case 's':
                display();
                break;
            default:
                printf("Invalid choice\n");
                break;
        }
    }

    return 0;
}

