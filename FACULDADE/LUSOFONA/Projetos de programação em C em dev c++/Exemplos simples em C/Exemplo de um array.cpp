#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

int array[MAX_SIZE];
int size = 0;

void insert(int value) {
    if (size == MAX_SIZE) {
        printf("Error: Array is full\n");
        return;
    }
    array[size] = value;
    size++;
}

void display() {
    printf("The array is: ");
    for (int i = 0; i < size; i++)
        printf("%d ", array[i]);
    printf("\n");
}

int main() {
    int value;
    char choice;

    while (1) {
        printf("Enter choice (i for insert, d for display, q for quit): ");
        scanf(" %c", &choice);

        if (choice == 'q')
            break;

        switch (choice) {
            case 'i':
                printf("Enter an integer value to insert: ");
                scanf("%d", &value);
                insert(value);
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

