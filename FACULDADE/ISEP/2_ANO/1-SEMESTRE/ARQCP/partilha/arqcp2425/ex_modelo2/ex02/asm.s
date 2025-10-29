.section .data

.section .text
		.global max
		.global count_max
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
		jmp end_max

a_not_greater:
		movl $0, %eax
		jmp end_max
		
end_max:
		ret
		
count_max:
		#%rdi = vec, %esi = num --> count_max
		# %edi = a, %esi = b , %edx = c ---> max
		
		#free -> rcx, r8, r9, r10, r11
		movl $0, %eax
		movl %esi, %r8d			#loop counter
		subl $2, %r8d
		movl $0, %r11d			#sum to return
		leaq (%rdi), %r9
		
loop_count_max:
		cmpl $0, %r8d
		jle end_count_max
		movl (%r9), %esi			#place first element in b of max()
		addq $4, %r9				#next int 
		movl (%r9), %ecx			#place next int
		addq $4, %r9				#point to next int
		movl (%r9), %r10d			#place next int
		
		movl %ecx, %edi				#place vec[i+1] in max
		movl %r10d, %edx			#place vec[i+2] in max
		
		call max					#function call
		
		cmpl $1, %eax				#compare return value
		je add_num
		jmp reset
		
add_num:
		incl %r11d					#sum++
		jmp reset

reset:
		subq $4, %r9				#next elements to test
		decl %r8d					#counter--
		jmp loop_count_max
		
end_count_max:
		movl %r11d, %eax			#return sum
		ret
