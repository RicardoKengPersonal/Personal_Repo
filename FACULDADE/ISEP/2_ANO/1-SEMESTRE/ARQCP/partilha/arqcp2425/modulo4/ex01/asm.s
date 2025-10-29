.section .data
.section .text
    .global d_square

d_square:
    movq $0, %rax			# Limpa o %rax
    movslq %edi, %rax		# Extende o sinal e move o x (%edi) para o %rax
    imulq %rax, %rax		# Multiplica o %rax por si próprio (x^2) e guarda no %rax
    imulq $2, %rax			# Multiplica o %rax por 2 (2*x^2)
    ret
