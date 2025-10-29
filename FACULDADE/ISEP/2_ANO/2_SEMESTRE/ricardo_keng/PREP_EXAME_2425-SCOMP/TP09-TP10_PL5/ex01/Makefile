CC = gcc
CFLAGS = -Wall -Wextra -O2 -pthread
TARGET = ex01
SRC = ex01.c

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

clean:
	rm -f $(TARGET) output.txt

run: $(TARGET)
	./$(TARGET)
