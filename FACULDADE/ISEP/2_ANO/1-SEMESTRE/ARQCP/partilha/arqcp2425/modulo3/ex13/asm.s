.section .data

.section .text
		.global keep_positives
		
keep_positives:
		movl $0,%eax		#loop counter
		movl %esi,%ecx		#number of elements in array
		
loop:
		cmpl %eax,%ecx		#end of array?
		je end
		movl (%rdi),%edx	#array element in edx
		cmpl $0,%edx		#element < 0?
		jl negative
		jmp increment
		
negative:
		movl %eax,(%rdi)	#replace element by index number
		jmp increment
		
increment:
		addq $4,%rdi		#next int
		incl %eax			#loop counter++
		jmp loop
		
end:
		ret
		
