.section .data

.section .text
	.global update_grades
	
update_grades:
	addq $4, %rdi
	movq $0, %rdx
	
loop:
	movl (%rsi,%rdx,4), %eax
	movl %eax, (%rdi,%rdx,4)
	incq %rdx
	cmpq $5, %rdx
	jne loop
	
ret
