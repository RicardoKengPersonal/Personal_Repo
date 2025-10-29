.section .data

.section .text
		.global vec_sum
		.global vec_avg
		
vec_sum:
		movl $0,%r9d		#initialize counter
		movl $0, %eax		#initialize sum
		
loop_sum:
		cmpw %si,%r9w
		je end
		movl (%rdi),%ecx
		addl %ecx,%eax
		jmp increment
		
increment:
		incw %r9w			#increment counter
		addq $4,%rdi		#next int
		jmp loop_sum
		
end:
		ret
		
vec_avg:
	call vec_sum 			# call vec_sum

	movw %si, %r8w 			# r8w = number of elements

	cmpw $0, %r8w 			# compare to see if num is 0
    je return_zero 			# if it is, jump to return_zero

	movswl %r8w, %r8d 		# pass num from short to integer

	cdq 					# sign extend eax into edx:eax
	
	idivl %r8d 				# divide eax by r8d (num)

	ret 					# return from function

return_zero:
	movl $0, %eax 			# eax = 0
	ret 					# return from function
