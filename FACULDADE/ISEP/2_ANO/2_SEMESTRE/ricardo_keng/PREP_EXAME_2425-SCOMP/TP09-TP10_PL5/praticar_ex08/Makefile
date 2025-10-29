# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -g -pthread

# Target name
TARGET = pl5_ex08

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
