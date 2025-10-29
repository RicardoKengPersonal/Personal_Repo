.section .data

	length1:
		.int 0
	length2:
		.int 0
	height:
		.int 0
		
    .global length1,length2,height

.section .text
    .global getArea

getArea:
		movl length1(%rip), %eax  	# Load length1 into %eax
		movl length2(%rip), %ecx 		# Load length2 into %ecx
		addl %ecx,%eax 				  	# Adds length1 and length2
		movl height(%rip), %ecx    	# Load height into %ecx
		mull %ecx 						# Multiply the result in %eax by the height
		
		movl $2, %ecx
		divl %ecx    					 # Divide the result by 2
		ret
		
