.section .data
	.global value
	.global bits

.section .text
	.global get_number_binary

	get_number_binary:

		cmp $31, %edi            # Se n > 31
		jg fora_intrevalo         # Retorna 0

		cmp $0, %edi             # Se n < 0
		jl fora_intrevalo         # Retorna 0

		# Inicializar ponteiro do array e contador
		movl $5, %ecx             # Contador para 5 bits
		movq %rsi, %rdx           # %rdx será o ponteiro para bits


	converter_binario:

		# converter o valor n (passado em %edi) para binario 5 bits, armazena cada bit isolado no array, apontado por %rdx
		cmpl $0, %ecx			 # Fim do loop?
		je end					 # Termina
		movl %edi, %eax
		and $1, %eax             # limpar os bits exceto o último
		movb %al, (%rdx)         # Armazenar o bit mais a direita
		incq %rdx                # Incrementar próximo byte
		shr $1, %edi             # 10011 - 1001
		decl %ecx				 #Contador--
		jmp converter_binario	 #loop

	end:
		movl $1, %eax             # Retornar 1
		ret


	fora_intrevalo:
		movl $0, %eax           # zerar e retornar 0
		movl $5, %ecx             # inicializar contador para 5 bits
		movq %rsi, %rdx           # inicializa o ponteiro

	apagar_bits:
		incq %rdx                 # 5 iterações (limpa os bits) e depois retorna
		loop apagar_bits

    ret