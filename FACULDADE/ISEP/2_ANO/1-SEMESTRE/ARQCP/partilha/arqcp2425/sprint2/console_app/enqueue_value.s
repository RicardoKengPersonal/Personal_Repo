.section .text
    .global enqueue_value

enqueue_value:
    # Load the current head and tail values
    movl (%rdx), %eax         # eax = tail (current read position)
    movl (%rcx), %r9d         # r9d = head (current write position)

    # Write the new value at the current head position
    movl %r8d, (%rdi, %r9, 4) # buffer[head] = value

    # Calculate next head position (circular buffer logic)
    leal 1(%r9d), %r8d        # r8d = head + 1
    cmpl %esi, %r8d           # Compare next head with buffer size (rsi)
    jl no_wrap_head           # If within bounds, skip wrapping
    xorl %r8d, %r8d           # If out of bounds, wrap around to 0
no_wrap_head:
    movl %r8d, (%rcx)         # Update head to the next position

    # Check if buffer is full (next head == tail)
    cmpl %eax, %r8d           # Compare next head with tail
    jne not_full              # If not equal, buffer is not full

    # If buffer is full, advance the tail (delete oldest element)
    leal 1(%eax), %eax        # eax = tail + 1
    cmpl %esi, %eax           # Compare new tail with buffer size
    jl tail_wrap              # If within bounds, skip wrapping
    xorl %eax, %eax           # If out of bounds, wrap around to 0
tail_wrap:
    movl %eax, (%rdx)         # Update tail to the new value

    # Return 1 (buffer is full)
    movl $1, %eax
    ret

not_full:
    # Return 0 (buffer is not full)
    movl $0, %eax
    ret
