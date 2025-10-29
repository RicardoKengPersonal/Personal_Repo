.section .data 
	
.section .text
	.global count_max 		# count_max is the function name

count_max:
	movl $0, %eax		 	# initialize eax to 0
	subl $2, %esi 			# subtract 2 from esi
	
loop:
	cmpl $0, %esi
	jle end 				# if esi <= 0, jump to end
	movl (%rdi), %edx 		# load ptr into edx
	addq $4, %rdi			# increment ptr by 4
	movl (%rdi), %r8d 		# load ptr into r8d
	cmpl %edx, %r8d 		# compare r8d and edx
	jg pass 				# if r8d > edx, jump to pass
	subl $1, %esi 			# decrement esi by 1
	jmp loop
	
	
pass:
	addq $4, %rdi			# increment ptr by 4
	movl (%rdi), %r9d 		# load ptr into r9d
	cmpl %r8d, %r9d 		# compare r9d and r8d
	jl sum 					# if r9d < r8d, jump to sum
	subq $4, %rdi 			# decrement ptr by 4
	subl $1, %esi 			# decrement esi by 1
	jmp loop
	
sum:
	addl $1, %eax 			# increment eax by 1
	subq $4, %rdi			# decrement ptr by 4
	subl $1, %esi 			# decrement esi by 1
	jmp loop 				# jump to loop
	
end:
	ret 					# return
