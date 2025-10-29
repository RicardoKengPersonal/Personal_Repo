.section .data
    .equ SHORT, 4
    .equ INT, 12
.section .text

 .global fill_s2        #void fill_s2(s2 *s, short vw[3], int vj, char vc[3]) with *s in (%rdi), vw in (%rsi), vj in %rdx and vc in (%rcx)

fill_s2:
    movq $0, %r9     #index = 0

loopshort:
    cmpq    $3, %r9                 #index = 3
    je      int                     #end loop
    movw    (%rsi, %r9, 2), %ax   #ax = vw[index]
    movw    %ax, SHORT(%rdi, %r9, 2)    #s->w[index] = vw[index]
    incq    %r9                 #index++
    jmp     loopshort           #loop

int:
    movq    $0, %r9     #index = 0      
    movl    %edx, INT(%rdi, %r9, 4)     #s->j = vj

loopchar:
    cmpq    $3, %r9               #index = 3
    je      end                   #end loop
    movb    (%rcx, %r9, 1), %al   #al = vc[index]
    movb    %al, (%rdi, %r9, 1)   #s->c[index] = vc[index]
    incq    %r9     #index++
    jmp     loopchar        #loop

end:
    ret
    