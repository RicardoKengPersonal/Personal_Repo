.section .data

.section .text
	.global update_address

update_address:
	addq $84, %rdi
	movb (%rsi), %al
	
	cmpb $0, %al
	jle final
	
loop:
	movb %al, (%rdi)
	incq %rdi
	incq %rsi
	movb (%rsi), %al
	cmpb $0, %al
	jne loop
	
final:
	movb $0, (%rdi)
	ret
	
	
