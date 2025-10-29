CC = gcc
CFLAGS = -Wall -Wextra -O2 -pthread
TARGET = ex05
SRC = ex05.c

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

clean:
	rm -f $(TARGET) output.txt

run: $(TARGET)
	./$(TARGET)
