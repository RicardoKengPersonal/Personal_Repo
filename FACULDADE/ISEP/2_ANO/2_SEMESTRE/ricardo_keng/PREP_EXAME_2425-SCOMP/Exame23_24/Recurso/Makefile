# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -g -pthread -lrt

# Target name
TARGET = ex01

# Default rule: compile the program
all: $(TARGET)

$(TARGET): $(TARGET).c
	$(CC) $(CFLAGS) -o $(TARGET) $(TARGET).c

# Run the program
run: $(TARGET)
	./$(TARGET)

# Clean the executable
clean:
	rm -f $(TARGET)
