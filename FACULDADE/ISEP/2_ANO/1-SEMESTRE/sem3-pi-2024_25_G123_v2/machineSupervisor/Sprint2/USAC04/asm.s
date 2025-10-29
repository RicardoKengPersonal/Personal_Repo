# int format_command(char* op, int n, char *cmd)

# op  -> %rdi
# n   -> %rsi
# cmd -> %rdx

.section .text
 .global format_command

format_command:

    cmpb $0, (%rdi)          # *op == '\0'  ???
    je error

trim_op:

    cmpb $' ', (%rdi)        # *op == ' '  ???
    je skip_char

    # search op, on, off

    cmpb $0, (%rdi)          # *op == '\0'  ???
    je error

    cmpb $'o', (%rdi)        # *op == 'o'  ???
    je o_found

    cmpb $'O', (%rdi)        # *op == 'O' ???
    je o_found

    jmp error

skip_char:
    addq $1, %rdi
    jmp trim_op

o_found:

    addq $1, %rdi            # op++

    cmpb $'p', (%rdi)        # *op == p ???
    je p_found

    cmpb $'P', (%rdi)        # *op == P ???
    je p_found

    cmpb $'n', (%rdi)        # *op == n ???
    je n_found

    cmpb $'N', (%rdi)        # *op == N ???
    je n_found

    cmpb $'f', (%rdi)        # *op == f ???
    je f_found

    cmpb $'F', (%rdi)        # *op == F ???
    je f_found

    jmp error

p_found:
    movb $'O', (%rdx)       # *cmd = O

    movb $'P', 1(%rdx)      # *(cmd + 1) = P

    movb $0, 2(%rdx)        # last position == '\0'
    
    addq $2, %rdx           # cmd + 2

    jmp add_comma

n_found:
    movb $'O', (%rdx)       # *cmd = O

    movb $'N', 1(%rdx)      # *(cmd + 1) = N

    movb $0, 2(%rdx)        # last position == '\0'

    addq $2, %rdx           # cmd++

    jmp add_comma

f_found:

    addq $1, %rdi           # op++

    cmpb $'f', (%rdi)       # *op == f ???
    je ff_found

    cmpb $'F', (%rdi)       # *op == F ???
    je ff_found

    jmp error

ff_found:
    movb $'O', (%rdx)       # *cmd = O

    movb $'F', 1(%rdx)      # *(cmd + 1) = F

    movb $'F', 2(%rdx)      # *(cmd + 2) = F

    movb $0, 3(%rdx)        # last position == '\0'

    addq $3, %rdx           # cmd + 3

    jmp add_comma

add_comma:
    movb $',', (%rdx)
    addq $1, %rdx
    jmp add_binary
    
# add the binary representation of n

add_binary:

    movq $5, %rcx          # counter
    movq %rsi, %rax        # copy n to %rax

write_bits:

    sar $1, %rax           # left shift
    jc write_one

    movb $'0', (%rdx)      # store 0

    jmp store_bit

write_one:
    movb $'1', (%rdx)      # store 1

store_bit:

    addq $1, %rdx          # cmd++

    decq %rcx              # counter--
     
    cmpq $0, %rcx          # counter == 0 ???
    je finalize

    movb $',', (%rdx)      # add ,

    addq $1, %rdx          # cmd++

    jmp write_bits

finalize:
    movb $0, (%rdx)        # last position == '\0'
    movl $1, %eax          # 1 - success
    ret

error:
    movl $0, %eax          # 0 - failure
    ret
