.section .data

.section .text
		.global swap
		
swap:
		movl $0,%r8d		#counter for both arrays
		movl %edx,%r9d		#number of elements of the arrays
		
loop:
		cmpl %r9d,%r8d		#end?
		je end
		movb (%rdi),%al		#element of first array
		movb (%rsi),%r10b	#element of second array
		movb %r10b,(%rdi)	#change array
		movb %al, (%rsi)
		jmp increment
		
increment:
		incq %rsi			#next char
		incq %rdi			#next char
		incl %r8d			#counter++
		jmp loop

end:
		ret
