.section .data
    .global length1
    .global length2
    .global height
    
.section .text
    .global getArea

getArea:
		movswl length1(%rip), %eax  	# Load length1 into %eax
		movswl length2(%rip), %ecx 		# Load length2 into %ecx
		addl %ecx,%eax 				  	# Adds length1 and length2
		movswl height(%rip), %ecx    	# Load height into %ecx
		mull %ecx 						# Multiply the result in %eax by the height
		
		movl $2, %ecx
		divl %ecx    					 # Divide the result by 2
		ret
		
