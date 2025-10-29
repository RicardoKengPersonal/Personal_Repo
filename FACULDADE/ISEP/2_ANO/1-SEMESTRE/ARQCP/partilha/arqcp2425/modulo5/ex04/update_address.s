.section .data
	
.section .text

 .global update_address				# void update_address(Student *s, char *new_address)
									#                           rdi ,       rsi


# typedef struct {
#    short number; - 2
#    char age; - 1 +1 padding
#    int grades[5]; - 20
#    char name[60]; - 60
#    char address[100];
#} Student;

update_address:
	# prologue
	pushq 	%rbp				# save the base pointer
	movq	%rsp, %rbp			# set the base pointer to the stack pointer

	
	leaq 84(%rdi), %rdi			# rcx = address - move the address of the first position of the address array into rcx 
	
	movq $0, %rax				# rax = 0, will be used to store the value of each new address (for each iteration)

	
# loop to update the address
updater:

	movb (%rsi), %al			# rax = *rsi - move the char at the current position of the new address into rax
	movb %al, (%rdi)			# *rdi = rax - change the value on the current position of the address array to the new value
	
	incq %rdi					# rdi++ - move to the next address
	incq %rsi					# rsi++ - move to the next address
	
	cmpb $0, %al				# compare the value of rax to 0
	jne updater					# if rax != 0, jump to the updater label


	
end:	
	# epilogue
	
	movq 	%rbp, %rsp		    # restore the stack pointer
	popq	%rbp				# restore the base pointer
	ret							# return to the caller
