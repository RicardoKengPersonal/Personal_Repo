CC = gcc
CFLAGS = -Wall -Wextra -O2

all: ex02

ex02: ex02.c
	$(CC) $(CFLAGS) -o ex02 ex02.c -lrt

clean:
	rm -f ex02

run: ex02
	./ex02

.PHONY: all clean run