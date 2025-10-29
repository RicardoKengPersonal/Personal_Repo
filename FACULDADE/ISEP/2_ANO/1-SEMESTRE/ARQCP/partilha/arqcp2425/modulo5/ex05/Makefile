# A Makefile with variables and suffix rules
# variaveis
INCLUDES = newGrades.h
SOURCES = main.c newGrades.s
OBJFILES = main.o newGrades.o
EXEC = ex05

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
