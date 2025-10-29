.section .data
    .global op1
    .global op2
	.equ CONST, 20

.section .text
   
    .global another_sum


another_sum:
    movl $CONST, %edx      # Load constant 20 into edx
    movl op1(%rip), %ecx        # Load op1 into ecx
    subl %ecx, %edx             # Subtract op1 from constant and store the result in edx

    movl $CONST, %esi      # Load constant 20 into esi
    movl op2(%rip), %eax        # Load op2 into eax
    subl %eax, %esi             # Subtract op2 from constant and store the result in esi

    addl %esi, %edx             # Add the results together in edx
    addl $CONST, %edx      # Add constant to the previous result in edx

    movslq %edx, %rax             # Copy the result from edx to rax for the return value


    ret
