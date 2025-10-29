.section .data

.section .text
		.global vec_zero

vec_zero:
		movb $0, %al		#return value
		movl %esi,%ecx		#number of elements in array
		movl $0,%edx		#counter for loop

loop:
		cmpl %edx,%ecx		#end of array?
		je end
		movl (%rdi),%r8d	#array element
		cmpl $50,%r8d		#element >= 50?
		jge change			
		jmp increment
		
change:
		movl $0,(%rdi)		#zero the element
		incb %al
		jmp increment
		
increment:
		addq $4,%rdi		#next int
		incl %edx			#increment counter for the loop
		jmp loop
		
end:
		ret
