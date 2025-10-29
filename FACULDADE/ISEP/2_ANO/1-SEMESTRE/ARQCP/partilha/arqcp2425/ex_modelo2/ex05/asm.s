.section .data

.section .text
		.global fn

fn:
		movb (%rdi), %sil 	#primeiro byte, index 0
		movsbl %sil, %esi
		incq %rdi
		movb (%rdi), %cl	#segundo byte, index 1
		movsbl %cl, %ecx
		incq %rdi			
		movb (%rdi), %dl	#terceiro byte, index 2
		movsbl %dl, %edx
		incq %rdi
		movb (%rdi), %r8b	#quarto byte, index 3
		movsbl %r8b, %r8d
		incq %rdi
		movb (%rdi), %r9b	#quinto byte, index 4
		movsbl %r9b, %r9d
		incq %rdi
		movb (%rdi), %r10b	#sexto byte, index 5
		movsbl %r10b, %r10d

start:
		movl %edx, %eax			#terceiro byte em al
		imull %r10d				#multiplica pelo sexto byte
		cmpl $255, %eax			#resultado > 255?
		jg sum_first
		jmp sum_second
		
sum_first:
		addl %ecx,%r10d			#soma os dois
		movl %r10d, %eax		#coloca o resultado no return
		jmp end
		
sum_second:
		addl %esi, %r9d			#soma
		movl %r9d, %eax			#coloca o resultado no return
		jmp end
end:
		ret
