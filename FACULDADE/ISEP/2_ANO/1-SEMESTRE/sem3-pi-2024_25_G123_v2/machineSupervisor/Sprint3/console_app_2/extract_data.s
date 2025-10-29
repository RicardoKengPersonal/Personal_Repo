.section .text
	.global extract_data

extract_data:

		#%rdi = str, %rsi = token, %rdx = unit, %rcx = value

	pushq %rbx              # Save rbx to save string and use as a local variable

	movl $0, %eax           # Initialize eax
	movb $0, (%rdx)         # place null in rdx
	movl $0, (%rcx)         # place null in rcx

	movq %rdi, %rbx         # place string in rbx

token_search:

	movb (%rbx), %al        # place string character in al
	cmpb $0, %al            # End of string?
	je fail

	cmpq %rbx, %rdi         # Check if string is at the start
	je verify_token

	movb -1(%rbx), %al      # Place character before in al
	cmpb $'#', %al          # character == '#'?
	je verify_token

	incq %rbx				# check next string character
	jmp token_search

fail:
	movl $0, %eax			# Return fail

	popq %rbx               # Restore %rbx
	ret

verify_token:
	movb (%rsi), %al         # place token character in al
	cmpb $0, %al             # End of token?
	je verify_end

	cmpb (%rbx), %al        # string character == token character ?
	jne next_char

	incq %rsi                # Next token character
	incq %rbx               # Next string character

	jmp verify_token

verify_end:
	cmpb $'&', (%rbx)       # string character == '&'?
	je extract_unit         # Token found

	jmp next_char

next_char:
	incq %rbx               # Next string character

	jmp token_search


extract_unit:
	addq $6, %rbx           # Jump to unit name extraction

extract_unit_loop:

	movb (%rbx), %al		#place unit character in al
	cmpb $'&', %al          # character == '&'?
	je extract_value

	movb %al , (%rdx)		#place character in unit
	incq %rdx				#next unit character
	incq %rbx				#next string character

	jmp extract_unit_loop

extract_value:
	movb $0, (%rdx)			#terminate unit extraction ('\0')

	addq $7, %rbx           #skip value

	movl $0, %eax			#clear eax
	movl $0, %edx			#clear edx

numeric_conversion:

    movb (%rbx), %al           # Place character in al
    cmpb $0, %al               # End of string?
    je done

    cmpb $'#', %al             # Character == '#'?
    je done

    cmpb $'0', %al             # Check if its a number in ASCII code
    jl done

    cmpb $'9', %al
    jg done

    subb $'0', %al             # Convert to a number
    imull $10, %edx

    addl %eax, %edx            # Add to sum
    incq %rbx                  # Next character

    jmp numeric_conversion

done:

	movl %edx, (%rcx)       # Place sum in value
	movl $1, %eax           # Return success

	popq %rbx               # Restore %rbx
	ret


