.section .data

.section .text
		.global str_cat
		
str_cat:
		movb $0, %al		#initialize comparison
		
loop:
		movb (%rdi),%cl		#character of ptr1
		cmpb %cl,%al		#end of string?
		je end_next			
		movb %cl,(%rdx)		#copy character
		incq %rdi			#next character of ptr1
		incq %rdx			#next ptr3
		jmp loop
		
end_next:
		movb (%rsi),%al		#character of ptr2
		cmpb %cl,%al		#end of string?
		je end_end
		movb %al, (%rdx)	#copy	
		incq %rsi			#next character
		incq %rdx
		jmp end_next
		
end_end:
		movb $0,(%rdx)
		ret
