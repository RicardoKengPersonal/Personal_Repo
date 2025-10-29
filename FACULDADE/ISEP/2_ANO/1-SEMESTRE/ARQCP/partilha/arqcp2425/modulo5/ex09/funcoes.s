.section .data

.section .text
	.global fun1
	.global fun2
	.global fun3
	.global fun4
	
fun1:
	movw 4(%rdi), %ax
	ret
	
fun2:
	movw 20(%rdi), %ax
ret


fun3:
	leaq 20(%rdi), %rax		# structA ocupa 6 bytes e *b ocupa 8 bytes --> 8(6+2 para ser múltiplo) + 8 + 4 = 20
ret


fun4:
	movq 8(%rdi), %rcx		# s->b
	movw 4(%rcx), %ax		# b->x
ret
