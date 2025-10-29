.section .data
NULL:
		.asciz "NULL"
			

.section .text
		.global find_result
		
find_result:
		movq $0, %rax			#clear rax
		
		movb (%rsi), %cl		#primeira letra do token em cl
		incq %rsi
		movb (%rsi), %dl		#segunda letra do token em dl
		incq %rsi
		movb (%rsi), %r8b		#terceira letra do token em r8b
		
first_loop:
		cmpb $0,(%rdi)			#end of string?
		je end_not_found
		cmpb %cl, (%rdi)		#primeira letra do token = letra da string ?
		je first_found
		jmp increment_str

increment_str:
		incq %rdi
		jmp first_loop
		
first_found:
		movq %rdi, %rax
		incq %rdi
		cmpb $0, (%rdi)
		je end_not_found
		cmpb %dl, (%rdi)
		je second_found
		jmp first_loop
		
second_found:
		incq %rdi
		cmpb $0, (%rdi)
		je end_not_found
		cmpb %r8b, (%rdi)
		je third_found
		jmp first_loop

third_found:
		jmp end
		
end_not_found:

		movb NULL(%rip), %al
		jmp end
end:
		ret
		
		
