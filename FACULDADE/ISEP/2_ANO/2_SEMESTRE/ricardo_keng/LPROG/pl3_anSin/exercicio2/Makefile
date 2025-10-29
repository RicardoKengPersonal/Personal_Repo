# Nomes dos arquivos (sem extensão)
FLEX_SRC = flex.l
BISON_SRC = bison.y
TARGET = bison

# Compilador e flags
CC = gcc
CFLAGS = -Wall -g
LEX = flex
YACC = bison

# Arquivos gerados
LEX_C = lex.yy.c
YACC_C = bison.tab.c
YACC_H = bison.tab.h

all: $(TARGET)

$(TARGET): $(LEX_C) $(YACC_C)
	$(CC) $(CFLAGS) -o $@ $(YACC_C) $(LEX_C) -lfl

$(LEX_C): $(FLEX_SRC) $(YACC_H)
	$(LEX) $(FLEX_SRC)

$(YACC_C) $(YACC_H): $(BISON_SRC)
	$(YACC) -d $(BISON_SRC)

run: all
	./$(TARGET) < input.txt

clean:
	rm -f $(TARGET) $(LEX_C) $(YACC_C) $(YACC_H)

.PHONY: all clean run
