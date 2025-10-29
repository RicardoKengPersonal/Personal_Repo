.section .data

.section .text
		.global fn 
		
		#ocupado -> %rdi -> num
fn:
		movl $0, %eax				#inicializa eax
		
		movb (%rdi), %sil 			#primeiro byte de x em sil
		incq %rdi					#segundo byte
		movb (%rdi), %dl			#segundo byte de x em dl
		incq %rdi					#next byte
		movb (%rdi), %cl			#terceiro byte de x em cl
		incq %rdi					#segundo byte
		movb (%rdi), %r8b			#segundo byte de x em r8b
		incq %rdi
		
		cmpb %sil, %cl
		jg end_false
		cmpb %dl, %r8b
		jg end_false
		jmp end_true
		
end_false:
		movl $0, %eax
		jmp end
end_true:
		movl $1, %eax
		jmp end
end:
		ret
		
		
