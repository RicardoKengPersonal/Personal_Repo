.section .data

.section .text
	.global return_unionB_b
	
return_unionB_b:
	movq (%rdi,%rsi,8), %rcx
	shlq $5, %rdx
	leaq (%rcx,%rdx,1), %r8
	movb 24(%r8), %al
	ret
