.section .data
    .global m #rdi
    .global y #esi
    .global k #edx


.section .text
    .global count_odd_matrix

count_odd_matrix:

    movl $0, %r11d # r11d = 0
    movl $0, %eax # eax = 0
    movl $0, %r9d # r9d = 0
    movl $0, %r10d # r10d = 0

loop:

    movq (%rdi, %r10, 8), %rcx # rcx = m[r10]
    movl (%rcx, %r9, 2), %r11d # r11d = m[r10][r9]

    andl $1, %r11d #  r11d & 1
    cmpl $0, %r11d # if r11d == 0
    jne odd_number # if r11d != 0, jump to odd_number

    incl %r9d # r9d++
    cmpl %r9d, %edx # check end of line

    je next_line # if end of line, jump to next_line
    jmp loop


    odd_number:
        incl %eax # increase number of odd numbers
        incl %r9d # r9d++
        cmpl %r9d, %edx # check end of line
        je next_line # if end of line, jump to next_line
        jmp loop

    next_line:
        movl $0, %r9d # r13 = 0
        incl %r10d # r10d++
        cmpl %r10d, %esi # check end of matrix
        je end # if end of matrix, jump to end
        jmp loop # if not, jump to loop

end:
    ret