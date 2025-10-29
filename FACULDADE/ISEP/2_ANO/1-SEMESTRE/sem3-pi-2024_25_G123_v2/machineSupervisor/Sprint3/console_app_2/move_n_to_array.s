# move_n_to_array(int* buffer, int length, int *tail, int head, int n, int* array)

# buffer -> %rdi
# length -> %rsi
# tail   -> %rdx
# head   -> %rcx
# n      -> %r8
# array  -> %r9

.section .text
 .global move_n_to_array

move_n_to_array:

    pushq %r10                       # save %r10
    pushq %r11                       # save %r11
    pushq %r12                       # save %r12
    pushq %r13                       # save %r13

    movl (%rcx), %r12d              # head
    movl (%rdx), %r13d              # tail

    # calculate elements in buffer
    movl %r12d, %r10d               # %r10d = head
    subl %r13d, %r10d               # %r10d -= tail

    cmpl $0, %r10d                  # head >= tail ??? 
    jge check_wrap

    addl %esi, %r10d                # wrap case - %r10d += length

check_wrap:
    cmpl %r8d, %r10d                # avalailable elements == n ???
    jl end

ciclo:

    cmpq $0, %r8                    # n == 0 ???
    je no_more_elements

    # dequeue
    movl (%rdx), %r10d             # tail
    movl (%rdi, %r10, 4), %r11d    # removes the last 4 bytes of buffer
    movl %r11d, (%r9)              # *array = removed element


    incl %r10d                     # tail++

    cmpl %esi, %r10d               # tail == length ???
    jb no_wrap

    movl $0, %r10d                 # tail = 0

no_wrap:

    movl %r10d, (%rdx)             # updates tail

    decl %r8d                      # n--
    addq $4, %r9                   # array++

    jmp ciclo

no_more_elements:

    movl $1, %eax                  # 1 - success

    popq %r13                      # restore %r13
    popq %r12                      # restore %r12
    popq %r11                      # restore %r11
    popq %r10                      # restore %r10

    ret

end:

    movl $0, %eax                  # 0 - failure

    popq %r13                      # restore %r13
    popq %r12                      # restore %r12
    popq %r11                      # restore %r11
    popq %r10                      # restore %r10

    ret
