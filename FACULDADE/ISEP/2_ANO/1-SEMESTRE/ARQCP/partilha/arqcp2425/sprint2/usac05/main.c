#include <stdio.h>
#include "asm.h"

int main(void) {
    int buffer[] = {0,0,0,0};    
    int length = 4;         
    int tail = 3;           
    int head = 3;
    int value = 5;

	printf("\nO buffer original é: %d", buffer[0]);
    
	for (int i = 1; i < length; i++) {
		
		printf(", %d", buffer[i]);
            
    }
    
    int res =  enqueue_value(buffer, length, &tail, &head, value) ;
    printf("\n%d", res);
    printf("\nO buffer alterado é: %d", buffer[0]);
    
	for (int i = 1; i < length; i++) {
			
			printf(", %d", buffer[i]);
            
    }
    
    printf("\n");

    return 0;
}
