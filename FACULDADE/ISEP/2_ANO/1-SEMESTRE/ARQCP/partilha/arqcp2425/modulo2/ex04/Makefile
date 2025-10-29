# A Makefile with variables and suffix rules
# variaveis
INCLUDES = asm.h
SOURCES = main.c asm.s
OBJFILES = main.o asm.o
EXEC = ex4

# Suffix rules
.SUFFIXES : .c .o .s


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
