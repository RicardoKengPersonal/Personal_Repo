.section .data
	.global current
	.global desired
	
	
.section .text
	.global needed_time
	
needed_time:
		movsbw current(%rip),%dx
		movsbw desired(%rip),%cx
		cmpw %cx,%dx
		je igual
		jg maior
		jl menor
		
igual:
		movw $0, %ax
		jmp end
		
maior:
		subw %cx,%dx
		movw %dx,%ax
		imulw $240,%ax
		jmp end
menor:
		subw %dx,%cx
		movw %cx,%ax
		imulw $180,%ax
		jmp end
		
end:
		ret
