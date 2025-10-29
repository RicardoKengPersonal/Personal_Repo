.section .data

.section .text
    .global median			
    .extern sort_array 		# array_sort is the name of the function

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
		
		
end:

	#Epilogue
	popq %r13
	popq %r12
    popq %rbx
    movq %rbp, %rsp
    popq %rbp

    ret						# return

