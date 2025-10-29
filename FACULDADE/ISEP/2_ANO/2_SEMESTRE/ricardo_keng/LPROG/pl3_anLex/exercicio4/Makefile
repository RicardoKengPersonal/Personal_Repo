# Nome do executável
TARGET = ex4

# Fontes
FLEX_SRC = ex4.l
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
	./$(TARGET) < ficheiro.txt

# Limpa arquivos gerados
clean:
	rm -f $(TARGET) lex.yy.c
