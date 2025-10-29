.section .data
	.global op1 #int
	.global op2 #int
	.global op3 #int
	
.section .text
	.global sum3ints #op1+op2+op3
	
sum3ints:
		movl op1(%rip),%eax
		movl op2(%rip),%ecx
		addl %ecx,%eax
		movl op3(%rip),%ecx
		addl %ecx,%eax
		movslq %eax,%rax
		ret
