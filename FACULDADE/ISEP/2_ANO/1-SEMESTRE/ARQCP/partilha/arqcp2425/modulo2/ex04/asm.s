.section .data
    .global op1
    .global op2
    .global op3
    .global op4
	op3:
    .quad 3

	op4:
    .quad 4
   
.section .text
   
    .global yet_another_sum


 yet_another_sum:
 
	movq op4(%rip), %rax # rax = op4
    movq op3(%rip), %rcx # rcx = op3
    addq %rcx, %rax # rax = rax + rcx

    movslq op2(%rip), %rcx # rcx = op2
    subq %rcx, %rax # rax = rax - rcx
    subq %rcx, %rax # rax = rax - rcx

    movslq op1(%rip), %rcx # rcx = op1
    addq %rcx, %rax # rax = rax + rcx
    subq op4(%rip), %rax # rax = rax - op4


    ret
