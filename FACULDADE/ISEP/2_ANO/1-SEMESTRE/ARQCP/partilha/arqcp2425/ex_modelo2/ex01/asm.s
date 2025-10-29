.section .data

.section .text
		.global max
		
max:
		# %edi = a, %esi = b , %edx = c
		#free -> rcx, r8, r9, r10, r11
		
		cmpl %esi,%edi				#compara a (%edi) com b (%esi) ---- (%edi - %esi)
		jg a_greater_b				#se a for maior, salta para o ciclo maior
		jmp a_not_greater			#senao termina 

a_greater_b:
		cmpl %edx, %edi				#compara a (%edi) com c (%edx) ----- (%edi - %edx)
		jg a_greater_c				#se a for maior avanca para colocar o retorno a 1 (%eax)
		jmp a_not_greater			#senao avanca para colocar o retorno a 0 (%eax)
		
a_greater_c:
		movl $1, %eax
		jmp end

a_not_greater:
		movl $0, %eax
		jmp end
		
end:
		ret
		
