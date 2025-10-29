.section .data

.section .text
		.global vec_greater12
		
vec_greater12:
		movl $0,%eax		#return value
		movl $0,%ecx		#counter 
		movl %esi,%edx		#number of elements in array
		cmpl %ecx,%edx		#array size = 0?
		je end
loop:
		cmpl %edx,%ecx			#end of array?
		je end
		movl (%rdi),%r8d		#array element
		cmpl $12,%r8d			#element > 12 ?
		jg greater
		jmp increment
		
greater:
		incl %eax			#return value
		jmp increment
		
increment:
		incl %ecx			#counter++
		addq $4,%rdi		#next int
		jmp loop
		
end:
		ret
