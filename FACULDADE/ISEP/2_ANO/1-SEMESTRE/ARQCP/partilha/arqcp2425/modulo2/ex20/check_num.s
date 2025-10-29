.section .data
    .global num

.section .text
    .global check_num

check_num:
    movw num(%rip), %ax
    cmp $0, %ax
    jl negative
    jg positive

positive:
    movw $2, %cx
    cltd
    idivw %cx
    movw $0, %cx
    cmp %cx, %dx
    jne positiveOdd
    movw $3, %ax
    jmp end

negative:
    movw $2, %cx
    cltd
    idivw %cx
    movw $0, %cx
    cmp %cx, %dx
    jne negativeOdd
    movw $1, %ax
    jmp end

negativeOdd:
    movw $2, %ax
    jmp end

positiveOdd:
    movw $4, %ax

end:
    ret
