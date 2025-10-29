.section .text
    .global sum2_n

sum2_n:
    pushq %rbp              # prologue
    movq %rsp, %rbp
	
	movslq %edi, %rdi
	movslq %eax, %rax
    movq $0, %rax

loop:
    cmpq $0, %rdi
    jle quadrado            # Jump to quadrado if n is less than or equal to 0
    addq %rdi, %rax
    decq %rdi               
    jmp loop

quadrado:
    imulq %rax, %rax        

    movq %rbp, %rsp         # epilogue
    popq %rbp

    ret
