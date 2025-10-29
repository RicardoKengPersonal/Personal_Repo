.section .data

.section .text
    .global sort_without_reps 	# sort_without_reps is the name of the function
    .global check_duped 		# check_duped is the name of the function
    .global order 				# order is the name of the function

sort_without_reps:
    leaq (%rsi), %r11 # load ptrdest into r11
    movl $0, %r10d # set r10d to 0

#------------------move unduped--------------------------

move_unduped_loop:
    cmpl $0, %edx 				# check if num is 0
    je end_move_unduped_loop 	# if num is 0, jump to end_move_unduped_loop
    movw (%rdi), %cx 			# load the value of ptrsrc into cx
    call check_duped 			# call check_duped
    cmpl $0, %eax 				# check if eax is 0
    je move_unduped 			# if eax is 0, jump to move_unduped
    addq $2, %rdi 				# increment ptrsrc by 2
    decl %edx 					# decrement num by 1
    jmp move_unduped_loop 		# jump to move_unduped_loop

move_unduped:
    movw %cx, (%rsi) 			# move the value of cx into ptrdest
    addq $2, %rdi 				# increment ptrsrc by 2
    addq $2, %rsi 				# increment ptrdest by 2
    decl %edx 					# decrement num by 1
    incl %r10d 					# increment r10d by 1
    jmp move_unduped_loop 		# jump to move_unduped_loop

end_move_unduped_loop:
    movl %r10d, %eax 			# move the value of r10d into eax
    jmp order 					# jump to order

#------------------check duped--------------------------

check_duped:
    leaq (%r11), %r8 			# load ptrdest into r8
    movl %r10d,%r9d 			# move the value of r10d into r9d
    movl $0, %eax 				# set eax to 0

check_duped_loop:
    cmpl $0, %r9d 				# check if r9d is 0
    je end 						# if r9 is 0, jump to end
    cmpw (%r8), %cx 			# compare the value of ptrdest with cx
    je num_exists 				# if they are equal, jump to num_exists
    addq $2, %r8 				# increment ptrdest by 2
    decl %r9d 					# decrement r9d by 1
    jmp check_duped_loop 		# jump to check_duped_loop

num_exists:
    movl $1, %eax 				# set eax to 1
    je end 						# jump to end

#------------------order ascending--------------------------

order:
    leaq (%r11), %rdi 			# load ptrdest into rdi
    movl %eax, %edx 			# move the value of eax into edx
    movl $0, %r9d 				# set r9d to 0

first_loop:
    cmpl $0, %edx 				# check if edx is 0
    je end 						# if edx is 0, jump to end
    subl $1, %edx 				# decrement edx by 1
    movl %edx, %r9d 			# move the value of edx into r9d
    jmp second_loop 			# jump to second_loop

second_loop:
    cmpl $0, %r9d 				# check if r9d is 0
    je end_second_loop 			# if r9d is 0, jump to end_second_loop
    movw (%rdi), %cx 			# load the value of ptrdest into cx
    movw %cx, %r10w 			# move the value of cx into r10w
    addq $2, %rdi 				# increment ptrdest by 2
    movw (%rdi), %cx 			# load the value of ptrdest into cx
    movw %cx, %r11w 			# move the value of cx into r11w
    cmpw %r10w, %r11w 			# compare the value of r10w with r11w
    jl change 					# if r10w is less than r11w, jump to change
    decl %r9d 					# decrement r9d by 1
    jmp second_loop 			# jump to second_loop

change:

    subq $2, %rdi 				# decrement ptrdest by 2
    movw %r11w, (%rdi) 			# move the value of r11w into ptrdest
    addq $2, %rdi 				# increment ptrdest by 2
    movw %r10w, (%rdi) 			# move the value of r10w into ptrdest
    decl %r9d 					# decrement r9d by 1
    jmp second_loop 			# jump to second_loop

end_second_loop:

    movl %edx, %r9d 			# move the value of edx into r9d
    jmp reset 					# jump to reset

reset:
    cmp $0, %r9d 				# compare r9d with 0
    je first_loop 				# if r9d is 0, jump to first_loop
    subq $2, %rdi 				# decrement ptrdest by 2
    decl %r9d 					# decrement r9d by 1
    jmp reset 					# jump to reset

#------------------end--------------------------

end:
    ret 						# return
