.section .data

.section .text
		.global vec_search
		
vec_search:
		movl $0,%r9d			#counter
		movl %esi, %r8d			#number of elements in array
		movl %edx,%ecx			#element to search for
		movq $0, %rax			#initialize rax
		
		
loop:	
		cmpl %r9d, %r8d			#continue?
		je end
		movl (%rdi),%r10d		#array element in eax
		cmpl %ecx,%r10d			#earch = element
		je element_found
		jmp increment
		
increment:
		addl $1,%r9d			#counter++
		addq $4,%rdi			#next int
		jmp loop

element_found:
		movq %rdi, %rax
		jmp end

end:
		ret
		
