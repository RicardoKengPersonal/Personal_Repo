.section .note.GNU-stack, "", @progbits
.section .text
.global sort_array

sort_array:
    pushq %rbx              # Salva registradores não voláteis
    pushq %r12
    pushq %r13

    cmpq $0, %rsi           # Verifica se o tamanho do vetor é <= 0
    jle return_failure      # Retorna 0 se o tamanho for inválido

    cmpb $0, %dl            # Verifica se a ordem é 0 (decrescente)
    je descending_order     # Salta para ordenação decrescente se ordem == 0
    cmpb $1, %dl            # Verifica se a ordem é 1 (crescente)
    jne return_failure      # Retorna falha se a ordem não for 0 ou 1

ascending_order:
    movq %rsi, %rcx         # Copia o número de elementos para %rcx
    decq %rcx               # Ajusta para o índice do último par de elementos
    jz sorting_complete     # Se o vetor tem apenas 1 elemento, já está ordenado
outer_loop_asc:
    movq %rdi, %r8          # Ponteiro para o início do vetor
    movq %rcx, %r9          # Contador para o loop interno

compare_and_swap_asc:
    movl (%r8), %eax        # Carrega o valor atual (arr[i]) em %eax
    movl 4(%r8), %ebx       # Carrega o próximo valor (arr[i+1]) em %ebx

    cmpl %ebx, %eax         # Compara arr[i] < arr[i+1] para ordem crescente
    jle skip_swap_asc       # Se já está na ordem correta, pula a troca

    movl %eax, 4(%r8)       # Troca: coloca arr[i] em arr[i+1]
    movl %ebx, (%r8)        # Troca: coloca arr[i+1] em arr[i]

skip_swap_asc:
    addq $4, %r8            # Avança para o próximo par no vetor
    decq %r9                # Decrementa o contador do loop interno
    jnz compare_and_swap_asc # Continua a comparar os pares restantes

    decq %rcx               # Decrementa o número de pares restantes
    jnz outer_loop_asc      # Continua para outra passagem de ordenação

    jmp sorting_complete    # Ordenação completa

descending_order:
    movq %rsi, %rcx         # Copia o número de elementos para %rcx
    decq %rcx               # Ajusta para o índice do último par de elementos
    jz sorting_complete     # Se o vetor tem apenas 1 elemento, já está ordenado
outer_loop_desc:
    movq %rdi, %r8          # Ponteiro para o início do vetor
    movq %rcx, %r9          # Contador para o loop interno

compare_and_swap_desc:
    movl (%r8), %eax        # Carrega o valor atual (arr[i]) em %eax
    movl 4(%r8), %ebx       # Carrega o próximo valor (arr[i+1]) em %ebx

    cmpl %ebx, %eax         # Compara arr[i] > arr[i+1] para ordem decrescente
    jge skip_swap_desc      # Se já está na ordem correta, pula a troca

    movl %ebx, (%r8)        # Troca: coloca arr[i+1] em arr[i]
    movl %eax, 4(%r8)       # Troca: coloca arr[i] em arr[i+1]

skip_swap_desc:
    addq $4, %r8            # Avança para o próximo par no vetor
    decq %r9                # Decrementa o contador do loop interno
    jnz compare_and_swap_desc # Continua a comparar os pares restantes

    decq %rcx               # Decrementa o número de pares restantes
    jnz outer_loop_desc      # Continua para outra passagem de ordenação

sorting_complete:
    movl $1, %eax           # Retorna 1 para indicar sucesso
    popq %r13               # Restaura registradores
    popq %r12
    popq %rbx
    ret

return_failure:
    movl $0, %eax           # Retorna 0 para indicar falha
    popq %r13               # Restaura registradores
    popq %r12
    popq %rbx
    ret
