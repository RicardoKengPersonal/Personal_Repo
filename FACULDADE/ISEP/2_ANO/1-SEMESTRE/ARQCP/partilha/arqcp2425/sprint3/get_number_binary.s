.section .text
    .global get_number_binary

get_number_binary:
    # verifica se n está fora do intervalo [0, 31]
    cmpl $0, %edi            # Se n < 0
    jl invalid_input         # Retorna 0
    cmpl $31, %edi           # Se n > 31
    jg invalid_input         # Retorna 0

    # iicializar o apontador do array e o contador
    movl $5, %ecx            # contador para 5 bits
    leaq (%rsi), %rdx        # apontador para o ínicio do array

convert_bits:
    # extrair o bit mais significativo
    movl %edi, %eax          # copiar n para %eax
    andl $16, %eax           # isolar o bit mais significativo (n & 16)
    shr $4, %eax             # deslocar para posição LSB
    movb %al, (%rdx)         # armazenar o bit no array ('1' ou '0')
    orb $'0', (%rdx)         # converter o número em caractere ('0' ou '1')
    incq %rdx                # avançar para o próximo byte

    # adiciona vírgula se não for o último bit
    decl %ecx                # decrementar o contador
    jz done_bits             # se for o último bit, salta para done_bits
    movb $',', (%rdx)        # escreve a vírgula
    incq %rdx                # aança para o próximo byte

    # deslocar n para a esquerda (próximo bit)
    shl $1, %edi             # n <<= 1
    jmp convert_bits         # repetir para os próximos bits

done_bits:
    # adicionar '\0'
    movb $0, (%rdx)

    movl $1, %eax            # retornar 1
    ret

invalid_input:
    xor %eax, %eax           # retornar 0
    movl $5, %ecx            # inicializar o contador para 5 bits
    movq %rsi, %rdx          # apontador para bits

clear_bits:
    
    incq %rdx                # avançar no array
    loop clear_bits          # repetir para todos os 5 bytes
    ret

.section .note.GNU-stack,"",@progbits
