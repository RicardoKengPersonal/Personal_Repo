# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -g

# Target name
TARGET = pl3_ex03

# Default rule: compile the program
all: $(TARGET)

$(TARGET): $(TARGET).c
	$(CC) $(CFLAGS) -o $(TARGET) $(TARGET).c

# Run the program
run: $(TARGET)
	./$(TARGET) test.txt

# Clean the executable
clean:
	rm -f $(TARGET)
