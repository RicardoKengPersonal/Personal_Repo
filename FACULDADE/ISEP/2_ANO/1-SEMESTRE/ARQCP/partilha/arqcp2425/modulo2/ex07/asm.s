.section .data
    .global s1
    .global s2

.section .text
    .global crossSubBytes

crossSubBytes:
		movw s1(%rip), %ax    #Loads s1 into ax
		movw s2(%rip), %cx		#Loads s2 into cx
		subb %cl, %ah			# AH-CL=AH
		subb %ch, %al			#AL-CH=AL
	
		ret
		
