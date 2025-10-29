.section .data
    .global code
    .global currentSalary

.section .text
    .global new_salary

new_salary:
		movw code(%rip), %ax     #Loads the code into ax
		cmp $10, %ax
		je manager             #Jumps to manager if the code is equal to the code of the manager
		cmp $11, %ax
		je enginner				#Jumps to enginner if the code is equal to the code of the enginner
		cmp $12, %ax
		je technician			#Jumps to technician if the code is equal to the code of the technician
		
		jmp otherFunction      #Jumps to otherFunction in the other cases
manager:
				movw currentSalary(%rip), %ax
				addw $500, %ax					#Adds the salary of manager to the currentSalary
				jmp end
				
enginner:
				movw currentSalary(%rip), %ax
				addw $400, %ax						#Adds the salary of enginner to the currentSalary
				jmp end	
				
technician:
				movw currentSalary(%rip), %ax
				addw $300, %ax						#Adds the salary of technician to the currentSalary
				jmp end			
		
otherFunction:		
				movw currentSalary(%rip), %ax
				addw $250, %ax						#Adds the salary of the other functions to the currentSalary
				jmp end	
				
end: 
ret				
		
		
		
		
