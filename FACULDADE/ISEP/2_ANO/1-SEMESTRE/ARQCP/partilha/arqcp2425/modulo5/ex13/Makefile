# A Makefile with variables and suffix rules
# variaveis
INCLUDES = create_matrix.h count_odd_matrix.h
SOURCES = main.c create_matrix.c count_odd_matrix.s
OBJFILES = main.o create_matrix.o count_odd_matrix.o
EXEC = ex13

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