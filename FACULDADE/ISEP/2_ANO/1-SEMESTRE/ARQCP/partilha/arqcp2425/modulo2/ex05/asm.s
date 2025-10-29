.section .data
    .global s
    .section .text
    .global swapBytes

swapBytes:
    movw s1(%rip), %cx     #Loads s1 into cx
    movb %cl, %ah         #Swaps the byte cl to ah
    movb %ch, %al 		#Swaps the byte ch to al
        
    ret





