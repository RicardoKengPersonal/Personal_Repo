.section .data
    .global A
    .global B
    .global C
    .global D
    
.section .text
    .global compute

compute:
	movl A(%rip), %eax       # %eax = A
    movl B(%rip), %ecx       # %ecx = B
    imull %ecx, %eax         # %eax = %eax(A) * %ecx(B)
    
    movl C(%rip), %ecx       # %ecx = C
    imull A(%rip), %ecx      # %ecx = %ecx(C) * A
    subl %ecx, %eax          # %eax = %eax(A*B) - %ecx(C*A)     
    
    cdq

    movl D(%rip), %ecx 					# %ecx = D
    
										# ((A + B) - C*A) / D (caso o D seja diferente de 0)
    
    cmpl $0, %ecx 						# divisor = 0 ?
    je divisorIgualZero 				# if divisor == 0, jump to divisorIgualZero  
    
    idivl %ecx 							# %eax = %eax((A*B)-C*A) / %ecx(D)
    jmp end

divisorIgualZero:
    movl $0, %eax # %eax = 0
    jmp end

end:
    ret
