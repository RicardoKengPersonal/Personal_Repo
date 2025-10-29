# A Makefile with variables and suffix rules
# variaveis
INCLUDES = student.h update_address.h
SOURCES = student.c main.c update_address.s
OBJFILES = student.o main.o update_address.o
EXEC = ex04

# Suffix rules
.SUFFIXES: .c .o

# How to build an object .o from a code file .c ; $< -- file name
.c.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}

${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES} ${EXEC}
	
vg: 
	valgrind  ./${EXEC}
	
db:
	gdb -tui ./${EXEC}