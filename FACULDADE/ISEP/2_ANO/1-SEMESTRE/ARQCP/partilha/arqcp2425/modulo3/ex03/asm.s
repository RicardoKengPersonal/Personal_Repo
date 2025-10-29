.section .data

letter_u:
		.byte 'u'
letter_v:
		.byte 'v'
capital_u:
		.byte 'U'
capital_v:
		.byte 'V'
		
.section .text
		.global str_copy_roman2
		
str_copy_roman2:
		movb letter_u(%rip),%dl			#letter u
		movb letter_v(%rip),%r8b		#letter v
		movb capital_u(%rip),%cl		#Capital u
		movb capital_v(%rip),%r9b		#Capital v
		
loop:
		movb (%rdi),%al					#char of ptr1 in al
		cmpb $0, %al					#end of string?
		je fim							#end
		cmpb %dl,%al					#char of ptr1 = u?
		je swap_lowercase
		cmpb %cl,%al					#char of ptr1 = U?
		je swap_uppercase
		jmp copy 
		
swap_lowercase:
		movb %r8b,%al					#change u for v
		jmp copy						#copy to new string
		
swap_uppercase:
		movb %r9b,%al					#change U for V
		jmp copy						#copy to new string
		
copy:
		movb %al,(%rsi)					#copy char of al to ptr2
		jmp increment
		
increment:
		incq %rdi						#next char
		incq %rsi						#next char
		jmp loop
fim:
		movb $0,(%rsi)					#terminate string
		ret
