CC = gcc
CFLAGS = -Wall -Wextra -O2 -pthread
TARGET = ex06
SRC = ex06.c

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

clean:
	rm -f $(TARGET)

run: $(TARGET)
	./$(TARGET)
