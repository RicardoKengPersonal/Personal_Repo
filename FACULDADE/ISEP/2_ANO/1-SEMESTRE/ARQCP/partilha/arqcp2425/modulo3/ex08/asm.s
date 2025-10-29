.section .data

.section .text
		.global test_even
		.global vec_sum_even
		
test_even:
		movl $2,%r9d
		movl %edi, %eax
		cltd
		idivl %r9d
		cmpl $0,%edx
		je even
		movl $0,%eax
		jmp end
		
even:
		movl $1,%eax
		jmp end
		
end:
		ret
		
vec_sum_even:
		# %rdi = vec , %esi = num

		leaq (%rdi), %rcx		#place address in rcx
		movq $0, %rdi			#clear rdi
		movl $0, %r10d			#loop counter
		movl $0, %r8d			#sum to return
		cmpl $0, %esi			#end?
		je end_vec_sum
		
loop_vec_sum:
		cmpl %esi,%r10d			#end ?
		je end_vec_sum
		movl (%rcx), %r11d		#place array element in %r11d
		movl %r11d, %edi		#place in %edi  
		
		call test_even			#function call
		
		cmpl $1, %eax			#even?
		je add_to_sum
		jmp increment_vec_sum
		
add_to_sum:
		addl (%rcx), %r8d
		jmp increment_vec_sum

increment_vec_sum:
		incl %r10d				#loop counter++
		addq $4,%rcx				# next int 
		jmp loop_vec_sum
		
end_vec_sum:
		movl %r8d, %eax
		ret
		
		
