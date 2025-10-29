.section .data

.section .text
		.global sum_third_byte
		
sum_third_byte:
		movl $0,%eax		#return value
		movl $0,%ecx		#counter for loop
		addq $2, %rdi		#point to third byte
		movl %esi, %r9d		#number of array elements
loop:
		cmpl %r9d, %ecx
		je end
		movb (%rdi), %r8b	#move the third byte of first element to register
		movsbl %r8b,%r8d
		addl %r8d, %eax		#add third byte to sum
		jmp increment
		
increment:
		addq $8, %rdi		#point to next long
		incl %ecx			#increment counter
		jmp loop	
		
end:
		ret
