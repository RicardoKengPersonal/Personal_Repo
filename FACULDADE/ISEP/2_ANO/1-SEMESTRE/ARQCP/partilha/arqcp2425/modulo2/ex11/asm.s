.section .data
	.global op1		#16 bit
	.global op2		#16 bit
	
.section .text
	.global verify_flags		#return 16 bit
	
verify_flags:
		movw op1(%rip), %ax
		movw op2(%rip), %cx
		addw %cx, %ax
		jc carry
		jo overflow
		movw $0, %ax
		jmp end
		
carry:
		movw $1,%ax
		jmp end
		
overflow:
		movw $1,%ax
		jmp end
		
end:
		ret
