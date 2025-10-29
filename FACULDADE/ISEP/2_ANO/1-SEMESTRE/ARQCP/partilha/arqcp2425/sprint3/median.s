.section .text
.extern sort_array
.global median

median:

    cmpq $0, %rsi              #
    jle return_zero            # Erro se comprimento inválido

    # Salva os registradores necessários
    pushq %rbx                 # Salva %rbx para uso posterior
    pushq %rdx                 # Salva %rdx (ponteiro para *me)

    # Configura os argumentos para sort_array
    movq %rdi, %rdi            # Ponteiro para o array (já em %rdi)
    movq %rsi, %rsi            # Comprimento do array (já em %rsi)
    movq $1, %rdx              # Ordem crescente (1) em %rdx

    call sort_array            # Chama a função de ordenação
    testl %eax, %eax           # Verifica se sort_array retornou sucesso
    je restore_and_return_zero # Se falhou, restaura stack e retorna 0

    # Restaura %rdx após sort_array
    popq %rdx                  # Restaura %rdx (ponteiro para *me)

    # Calcular a mediana
    movq %rsi, %rcx            # Copia comprimento para %rcx
    shrq $1, %rcx              # Calcula length / 2 (metade do comprimento)

    testb $1, %sil             # Verifica se length é ímpar (%sil contém o byte menos significativo de %rsi)
    jnz odd_median             # Se for ímpar vai para odd_median

    # Caso par: calcula a média dos dois valores centrais
    movl (%rdi, %rcx, 4), %eax   # Carrega arr[length/2] em %eax
    movl -4(%rdi, %rcx, 4), %ebx # Carrega arr[length/2 - 1] em %ebx
    addl %ebx, %eax              # Soma os dois valores centrais

    sarl $1, %eax               # Divisão inteira por 2 (com arredondamento correto)
    
    movl %eax, (%rdx)           # Armazena a mediana no endereço *me

    movl $1, %eax               # Retorna 1 (sucesso)
    popq %rbx                   # Restaura %rbx
    ret

odd_median:
    # Caso ímpar: pega o elemento central
    movl (%rdi, %rcx, 4), %eax  # arr[length/2] em %eax
    movl %eax, (%rdx)           # Armazena a mediana no endereço *me
    movl $1, %eax          
    popq %rbx                   # Restaura %rbx
    ret

restore_and_return_zero:
    popq %rdx                   # Restaura %rdx
    popq %rbx                   # Restaura %rbx
return_zero:
    movl $0, %eax
    ret

.section .note.GNU-stack,"",@progbits
