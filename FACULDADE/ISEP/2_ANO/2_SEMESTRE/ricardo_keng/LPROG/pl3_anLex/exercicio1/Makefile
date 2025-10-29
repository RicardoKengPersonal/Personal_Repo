# Nome do executável
TARGET = ex1

# Fontes
FLEX_SRC = ex1.l
C_SRC = lex.yy.c

# Compilador e flags
CC = gcc
CFLAGS = -Wall -lfl

# Compila tudo
all: $(TARGET)

# Regra para compilar
$(TARGET): $(C_SRC)
	$(CC) $(C_SRC) -o $(TARGET) $(CFLAGS)

# Gera lex.yy.c
$(C_SRC): $(FLEX_SRC)
	flex $(FLEX_SRC)

# Executa o programa após compilar
run: all
	./$(TARGET) < teste.txt

# Limpa arquivos gerados
clean:
	rm -f $(TARGET) lex.yy.c
