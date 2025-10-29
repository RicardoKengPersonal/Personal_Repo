#include <stdio.h>
#include <stdbool.h>
#define MAX_SIZE 100

int stack1[MAX_SIZE];
int stack2[MAX_SIZE];
int top1 = -1;
int top2 = -1;

void push1(int x) {
    if (top1 < MAX_SIZE - 1) {
        top1++;
        stack1[top1] = x;
    } else {
        printf("Error: stack overflow\n");
    }
}

void push2(int x) {
    if (top2 < MAX_SIZE - 1) {
        top2++;
        stack2[top2] = x;
    } else {
        printf("Error: stack overflow\n");
    }
}

int pop1() {
    int x = -1;
    if (top1 >= 0) {
        x = stack1[top1];
        top1--;
    } else {
        printf("Error: stack underflow\n");
    }
    return x;
}

int pop2() {
    int x = -1;
    if (top2 >= 0) {
        x = stack2[top2];
        top2--;
    } else {
        printf("Error: stack underflow\n");
    }
    return x;
}

bool isPrime(int n) {
    if (n <= 1) return false;
    for (int i = 2; i < n; i++) {
        if (n % i == 0) return false;
    }
    return true;
}

int main() {
    int n1, n2;
    printf("Insira o limite superior: ");
    scanf("%d", &n1);
    printf("Insira o limite inferior: ");
    scanf("%d", &n2);
    

    for (int i = n1; i <= n2; i++) {
        if (isPrime(i)) {
            push1(i);
        } else {
            push2(i);
        }
    }

    printf("Numeros primos no intervalo entre %d e %d:\n ",n1,n2);
    while (top1 >= 0) 
	{
        printf(" | %d | ", pop1());
    }
    printf("\n");

    printf("Numeros nao primos no intervalo entre %d e %d:\n",n1,n2 );
    while (top2 >= 0) {
        printf(" | %d | ", pop2());
    }
    printf("\n");

    return 0;
}







