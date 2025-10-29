# A Makefile with variables and suffix rules
# variaveis
INCLUDES = fill_student.h
SOURCES = main.c fill_student.c
OBJFILES = main.o fill_student.o
EXEC = ex03

# Suffix rules
.SUFFIXES : .c .o .s

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

debug: ${EXEC}
	gdb -tui ${EXEC}
