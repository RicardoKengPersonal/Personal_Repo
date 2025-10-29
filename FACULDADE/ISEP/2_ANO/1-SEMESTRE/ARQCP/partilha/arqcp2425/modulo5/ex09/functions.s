.section .data
    .global s  #rdi
    .equ X_OFFSET,0
    .equ STRUCT_B_X_OFFSET,0
    .equ STRUCT_B_A_OFFSET,4
    .equ STRUCT_A_Y_OFFSET,0
    .equ STRUCT_A_X_OFFSET,4
    .equ STRUCT_B_B_OFFSET,16
    .equ STRUCT_B_Z_OFFSET,24
    .equ STRUCT_B_C_OFFSET,26
    .equ STRUCT_B_Y_OFFSET,27
    .equ STRUCT_B_E_OFFSET,31
    .equ STRUCT_B_AX_OFFSET,8

.section .text
    .global fun1
    .global fun2
    .global fun3
    .global fun4


fun1:
    movq STRUCT_B_AX_OFFSET(%rdi), %rax # s -> a.x is 8(%rdi), now in %rax
    jmp end # jmp end

fun2:
    movq STRUCT_B_Z_OFFSET(%rdi), %rax # s -> z is 20(%rdi), now in %rax
    jmp end # jmp end

fun3:
    leaq STRUCT_B_Z_OFFSET(%rdi), %rax # address of s -> z is 20(%rdi), now in %rax
    jmp end # jmp end

fun4:
    movq STRUCT_B_B_OFFSET(%rdi), %r12      # s -> b is 16(%rdi), now in %r12
    movq STRUCT_A_X_OFFSET(%r12), %rax       # s -> b -> x is 4(%r12), now in %rax
    jmp end                                  # jmp end
end:
    ret                                      # ret

