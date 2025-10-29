.section .text
    .global greatest
	greatest:
		# prologue
		pushq %rbp 				# Guarda o valor original do %rbp
		movq %rsp, %rbp 		# Copia o atual stack pointer para o %rbp 
		
		movq $0, %rax
		
		subq $4, %rsp
		movw %di, -4(%rbp)
		
	compare_second:
		cmpw -4(%rbp), %si
		jg update_greatest_b
		
	compare_third:		
		cmpw -4(%rbp), %dx
		jg update_greatest_c
		
		jmp end
		
	update_greatest_b:
		movw %si, -4(%rbp)
		jmp compare_third
	
	update_greatest_c:
		movw %dx, -4(%rbp)
		jmp end
		
	end:
		movw -4(%rbp), %ax
		
		# epilogue
		movq %rbp, %rsp
		popq %rbp
		ret
