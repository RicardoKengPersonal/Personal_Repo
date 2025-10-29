#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#define STACK_SIZE 100

typedef struct _stack
{
    char *mem;
    size_t size;
    int top;
} STACK;

STACK *stack_create(char *mem, size_t size);
void stack_push(STACK *stack, char c);
char stack_pop(STACK *stack);
char stack_peek(STACK *const stack);
void stack_print(STACK *stack);
int main() {
    char stack_mem[STACK_SIZE];

    STACK *my_stack = stack_create(stack_mem, STACK_SIZE);
    stack_push(my_stack, 'a');
    stack_push(my_stack, 'b');
    stack_push(my_stack, 'c');
    stack_push(my_stack, 'd');
    stack_print(my_stack);
    printf("Top of stack contains: %c\n", stack_peek(my_stack));
    stack_pop(my_stack);
    stack_print(my_stack);
    stack_pop(my_stack);
    stack_pop(my_stack);
    stack_pop(my_stack);
    stack_push(my_stack, 'j');
    stack_push(my_stack, 'k');
    stack_print(my_stack);

    free(my_stack);
    return EXIT_SUCCESS;
}



STACK *stack_create(char *mem, size_t size) {

    assert(mem && size);

    STACK *output = malloc(sizeof(STACK));

    output->mem = mem;
    output->size = size;
    output->top = -1;
    return output;
}

void stack_push(STACK *stack, char c)
{
    assert(stack);

    if(c < 0)
    {
        fprintf(stderr, "This stack is not designed for negative char values\n");
        return;
    }
    if((stack->top + 1) < stack->size)
    {
        stack->mem[stack->top + 1] = c;
        stack->top++;
    }
    return;
}

char stack_pop(STACK *stack)
{
    assert(stack);

    if(stack-> top < 0)
    {
        fprintf(stderr, "Cannot pop from empty stack.\n");
        return -1;
    }

    char o;
    o = stack->mem[stack->top];
    stack->top--;
    return o;
}

char stack_peek(STACK * const stack)
{
    assert(stack);

    if(stack->top < 0)
    {
        fprintf(stderr, "Nothing on stack to peek at.\n");
        return -1;
    }
    return stack->mem[stack->top];
}

void stack_print(STACK *stack)
{
    assert(stack);
    if(!stack->top)
    {
        fprintf(stderr, "Nothing on stack to print.\n");
        return;
    }
    printf("Entire stack:\n");
    int i;
    for(i = stack->top; i >= 0; --i)
    {
        printf("%c\n", stack->mem[i]);
    }
}
