# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -g

# Target name
TARGET = pl1_ex08

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
