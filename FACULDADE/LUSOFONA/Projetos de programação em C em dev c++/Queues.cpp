#include <stdio.h>
#include <stdlib.h>
#define MAX 100
int queueArray[MAX];
int rear = - 1;
int front = - 1;
void insertQ();
void deleteQ();
void printQ();
int main()
{
int op;
while (1) // while true
{
printf("\n\n1 - Inserir elemento na fila (queue)");
printf("\n2 - Eliminar elemento da fila (queue)");
printf("\n3 - Ver elementos da fila (queue)");
printf("\n0 - Sair");
printf("\n\nOpcao: ");
scanf("%d", &op);
switch(op)
{
case 1:
insertQ();
break;
case 2:
deleteQ();
break;
case 3:
printQ();
break;
case 0:
exit(1);
default:
printf("\nOpcao invalida");
}
}
}
void insertQ()
{
int item;
if (rear == MAX - 1)
printf("\nQueue Overflow");
else
{
if (front == - 1)
front = 0;
printf("\nInserir novo elemento na fila: ");
scanf("%d", &item);
rear = rear + 1;
queueArray[rear] = item;
}
}
void deleteQ()
{
if (front == - 1 || front > rear)
{
printf("\nQueue Underflow");
return;
}
else
{
printf("\nElemento eliminado da fila: %d",
queueArray[front]);
if (front == rear)
{
front = -1;
rear = -1;
}
else
front = front + 1;
}
}
void printQ()
{
int i;
if (front == - 1 || front > rear)
printf("\nEmpty Queue");
else
{
printf("\nElementos da fila (Queue): ");
for (i = front; i <= rear; i++)
printf("| %d ", queueArray[i]);
printf("|");
}
}
