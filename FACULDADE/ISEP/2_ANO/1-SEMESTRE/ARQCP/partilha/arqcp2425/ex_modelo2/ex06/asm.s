.section .data

space_character:
		.byte ' '

.section .text
		.global remove_spaces
		
remove_spaces:
		movb space_character(%rip), %cl			#space character ascii
		movq $0, %rax
		leaq (%rdi), %r10						#save address

loop:

		movb (%rdi), %dl				#character of string in %dl
		cmpb $0, %dl					#end of string?
		je end
		cmpb %cl,%dl					#character = space?
		je space_found
		movb %dl, (%r10)				#coloca caracter diferente de espaco em r10
		incq %r10						#incrementa
		incq %rdi						#incrementa
		jmp loop
		
space_found:
		incl %eax						#contador de espacos ++
		incq %rdi						#proximo caracter
		jmp loop

end:
		movb $0, (%r10)					#termina string
		ret
