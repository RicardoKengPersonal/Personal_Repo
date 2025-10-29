.section .data
    .global s   #rdi
    .global vi  #esi
    .global vc  #dl
    .global vj  #ecx
    .global vd  #r8b
    .equ I_OFFSET, 0
    .equ C_OFFSET,4
    .equ D_OFFSET, 5
    .equ J_OFFSET, 8

.section .text
    .global fill_s1


fill_s1:
    movb %dl, C_OFFSET(%rdi) # s1->c = vc
    movl %esi, I_OFFSET(%rdi) # s1->i = vi
    movb %r8b, D_OFFSET(%rdi) # s1->d = vd
    movl %ecx, J_OFFSET(%rdi) # s1->j = vj
ret