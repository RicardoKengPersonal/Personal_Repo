.section .text
	.global get_number



get_number:

	movl $0,%edx 		# acumulador para construir o número
	movq $0, %rax		# inicializar o registrador de retorno com 0

	movb (%rdi),%al		# lê o primeiro caractere da string apontada por rdi e coloca-o em al
	cmp $0,%al			# compara o valor do caractere com 0 .

	je fail				#Se o caractere for string vazia- fail

	cmpb $' ',%al		# verificar se é espaço
	je skip_first		# apagar os espaços

	jmp convert

skip_first:

	incq %rdi			# avança o ponteiro da string para o próximo caractere
	movb (%rdi),%al		# lê o próximo caractere.

	cmpb $' ', %al		# verifica se ainda é um espaço, se for continua a ignorar espaços
	je skip_first

	cmp $0,%al			# se for o fim da string fail pq so tem espaços
	je fail

	jmp convert			# encontrou um caractere válido, vai para a conversão


convert:

	movb (%rdi),%al

	cmpb $'0',%al		# compara o valor ASCII do caractere
	jl not_number

	cmpb $'9',%al
	jg not_number

	subb $'0',%al		#%al contém o valor ASCII do caractere atual,  Subtrair '0' (ASCII 48)

	imull $10,%edx		# 12 e o proximo digito é 3, fica a 120, para poder somar o próximo dígitor

	movsbl %al,%eax		# converter o valor de al (8 bits) para eax (32 bits), preservando o sinal

	addl %eax,%edx		# adiciona ao acumulador

	incq %rdi			# incrementar o ponteiro para o proximo caracter da string

	jmp convert

not_number:

	cmp $0,%al			# verifica se e o final da string
	je success

	cmpb $' ',%al		# verifica se é um espaço
	je skip_last		# ignora espaços no final

	jmp fail			# qualquer outro inválido falha

skip_last:

	incq %rdi			#next character
	movb (%rdi),%al		#place character in al

	cmpb $0,%al			# final?
	je success

	cmpb $' ',%al		# espaço?
	je skip_last

	movl $0,%eax		#falha caracteres invalidos
	ret


success:
	movl %edx,(%rsi)	#Armazena o número convertido no endereço apontado por rsi
	movl $1,%eax		#retorna 1 sucesso
	ret

fail:
	movl $-1, (%rsi)	# -1 falha
	movl $0,%eax
	ret
