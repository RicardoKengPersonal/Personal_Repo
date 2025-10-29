.section .data

.section .text
		.global vec_add_three
		
vec_add_three:
		movl $0, %r9d
		
loop:
		movw (%rdi),%ax				#get array element
		movl %esi,%ecx				#get array number of elements
		cmpl %ecx,%r9d				#end of array?
		je end						
		addw $3,%ax					#element + 3	
		movw %ax,(%rdi)				#change the element value
		jmp next
		
next:
		addq $2,%rdi
		incl %r9d					#increment counter
		jmp loop
		
end:
		ret
		
