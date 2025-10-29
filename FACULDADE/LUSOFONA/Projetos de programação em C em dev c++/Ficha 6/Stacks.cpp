#include <stdio.h>
#include <stdlib.h>

//stack initialisation
#define MSIZE 14

int stack [MSIZE] ;   // create stack using an array
int top = -1;


int isempty();
int isfull();
int push(int data);
int pop();
int peek();

int main ()
{
	// insert (push) stack elements
	int x = 143;
	push(34);
	push(x);
	push(15);
	push(150);
	push(88);
	

	printf("\n\nPop and print stack elements: \n ");
	
		while(!isempty())
	{
		int data = pop();
		printf(" %d | ",data);
		
	}	
	printf("\n");
	
	//push new elements
	
	push(67);
	push(989);
	push(75);
	push(12);
	push(45);
	
	
	printf("\nStack Overflow " );
	
	printf("\nStack elements: \n");
	
		
}

int isempty()
{
	if (top == -1)
    	return 1;
	else
	    return 0;
	    
}

int isfull()
{
	if (top == MSIZE)
	     return 1; 
	else
	     return 0;     
}

int push(int data)
{
	if (!isfull())
	{
		top = top + 1;
		stack[top] = data;
			
	} else {
		   printf("Stack overflow\n");
	}
	
}


int pop()
{
	int data;
	if (!isempty())
	{
		data = stack[top];
		top = top - 1;
		return data;
	}
	 else
	 {
		   printf("Empty stack\n");
	 }
}



