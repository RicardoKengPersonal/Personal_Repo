# int format_command(char* op, int n, char *cmd)

# op  -> %rdi
# n   -> %rsi
# cmd -> %rdx

.section .text
 .global format_command

format_command:

		movl $5, %r8d
		movl %esi, %r9d
		cmpl $0, %r9d
		jl end_fail
		cmpb $0, (%rdi)				#end of string?
		je end_fail
		
loop_extract_cmd:
		cmpb $0, (%rdi)				#end of string?
		je end_fail
		cmpb $' ', (%rdi)			#character == space?
		je skip
		cmpb $'o', (%rdi)			#character == 'o'?
		je copy_o_lowercase
		cmpb $'O', (%rdi)			#character == 'O' ?
		je copy_o_uppercase
		cmpb $'c', (%rdi)			#character == 'c' ?
		je copy_c_lowercase
		cmpb $'C', (%rdi)			#character == 'C' ?
		je copy_c_uppercase
		
		jmp end_fail
		
copy_o_lowercase:
		movb (%rdi), %al
		subb $32, %al
		incq %rdi
		jmp second_check
		
copy_o_uppercase:
		movb (%rdi), %al
		incq %rdi
		jmp second_check
		
copy_c_lowercase:
		movb (%rdi), %al
		subb $32, %al
		incq %rdi
		jmp second_check
		
copy_c_uppercase:
		movb (%rdi), %al
		incq %rdi
		jmp second_check
		
second_check:
		cmpb $'m', (%rdi)
		je copy_m_lowercase
		cmpb $'M', (%rdi)
		je copy_m_uppercase
		cmpb $'f', (%rdi)
		je copy_f_lowercase
		cmpb $'F', (%rdi)
		je copy_f_uppercase
		cmpb $'n', (%rdi)
		je copy_n_lowercase
		cmpb $'N', (%rdi)
		je copy_n_uppercase
		
		jmp end_fail
		
copy_m_lowercase:
		movb (%rdi), %cl
		subb $32, %cl
		incq %rdi
		jmp third_check
		
copy_m_uppercase:
		movb (%rdi), %cl
		incq %rdi
		jmp third_check
		
copy_f_lowercase:
		movb (%rdi), %cl
		subb $32, %cl
		incq %rdi
		jmp third_check
		
copy_f_uppercase:
		movb (%rdi), %cl
		incq %rdi
		jmp third_check
		
copy_n_lowercase:
		movb (%rdi), %cl
		subb $32, %cl
		incq %rdi
		jmp fourth_check
		
copy_n_uppercase:
		movb (%rdi), %cl
		incq %rdi
		jmp fourth_check
		
third_check:
		cmpb $'d', (%rdi)
		je copy_d_lowercase
		cmpb $'D', (%rdi)
		je copy_d_uppercase
		cmpb $'f', (%rdi)
		je copy_second_f_lowercase
		cmpb $'F', (%rdi)
		je copy_second_f_uppercase
		
		jmp end_fail
		
copy_d_lowercase:
		movb (%rdi), %dl
		subb $32, %dl
		incq %rdi
		jmp fourth_check
		
copy_d_uppercase:
		movb (%rdi), %dl
		incq %rdi
		jmp fourth_check
		
copy_second_f_lowercase:
		movb (%rdi), %dl
		subb $32, %dl
		incq %rdi
		jmp fourth_check
		
copy_second_f_uppercase:
		movb (%rdi), %dl
		incq %rdi
		jmp fourth_check
		
fourth_check:
		cmpb $0, (%rdi)
		je place_in_pointer
		cmpb $' ', (%rdi)
		je skip_space
		
		jmp end_fail
		
skip_space:
		incq %rdi
		jmp fourth_check
		
place_in_pointer:
		cmpb $0, %cl
		je place_on
		movb %al, (%rdx)
		incq %rdx
		movb %cl, (%rdx)
		incq %rdx
		movb %dl, (%rdx)
		incq %rdx
		
place_on:
		movb %al, (%rdx)
		incq %rdx
		movb %cl, (%rdx)
		incq %rdx
		movb $',', (%rdx)
		incq %rdx
		
convert_binary:
		cmpl $0, %r8d			 # Fim do loop?
		je end_success					 # Termina
		movl %r9d, %eax
		and $1, %eax             # limpar os bits exceto o último
		addb $'0', %al
		movb %al, (%rdx)         # Armazenar o bit mais a direita
		incq %rdx                # Incrementar próximo byte
		movb $',', (%rdx)
		incq %rdx
		shr $1, %r9d            # 10011 - 1001
		decl %r8d				 #Contador--
		jmp convert_binary	 #loop
skip:
		incq %rdi
		jmp loop_extract_cmd
		
end_success:
		movb $0, (%rdx) 
		movl $1, %eax
		ret
		
end_fail:
		movb $0, (%rdx)
		movl $0, %eax
		ret
		
