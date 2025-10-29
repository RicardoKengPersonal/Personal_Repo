.section .text
    .global inc_and_cube
	inc_and_cube:	
		incw (%rdi)
		
		movslq %esi, %rsi
		movq %rsi, %rax
		
		imulq %rsi, %rax
		imulq %rsi, %rax
		
		ret
