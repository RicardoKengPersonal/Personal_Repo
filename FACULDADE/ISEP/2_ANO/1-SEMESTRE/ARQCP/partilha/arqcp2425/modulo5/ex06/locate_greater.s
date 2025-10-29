.section .data

.section .text
	.global locate_greater
	
locate_greater:
	addq $4, %rdi
	movq $5, %rcx
	movl $0, %eax
inc_loop:
	cmpl (%rdi), %esi
	jge naoemaior
	incl %eax
	movl (%rdi), %r8d
	movl %r8d, (%rdx)	
	addq $4, %rdx
naoemaior:
	addq $4, %rdi

	loop inc_loop 
	
	ret
