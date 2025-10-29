.section .text
    .global count_even
	count_even:	
		movq $0, %rax
		movq $0, %r8
		
		cmpl $0, %esi
		je end
		
		movl %esi, %ecx
		movq $0, %r8
		
	ciclo:
		movswl (%rdi), %eax
		andl $1, %eax
		
		cmpl $0, %eax
		je incrementa_contador
		
		jmp proximo_elem
		
	incrementa_contador:
		incq %r8
		
	proximo_elem:
		addq $4, %rdi
		
		cmp $0, %ecx
		je end
		
		loop ciclo
	
	end:
		movq %r8, %rax
		ret
