.global get_n_element

get_n_element:

    # head = %rcx, tail = %rdx

    movl (%rcx), %r8d
    movl (%rdx), %r9d						# valores dos ponteiros sao carregados no registador

    movl $0, %eax							# eax vai retornar o resultado

# se head = tail o buffer esta vazio
    cmpl %r8d, %r9d
    je buffer_vazio


    cmpl %r9d, %r8d							# comparamos head com tail
    jg numeros_armazenado					# se head > tail


# quando head < tail, o cálculo é (length - tail) + head
    subl %r9d, %esi							# length - tail
    addl %r8d, %esi							# (length - tail) + head
    movl %esi, %eax
    ret


# verificar se o buffer está cheio, o buffer está cheio quando (head + 1) == tail
    movl %r8d, %r10d
    addl $1, %r10d							# head + 1 r10d
    cmpl %r10d, %r9d						# comparar se head + 1 (r10d) é igual a tail (r8d)
    je buffer_cheio							# se sim, significa que o buffer está cheio



# retornar 0 elementos
buffer_vazio:
	movl $0, %eax
	ret


buffer_cheio :
	subl $1, %esi							# atenção, é length - 1 porque ha uma posição reservada para distinguir entre cheio e vazio
	movl %esi, %eax
	ret


numeros_armazenado:
	movl %r8d, %eax							# copiamos head para eax
	subl %r9d, %eax							# head >= tail, o número de elementos é head - tail
	ret
