.section .data
	s1:
		.word 1
	s2:
		.word 3
	
	.global s1,s2

.section .text
    .global crossSubBytes

crossSubBytes:
		movw s1(%rip), %ax    #Loads s1 into ax
		movw s2(%rip), %cx		#Loads s2 into cx
		subb %cl, %ah			# AH-CL=AH
		subb %ch, %al			#AL-CH=AL
	
		ret
