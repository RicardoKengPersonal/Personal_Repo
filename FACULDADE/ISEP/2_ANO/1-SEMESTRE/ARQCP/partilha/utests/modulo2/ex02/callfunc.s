  
.section .text
 .global call_func 
 
 call_func:
   # prologue

    pushq  %rbx     # save registers 
    pushq  %r12 
    pushq  %r13 
    pushq  %r14 
    pushq  %r15 
    
    movq   $0x0eadbeef,%rbx   # place marker on registers 
    movq   $0x12345678,%r12
    movq   $0x07654321,%r13 
    movq   $0x76543218,%r14 
    movq   $0x65432187,%r15 
    
    call  *%rdi              # call  function 

    cmpq   $0x0eadbeef,%rbx  # register has changed ? 
    jne    0                #  illegal jump to crash! 
    
    cmpq   $0x12345678,%r12  # register has changed ? 
    jne    0                #  illegal jump to crash! 
    
    cmpq   $0x07654321,%r13  # register has changed ? 
    jne    0                #  illegal jump to crash! 
    
    cmpq   $0x76543218,%r14  # register has changed ? 
    jne    0                #  illegal jump to crash! 
    
    cmpq   $0x65432187,%r15  # register has changed ? 
    jne    0                #  illegal jump to crash! 
    
    popq %r15               # restore registers 
    popq %r14 
    popq %r13 
    popq %r12 
    popq %rbx 
    
    
     # epilogue
     ret


