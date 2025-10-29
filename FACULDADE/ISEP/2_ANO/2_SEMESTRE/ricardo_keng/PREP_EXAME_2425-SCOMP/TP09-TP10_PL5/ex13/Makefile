CC = gcc
CFLAGS = -Wall -Wextra -O2 -pthread
TARGET = ex13
SRC = ex13.c

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

clean:
	rm -f $(TARGET)

run: $(TARGET)
	./$(TARGET)
