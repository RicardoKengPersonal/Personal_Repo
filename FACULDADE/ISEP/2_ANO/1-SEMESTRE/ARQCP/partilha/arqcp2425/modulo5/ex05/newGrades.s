.section .data
    .global s   #rdi
    .global new_grades  #rsi
    .equ GRADES_OFFSET, 4  #grades start at 4 because theres two bytes for the short and then theres two bytes of gap
    .equ INDEX, 5


.section .text
    .global update_grades

update_grades:
    movq $INDEX, %rax # rax = INDEX
    addq $GRADES_OFFSET, %rdi # rdi = s->grades
    movq %rdi, %rdx # rdx = s->grades

    loop_start:
        cmpq $0, %rax # if (rax == 0)
        je loop_end

        movl (%rsi), %ecx # ecx = *new_grades
        movl %ecx, (%rdx) # s->*(grades+i) = ecx

        addq $4, %rsi # new_grades++
        addq $4, %rdx # s->grades++

        decq %rax # rax--
    jmp loop_start


loop_end:

    ret