.section .data 
cinco:
	.byte '5'
	
	.global cinco
	
.section .text
	.global five_count
	
five_count:
		movb cinco(%rip),%dl	
		movl $0, %eax			#counter
loop:		
		movb (%rdi),%cl
		cmpb %dl,%cl
		
		je igual
		
		cmpb $0,%cl
		je fim
		incq %rdi
		jmp loop
		
igual:
		incl %eax
		incq %rdi
		jmp loop
		
fim:
		ret
