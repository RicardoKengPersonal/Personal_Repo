.section .text
    .global calculate
	calculate:	
		# prologue
		pushq %rbp 				# Guarda o valor original do %rbp
		movq %rsp, %rbp 		# Copia o atual stack pointer para o %rbp
		
        subq    $8, %rsp
        
        movl    %edi, %r8d			# a
        movl    %esi, %r9d			# b
        
        movl    %r8d, %eax			# a -> eax
        subl    %r9d, %eax			# a - b -> eax
        movl    %eax, -4(%rbp)		
        
        movl    %r8d, %eax
        imull   %r9d, %eax
        movl    %eax, -8(%rbp)
        
        movl    $'*', %edi
        movl    %r8d, %esi
        movl    %r9d, %edx
        movl    -8(%rbp), %ecx
        
        pushq %r8
        pushq %r9
        
        call    print_result
        
        popq %r9
        popq %r8
        
        movl    $'-', %edi
        movl    %r8d, %esi
        movl    %r9d, %edx
        movl    -4(%rbp), %ecx
        
        pushq %r8
        pushq %r9
        
        call    print_result
        
        popq %r9
        popq %r8
        
        movl    %r8d, %eax
        subl    %r9d, %eax
        movl    %eax, %edx
        
        movl    %r8d, %eax
        imull   %r9d, %eax
        subl    %eax, %edx
        movl    %edx, %eax
        
        # epilogue
		movq %rbp, %rsp
		popq %rbp
        ret
		
		
		
