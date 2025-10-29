.section .data 

.section .text
		.global frequencies 		# frequencies function

frequencies:
		movl $20, %ecx		 		# load 20 into ecx
clear:
		cmpl $0, %ecx 				# compare ecx to 0
		je reset					# if ecx is 0, jump to reset
		movl $0, (%rdx) 			# set the value at ptrfreq to 0
		addq $4, %rdx 				# increment ptrfreq by 4
		subl $1, %ecx 				# decrement ecx
		jmp clear 					# jump to clear
reset:
		movl $0, (%rdx) 			# set the value at ptrfreq to 0
		subq $80, %rdx 				# subtract 80 (20X4) from ptrfreq
loop:
		cmpl $0, %esi				# compare esi to 0
		je end						# if esi is 0, jump to end
		movsbq (%rdi), %rax			# load ptrgrades into rax
		imulq $4, %rax 				# multiply rax by 4
		addq %rax, %rdx 			# add rax to ptrfreq
		addq $1, (%rdx) 			# add 1 to value at ptrfreq
		subq %rax, %rdx 			# subtract rax from ptrfreq
		incq %rdi 					# increment ptrgrades
		subl $1, %esi				# decrement esi
		jmp loop 					# jump to loop
	
	
end:
		ret							# return
