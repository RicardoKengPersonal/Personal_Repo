.section .data
	.global num
	
.section .text
	.global steps
	
steps:
		movq num(%rip),%rax
		cqo
		movq $3,%rcx
		idivq %rcx				#a)
		
		addq $6,%rax			#b)
		
		imulq $3,%rax			#c
		
		addq $12,%rax			#d
		
		movq num(%rip),%rcx
		subq %rcx,%rax			#e
		
		subq $4,%rax			#f
				
		ret
		
