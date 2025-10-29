.section .text
		.global extract_data
		
		# %rdi = string, %rsi = token, %rdx = unit (string value), %rcx = unit (numeric value)
		# free %r8, %r9, %r10, %r11 

extract_data:

		movb (%rsi), %r8b						#place first token character in r8b
		
		cmpb $'T', %r8b							#compare first character of token with 'T' 
		je temp_check_second	
								
		cmpb $'H', %r8b							#compare first token character with 'H'
		je check_second_hum		
		
fail:
		movb $0, (%rdx)							#terminate unit string
		movl $0,%eax							#return fail
		movl $0, (%rcx)							#return empty value in unit value
		
		ret
		
temp_check_second:
		incq %rsi								#point to next token character
		movb (%rsi), %r8b						#place token character in r8b
		
		cmpb $'E', %r8b							#character == 'E'?
		je temp_check_third
		
		jmp fail
		
temp_check_third:
		incq %rsi								#point to next token character
		movb (%rsi), %r8b						#place token character in r8b
		
		cmpb $'M', %r8b							#character == 'M'?
		je temp_check_fourth
		
		jmp fail
		
temp_check_fourth:
		incq %rsi								#point to next token character
		movb (%rsi), %r8b						#place token character in r8b
		
		cmpb $'P', %r8b							#character == 'P'?
		je extract_temp
		
		jmp fail
		
check_second_hum:
		incq %rsi								#point to next token character
		movb (%rsi), %r8b						#place token character in r8b
		
		cmpb $'U', %r8b							#character == 'U'?
		je check_third_hum
		
		jmp fail
		
check_third_hum:
		incq %rsi								#point to next token character
		movb (%rsi), %r8b						#place token character in r8b
		
		cmpb $'M', %r8b							#character == 'M' ?
		je extract_hum
		
		jmp fail
		
extract_hum:
		addq $36,%rdi							#point to first character of unit value
loop_hum:
		cmpb $'&', (%rdi)						#character == '&'?
		je numeric_extraction
		
		movb (%rdi),%r9b						#place unit character in r9b
		movb %r9b, (%rdx)						#place unit character in unit pointer
		incq %rdi								#next string character
		incq %rdx								#next unit pointer character
		
		jmp loop_hum
		 
		
extract_temp:
		addq $10,%rdi							#point to first character of unit value
loop_temp:
		cmpb $'&', (%rdi)						#character == '&'?
		je numeric_extraction
		
		movb (%rdi),%r9b						#place character in r9b
		movb %r9b, (%rdx)						#place character in unit pointer
		incq %rdi								#next string character
		incq %rdx								#next unit pointer character
		
		jmp loop_temp
		
numeric_extraction:
		addq $7, %rdi							#point to unit value in string
		movb (%rdi),%r9b						#place value in r9b
		
		cmpb $'0', %r9b							#is it a number?
		jl fail
		cmpb $'9', %r9b							#is it a number?
		jg fail
		
		movsbl %r9b,%r9d						#sign-extend byte to long
		subl $'0', %r9d							#subtract ASCII code
		movl %r9d, %eax							#place value in eax
		imull $10,%eax							#Multiply by 10
		
		incq %rdi								#next value
		
		movb (%rdi), %r9b						#place value in r9b
		
		cmpb $'0', %r9b							#is it a number?
		jl fail
		cmpb $'9', %r9b							#is it a number?
		jg fail
		
		movsbl %r9b,%r9d						#sign-extend byte to long
		subl $'0', %r9d							#subtract ASCII code
		addl %r9d, %eax							#add to return value
		
		movl %eax, (%rcx)						#place return value in unit value pointer		
stop:
		movb $0, (%rdx)							#terminate unit string 
		movl $1,%eax							#return success
		
		ret
