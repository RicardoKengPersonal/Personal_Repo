.section .data

letter_a:
		.byte 'a'
		
space_character:
		.byte ' '
		
.section .text
		.global encrypt
		.global decrypt
		
encrypt:
		movb letter_a(%rip),%r8b				#letter a in %r8b
		movb space_character(%rip),%r9b			#space character in %r9b
		movl $0,%eax							#initialize counter
		
loop:
		movb (%rdi),%dl							#string character
		cmpb $0,%dl								#end of string?
		je end
		cmpb %r8b,%dl							#character = a?
		je increment
		cmpb %r9b,%dl							#character = ' ' ?
		je increment
		incl %eax								#counter++
		addb $1,%dl								#character + 1
		movb %dl, (%rdi)						#change the content of pointer
		jmp increment
		
increment:
		incq %rdi								#next character
		jmp loop	
		
end:
		ret
		
decrypt:
		movb letter_a(%rip),%r8b				#letter a in %r8b
		movb space_character(%rip),%r9b			#space character in %r9b
		movl $0,%eax							#initialize counter
		
loop_decrypt:
		movb (%rdi),%dl							#string character
		cmpb $0,%dl								#end of string?
		je end_decrypt
		cmpb %r8b,%dl							#character = a?
		je increment_decrypt
		cmpb %r9b,%dl							#character = ' ' ?
		je increment_decrypt
		incl %eax								#counter++
		subb $1,%dl								#character + 1
		movb %dl, (%rdi)						#change the content of pointer
		jmp increment_decrypt
		
increment_decrypt:
		incq %rdi								#next character
		jmp loop_decrypt	
		
end_decrypt:
		ret
