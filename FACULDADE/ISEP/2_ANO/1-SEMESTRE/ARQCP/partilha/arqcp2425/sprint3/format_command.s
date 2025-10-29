.section .text
    .global format_command
    .extern get_number_binary
    .global validate_op
    .global compare_op
    .global compare_on
    .global compare_off
 
format_command:
    # epílogo
    pushq %rbx                 # salvar valor de %rbx na pilha
    pushq %r12                 # salvar valor de %r12 na pilha
    pushq %r13                 # salvar valor de %r13 na pilha
 
    movq %rdx, %r12            # restaurar apontador inicial
 
    # converter op para uppercase e remover espaços
    movq %rdi, %r13            # salvar o apontador para op em %r13
    movl $0, %ebx              # inicializar contador de caracteres úteis
    
convert_op:
    movb (%r13), %al           # carregar o próximo caractere de op
    cmpb $0, %al               # verificar se a string terminou
    je done_convert_op         # se terminou, saltar para done_convert_op
 
    cmpb $97, %al              # verificar se é uma letra minúscula ('a')
    jl skip_upper              # se for menor, saltar conversão
    cmpb $122, %al             # verificar se é uma letra minúscula ('z')
    jg skip_upper              # se for maior, saltar conversão
    subb $32, %al              # converter para maiúscula (subtrair 32)
 
skip_upper:
    cmpb $32, %al              # verificar se é um espaço
    je skip_store              # ignorar espaços
 
    cmpb $'A', %al             # validar se é uma letra útil ('A' - 'Z')
    jl skip_store              # ignorar se for antes de 'A'
    cmpb $'Z', %al
    jg skip_store              # ignorar se for depois de 'Z'
 
    movb %al, (%r12)           # armazenar o caractere em cmd
    incq %r12                  # avançar o apontador de cmd
    incb %bl                   # incrementar contador de caracteres úteis
 
skip_store:
    incq %r13                  # avançar para o próximo caractere de op
    jmp convert_op             # repetir para o próximo caractere
 
done_convert_op:
    cmpb $2, %bl               # verificar se há pelo menos 2 caracteres úteis
    jl failure                 # falha se houver menos de 2 caracteres
    movb $0, (%r12)            # adicionar terminador nulo '\0'
 
    # validar se op é OP, ON ou OFF
    movq %rdx, %r12            # restaurar apontador para início de cmd
    call validate_op           # verifica se op é válido
    test %eax, %eax            # verifica o retorno (em eax) da validação
    je failure                 # falha se op não for válido
 
    # adicionar vírgula após o comando
    movq %rdx, %r12            # restaurar o apontador para o início de cmd
    addq %rbx, %r12            # avançar pelo tamanho de cmd
    movb $',', (%r12)          # adicionar vírgula
    incq %r12                  # avançar para a próxima posição
 
    # chamar get_number_binary
    movl %esi, %edi            # passar n para %edi
    movq %r12, %rsi            # passar buffer para %rsi
    call get_number_binary
 
    # verificar retorno de get_number_binary
    test %eax, %eax            # verificar se a função foi bem-sucedida
    je failure                 # falha se retorno for 0
 
    # retorna sucesso
    movl $1, %eax              # definir retorno como sucesso (1)
    jmp done                   # saltar para fim
 
failure:
    xor %eax, %eax             # retorna 0 em caso de falha
 
done:
    # prólogo
    popq %r13                  # restaurar valor original de %r13
    popq %r12                  # restaurar valor original de %r12
    popq %rbx                  # restaurar valor original de %rbx
    ret
 
# validação do op: verifica se é "OP", "ON" ou "OFF"
validate_op:
    pushq %rdi                 # salvar valor de rdi
    movq %r12, %rdi            # carregar cmd em %rdi para comparação
    call compare_op            # verificar se é "OP"
    test %eax, %eax            # verificar o retorno
    jne valid_op               # se válido, saltar para valid_op
 
    call compare_on            # verificar se é "ON"
    test %eax, %eax            # verificar o retorno
    jne valid_op               # se válido, saltar para valid_op
 
    call compare_off           # verificar se é "OFF"
    test %eax, %eax            # verificar o retorno
    jne valid_op               # se válido, saltar para valid_op
 
    xor %eax, %eax             # retorna 0 se todas comparações falharam
    popq %rdi                  # restaurar valor original de rdi
    ret

valid_op:
    movl $1, %eax              # definir retorno como válido (1)
    popq %rdi                  # restaurar valor original de rdi
    ret
 
# comparações específicas para "OP", "ON" e "OFF"
compare_op:
    movb $'O', %al             # carregar 'O' em %al
    cmpb %al, (%rdi)           # comparar com o primeiro caractere
    jne cmp_fail               # falha se não for igual
    movb $'P', %al             # carregar 'P' em %al
    cmpb %al, 1(%rdi)          # comparar com o segundo caractere
    jne cmp_fail               # falha se não for igual
    cmpb $0, 2(%rdi)           # garantir que não há caracteres extras
    jne cmp_fail               # falha se não for o final
    movl $1, %eax              # sucesso, retornar 1
    ret
cmp_fail:
    xor %eax, %eax             # falha, retornar 0
    ret
 
compare_on:
    movb $'O', %al             # carregar 'O' em %al
    cmpb %al, (%rdi)           # comparar com o primeiro caractere
    jne cmp_fail               # falha se não for igual
    movb $'N', %al             # carregar 'N' em %al
    cmpb %al, 1(%rdi)          # comparar com o segundo caractere
    jne cmp_fail               # falha se não for igual
    cmpb $0, 2(%rdi)           # garantir que não há caracteres extras
    jne cmp_fail               # falha se não for o final
    movl $1, %eax              # sucesso, retornar 1
    ret
 
compare_off:
    movb $'O', %al             # carregar 'O' em %al
    cmpb %al, (%rdi)           # comparar com o primeiro caractere
    jne cmp_fail               # falha se não for igual
    movb $'F', %al             # carregar 'F' em %al
    cmpb %al, 1(%rdi)          # comparar com o segundo caractere
    jne cmp_fail               # falha se não for igual
    cmpb %al, 2(%rdi)          # comparar com o terceiro caractere
    jne cmp_fail               # falha se não for igual
    cmpb $0, 3(%rdi)           # garantir que não há caracteres extras
    jne cmp_fail               # falha se não for o final
    movl $1, %eax              # sucesso, retornar 1
    ret
   
.section .note.GNU-stack,"",@progbits
