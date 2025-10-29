.section .data

letter_u:
		.byte 'u'
letter_v:
		.byte 'v'
		
		
.section .text
		.global str_copy_roman
		
str_copy_roman:
		movb letter_u(%rip),%dl			#letter u
		movb letter_v(%rip),%r8b		#letter v
		
loop:
		movb (%rdi),%al					#char of ptr1 in al
		cmpb $0, %al					#end of string?
		je fim							#end
		cmpb %dl,%al					#char of ptr1 = u?
		je swap_letter					#if yes, swap_letter
		jmp copy 
		
swap_letter:
		movb %r8b,%al					#change u for v
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
