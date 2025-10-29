.section .data

.section .text
    .global median			
    .global sort_array 		# array_sort is the name of the function

		#%rdi = array, %esi = size, %rdx = median			

median:

	#Prologue
	pushq %rbp
    movq %rsp, %rbp
    
    pushq %rbx
    pushq %r12				#save length
    pushq %r13				#save median address
	
	movl %esi, %r12d		#save length
	leaq (%rdx), %r13		#save median address

	cmpl $0, %esi 			#Vec size = 0?
    je failure 
    
	movl $1, %edx			#place 1 in edx for ascending sort
	
	call sort_array
	
	cmpl $0, %eax			#was the sort unsuccessful?
	je failure
	
	movl %r12d, %eax		#restore length
	jmp successful_sort
	
failure:
	movl $0, %eax			#return failure
	jmp end
	
					#calculate median
			
					#is array even or odd?

successful_sort:
	movq $0, %r8						#initialize r8 to save sum later
	movq $0, %r9						#clear r9 (was used in array sort)
	
	movl $2, %r9d						#diviser
	cltd 								# eax -> edx:eax, eax has middle of array
	idivl %r9d							#quotient in eax, remainder in edx
	
	cmpl $0, %edx						#remainder == 0?
	jne case_odd
	
	
					#case array == even
					
case_even:
	movl (%rdi,%rax,4),%r8d			#place middle element in r8d
	movl -4(%rdi,%rax,4),%r10d		#place second middle element in r10d
	
	addl %r10d, %r8d				#sum
	
	movl %r8d, %eax				#place sum in eax to prepare division
	
	sarl $1, %eax			#divide by 2, conserving the sign (if the number is negative the result will stay negative)
	
	leaq (%r13), %rdx		#restore median pointer address
	
	movl %eax, (%rdx)		#place quotient in median pointer address
	
	movl $1, %eax			#return success
	jmp end
	
			#case array == odd
	
case_odd:
	movl (%rdi,%rax,4), %eax		#place middle element in r8d
	
	leaq (%r13), %rdx				#restore median pointer address
	
	movl %eax, (%rdx)				#place median value in median pointer 
	movl $1, %eax					#return success
	jmp end
		

sort_array:

    #rdi = vec , esi = length , edx = order
    
	#Prologue
	
	pushq %rbp
    movq %rsp, %rbp
    pushq %rbx
    pushq %r12				#save array length
    pushq %r13				#save median address
	
    movl $0, %r9d 			# set r9d to 0
	cmpl $0, %esi 			# Array size = 0?
    je unsucessful_end 
    cmpb $0, %dl			#ascending or descending?
    je first_loop			# order = 0 -> descending
    jmp first_loop_ascending
    
first_loop:
    cmpl $0, %esi 			# Array size = 0?
    je sucessful_end 
    subl $1, %esi 			# subtract 1 from esi
    movl %esi, %r9d 		# move esi (number of array elements) into r9d
    jmp second_loop 		# jump to second_loop

    
first_loop_ascending:
    cmpl $0, %esi 			# End of function?
    je sucessful_end 
    subl $1, %esi 			# subtract 1 from esi
    movl %esi, %r9d 		# move esi (number of array elements) into r9d
    jmp second_loop_ascending 		# jump to second_loop

    
second_loop:
    cmpl $0, %r9d 			# end of array?
    je end_second_loop
    movl (%rdi), %ecx 		# place array element in %ecx
    movl %ecx, %r10d 		# move ecx into temp register
    addq $4, %rdi 			# next array element
    movl (%rdi), %ecx 		# place next array element in %ecx
    movl %ecx, %r11d 		# move ecx into second temp register
    cmpl %r10d, %r11d 		# compare r10d to r11d
    jg change 				# if r10d is greater than r11d, jump to change
    decl %r9d 				# update loop counter
    jmp second_loop
  
second_loop_ascending:
    cmpl $0, %r9d 			# end of array?
    je end_second_loop_ascending
    movl (%rdi), %ecx 		# place array element in %ecx
    movl %ecx, %r10d 		# move ecx into temp register
    addq $4, %rdi 			# next array element
    movl (%rdi), %ecx 		# place next array element in %ecx
    movl %ecx, %r11d 		# move ecx into second temp register
    cmpl %r10d, %r11d 		# compare r10d to r11d
    jl change_ascending 	# if r10d is smaller than r11d, jump to change
    decl %r9d 				# update loop counter
    jmp second_loop_ascending
    
change_ascending:
    subq $4, %rdi 			# back to array element
    movl %r11d, (%rdi) 		# move r11d into the value at rdi
    addq $4, %rdi 			# add 4 to rdi
    movl %r10d, (%rdi)		# move r10d into the value at rdi
    decl %r9d 				# update counter
    jmp second_loop_ascending 		# jump to second_loop
    
change:
    subq $4, %rdi 			# back to array element
    movl %r11d, (%rdi) 		# move r11d into the value at rdi
    addq $4, %rdi 			# add 4 to rdi
    movl %r10d, (%rdi)		# move r10d into the value at rdi
    decl %r9d 				# update counter
    jmp second_loop 		# jump to second_loop

end_second_loop:
    movl %esi, %r9d 		# move esi into r9d
    jmp reset 				# jump to reset

end_second_loop_ascending:
    movl %esi, %r9d 		# move esi into r9d
    jmp reset_ascending 				# jump to reset
    
reset_ascending:
    cmpl $0, %r9d 						# compare r9d to 0
    je first_loop_ascending 			# if r9d is 0, jump to first_loop
    subq $4, %rdi 						# subtract 4 from rdi
    decl %r9d 							# subtract 1 from r9d
    jmp reset_ascending 				# jump to reset
    
reset:
    cmpl $0, %r9d 			# compare r9d to 0
    je first_loop 			# if r9d is 0, jump to first_loop
    subq $4, %rdi 			# subtract 4 from rdi
    decl %r9d 				# subtract 1 from r9d
    jmp reset 				# jump to reset

sucessful_end:
	movl $1, %eax			#return success	
	jmp end
	
unsucessful_end:
	movl $0, %eax			#return fail		
	jmp end

end:

	#Epilogue
	popq %r13
	popq %r12
    popq %rbx
    movq %rbp, %rsp
    popq %rbp

    ret						# return

