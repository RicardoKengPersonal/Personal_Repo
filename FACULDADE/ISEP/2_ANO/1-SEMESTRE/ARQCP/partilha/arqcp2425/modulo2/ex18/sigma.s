.section .data
	.global n
A: 
	.int 3
B: 
	.int 4

.section .text

	.global sigma
	
	
sigma:
    movl  $0, %esi        # Initialize result to 0
    movl  $1, %ecx        # Initialize i to 1
    movl  n(%rip), %edi    # Copy n to %edi
    cmp %esi, %edi
    jle loop_end
    incl %edi

loop_start:
    # Calculate (i^2 * A^3) / B
    
   
     movl  A(%rip), %eax   # Load A into %edx
     
    imull %eax, %eax
    imull A(%rip), %eax
    
	cdq
    idivl B(%rip)
	 movl %ecx, %edx
   
    imull %edx, %edx
    imull %eax, %edx
    movl %edx, %eax
    
   # Add the result to the total sum
   
    addl %eax, %esi

    # Increment i
    
    addl $1,  %ecx 
    

    # Compare i with n (in %edi)
    
    cmpl  %ecx, %edi
    jne   loop_start       # If i <= n, continue the loop
 
loop_end:
   movl %esi, %eax	
   ret
