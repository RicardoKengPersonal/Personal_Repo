.section .data

.section .text
    .global array_sort 		# array_sort is the name of the function

array_sort:
    movl $0, %r9d 			# set r9d to 0
    
first_loop:
    cmpl $0, %esi 			# Array size = 0?
    je end 
    subl $1, %esi 			# subtract 1 from esi
    movl %esi, %r9d 		# move esi (number of array elements) into r9d
    jmp second_loop 		# jump to second_loop

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

reset:
    cmpl $0, %r9d 			# compare r9d to 0
    je first_loop 			# if r9d is 0, jump to first_loop
    subq $4, %rdi 			# subtract 4 from rdi
    decl %r9d 				# subtract 1 from r9d
    jmp reset 				# jump to reset

end:
    ret						# return
