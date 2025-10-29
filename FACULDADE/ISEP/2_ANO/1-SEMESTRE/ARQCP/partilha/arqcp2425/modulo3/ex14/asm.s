.section .data

.section .text
	.global exists 				# exists is the name of the function
	.global vec_diff 			# vec_diff is the name of the function

exists:
    movl $0, %eax 				# set eax to 0
    movl $0, %r8d 				# loop counter

exists_loop:
    cmpl %r8d, %esi 			# end of loop?
    je end_exists_loop
    cmpl (%rdi), %edx 			# Array element = search element?
    je num_exists
    addq $4, %rdi 				# next int in array
    incl %r8d 					# increment loop counter 
    jmp exists_loop 		

num_exists:
    cmpl $1, %eax 				# check if eax is 1
    je end 						# if eax is 1, jump to end
    incl %eax 					# increment eax by 1
    addq $4, %rdi 				# next int
    incl %r8d 					# increment loop counter
    jmp exists_loop

end_exists_loop:
    cmpl $1, %eax 				# check if eax is 1
    je end_exists 				# if eax is 1, jump to end_exists
    jmp end 					# jump to end

end_exists:
    movl $0, %eax 				# set eax to 0
    jmp end 					# jump to end

vec_diff:
    leaq (%rdi), %rcx 			# load the address of rdi into rcx
    leaq (%rdi), %r10 			# load the address of rdi into r10
    movl $0, %r9d 				# set r9d to 0
    movl $0, %r11d 				# loop counter

vec_diff_loop:
    cmpl %r11d, %esi 			# end of array?
    je end_vec_diff_loop 		
    movl (%rcx), %edx 			# move the value at rcx into edx (Array element to search in 3rd argument)
    leaq (%r10), %rdi 			# load the address of r10 into rdi (Place adress of first element in 1st argument)
    call exists 				# call exists
    cmpl $0, %eax 				# check if eax is 0
    je vec_diff_incq 			# if eax is 0, jump to vec_diff_incq
    addq $4, %rcx 				# increment rcx by 4
    incl %r11d 					# increment r11d by 1
    jmp vec_diff_loop			# jump to vec_diff_loop

vec_diff_incq:
    incl %r9d 					# increment unique counter 
    addq $4, %rcx				# next int
    incl %r11d 					# increment loop counter
    jmp vec_diff_loop 			# jump to vec_diff_loop

end_vec_diff_loop:
    movl %r9d, %eax 			# return value
    jmp end 					# jump to end

end:
    ret 					# return
