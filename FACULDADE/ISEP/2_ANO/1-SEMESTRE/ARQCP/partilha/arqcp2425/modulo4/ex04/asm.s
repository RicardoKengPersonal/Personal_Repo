# retorna a soma dos dois números, num1 e num2, e coloque a subtração dos dois (n1 − n2) na área de memória apontada por ptrsub

.section .text
    .global sum_sub

sum_sub:
	pushq %rbp              # prologue
    movq %rsp, %rbp
    
    movl %edi, %ecx
    movl %esi, %eax
    
    addl %ecx, %eax			# %eax = NUM1 + NUM2
    
    subl %esi, %ecx
    movl %ecx, (%rdx)
    
    movq %rbp, %rsp         # epilogue
    popq %rbp
    
    ret
