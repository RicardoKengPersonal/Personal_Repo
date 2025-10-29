.section .data
    .global op1
    .global op2

.section .text
    .global exchangeBytes

exchangeBytes:

    movw op1(%rip), %ax    # Load s1 into ax
    movw op2(%rip), %cx    # Load s2 into cx

    # Extract the least significant byte of op1 (AL) and the most significant byte of op2 (CH)
    movb %al, %dl
    movb %ch, %al

    # Double the value of DL (least significant byte)
    addb %dl, %dl

	#Moves the least significant doubled byte of op1 to the most significant byte of op1
    movb %dl, %ah

    ret
