.section .data
	.global num
	.global input
	
.section .text
		.global sub
		
sum:
		movl num(%rip),%eax
		movl input(%rip),%ecx
		subl %ecx,%eax
		ret
