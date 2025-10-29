# int dequeue_value(int* buffer, int length, int* tail, int* head, int *value)

# buffer -> %rdi
# length -> %rsi
# tail   -> %rdx
# head   -> %rcx
# value  -> %r8

.section .text
 .global dequeue_value

dequeue_value:

    pushq %r10             # save %r10
    pushq %r11             # save %r11

    movl (%rcx), %r9d      # head
    movl (%rdx), %r10d     # tail

    cmpl %r9d, %r10d       # head == tail ???
    je empty_buffer

    cmpl $0, %esi          # length == 0 ???
    je empty_buffer

    # dequeue
    movl (%rdi, %r10, 4), %r11d
    movl %r11d, (%r8)      # value = removed element

    # updade tail
    incl %r10d             # tail++

no_wrap:

    movl %r10d, (%rdx)    # *tail = tail

    movl $1, %eax         # 1 - success
 
    popq %r11             # restore %r11
    popq %r10             # restore %r10

    ret

empty_buffer:

    movl $0, %eax         # 0 = failure

    popq %r11             # restore %r11
    popq %r10             # restore %r10
    
    ret
