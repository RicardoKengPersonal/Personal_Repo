.section .data
	.global A	#int = 1
	.global B	#char = 3
	.global C	#short = 10
	.global D	#short = 2
	
.section .text
	.global sum_and_subtract	#c-a+d-b
	
sum_and_subtract:
    movswl C(%rip), %eax      # Move o valor de C para eax
    movl A(%rip), %ecx			#Move o valor de A para ecx
    subl %ecx, %eax				#C-A=C
    movswl D(%rip), %ecx		#Move o valor de D para ecx
    addl %ecx, %eax				#(C-A)+D
    movsbl B(%rip), %ecx		#Move o valor de B para ecx
    subl %ecx, %eax   			#(C-A+D)-B
    movslq %eax, %rax			#Move o resultado para o local correto de retorno para 64 bits
    ret

